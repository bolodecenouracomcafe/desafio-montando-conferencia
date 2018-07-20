package br.com.desafio.control;

import java.util.ArrayList;

import br.com.desafio.model.Conferencia;
import br.com.desafio.model.Palestra;
import br.com.desafio.model.Trilha;

public class ConferenciaControl {

	public void apresentaSolucao(Conferencia conferencia) {
		for(int i=0; i < conferencia.getTrilhas().size(); i++) {
			Trilha t = conferencia.getTrilhas().get(i);
			System.out.println("\n" + conferencia.getTrilhasTitulo() + " " + (i+1) + ":");
			for(int j=0; j < t.getPalestras().size(); j++) {
				Palestra p = t.getPalestras().get(j);
				String horas = PalestraControl.minutosToHoras(p.getTempoInicio());
				String duracao;
				if(p.getTitulo().equals(conferencia.getAlmocoTitulo()) || p.getTitulo().equals(conferencia.getNetworkTitulo())) {
					duracao = "";
				} else {
					if(p.getDuracao() == 5) {
						duracao = "lightning";
					} else {
						duracao = p.getDuracao() + "min";
					}
				}
				System.out.println(horas + " " + p.getTitulo() + " " + duracao);
				
			}
		}
	}
	
	public Conferencia montaConferencia(Conferencia conferencia, ArrayList<Palestra> palestras) throws Exception {
		
		conferencia.setTrilhas(new ArrayList<Trilha>());
		while(palestras.size() > 0) {
			conferencia.getTrilhas().add(this.montaProgramacaoTrilha(conferencia, palestras));
		}
		
		return conferencia;
		
	}
	
	public Trilha montaProgramacaoTrilha(Conferencia conferencia, ArrayList<Palestra> palestras) throws Exception {
		
		TrilhaControl ctrlSolucao = new TrilhaControl();
		
		int custoIdealManha = conferencia.getTrilhasManhaFim() - conferencia.getTrilhasManhaIni();
		Trilha solucaoManha = ctrlSolucao.calculaSolucao(palestras, custoIdealManha);
		
		int custoIdealTarde = conferencia.getTrilhasTardeFim() - conferencia.getTrilhasTardeIni();
		Trilha solucaoTarde = ctrlSolucao.calculaSolucao(palestras, custoIdealTarde);
		
		Trilha trilhaDia = new Trilha();
		trilhaDia.setPalestras(new ArrayList<Palestra>());
		
		Palestra pAnt = null;
		for(int i=0; i < solucaoManha.getPalestras().size(); i++) {
			Palestra pAtual = solucaoManha.getPalestras().get(i);
			pAtual.setTempoInicio((pAnt == null) ? conferencia.getTrilhasManhaIni() : pAnt.getTempoTermino());
			pAtual.setTempoTermino(pAtual.getTempoInicio() + pAtual.getDuracao());
			pAnt = pAtual;
		}
		trilhaDia.getPalestras().addAll(solucaoManha.getPalestras());
		
		Palestra almoco = new Palestra();
		almoco.setTitulo(conferencia.getAlmocoTitulo());
		almoco.setDuracao(conferencia.getAlmocoDuracao());
		almoco.setTempoInicio(conferencia.getAlmocoInicio());
		almoco.setTempoTermino(almoco.getTempoInicio() + almoco.getDuracao());
		trilhaDia.getPalestras().add(almoco);
		
		
		if(solucaoTarde != null) {
			pAnt = null;
			for(int i=0; i < solucaoTarde.getPalestras().size(); i++) {
				Palestra pAtual = solucaoTarde.getPalestras().get(i);
				pAtual.setTempoInicio((pAnt == null) ? conferencia.getTrilhasTardeIni() : pAnt.getTempoTermino());
				pAtual.setTempoTermino(pAtual.getTempoInicio() + pAtual.getDuracao());
				pAnt = pAtual;
			}
			trilhaDia.getPalestras().addAll(solucaoTarde.getPalestras());
			
			Palestra network = new Palestra();
			network.setTitulo(conferencia.getNetworkTitulo());
			network.setDuracao(conferencia.getNetworkDuracao());
			
			Palestra ultimaPalestra = trilhaDia.getPalestras().get(trilhaDia.getPalestras().size()-1);
			if(ultimaPalestra.getTempoTermino() < conferencia.getNetworkInicioMinimo()) {
				network.setTempoInicio(conferencia.getNetworkInicioMinimo());
			} else {
				network.setTempoInicio(ultimaPalestra.getTempoTermino());
			}
			network.setTempoTermino(network.getTempoInicio() + network.getDuracao());
			
			trilhaDia.getPalestras().add(network);
		}
		
		return trilhaDia;
		
	}

	
	
}

