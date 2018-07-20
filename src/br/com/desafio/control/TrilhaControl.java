package br.com.desafio.control;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import br.com.desafio.model.Palestra;
import br.com.desafio.model.Trilha;

public class TrilhaControl {

	
	public Trilha calculaSolucao(ArrayList<Palestra> palestrasProgramar, int custoIdeal) throws Exception {
		
		List<Trilha> solucoesEcontradas = new ArrayList<Trilha>();

		ArrayList<Palestra> palestras = Palestra.getClone(palestrasProgramar);
		
		int iteracao = 1;
		int qtdSolucoesAnteriores = 0;
		while(true) {
			solucoesEcontradas = procuraSolucaoRecursivo(palestras,solucoesEcontradas,custoIdeal, palestras.size());
			
			iteracao++;
			if(iteracao == 100000) {
				if(verificaCriterioParada(qtdSolucoesAnteriores, qtdSolucoesAnteriores)) {
					break;
				} else {
					qtdSolucoesAnteriores = solucoesEcontradas.size();
					iteracao = 1;
				}
			} 
		}
		
		Trilha melhorSolucao = obterMelhorSolucao(solucoesEcontradas); 
		palestrasProgramar = atualizaPalestrasAProgramar(palestrasProgramar, melhorSolucao);
		
		return melhorSolucao;
		
	}
	
	private ArrayList<Palestra> atualizaPalestrasAProgramar(ArrayList<Palestra> palestrasInserir, Trilha solucao) {
		
		int pos = 0;
		while(pos < palestrasInserir.size()) {
			boolean palestraProgramada = false;
			for(Palestra palestraSolucao : solucao.getPalestras()) {
				if(palestrasInserir.get(pos).getTitulo().equals(palestraSolucao.getTitulo())) {
					palestrasInserir.remove(pos);
					palestraProgramada = true;
					break;
				}
			}
			if(!palestraProgramada) {
				pos++;
			}
		}

		return palestrasInserir;
		
		
	}
	
	private boolean verificaCriterioParada(int qtdSolucoesAnteriores, int qtdNovasSolucoes) {
		return (qtdNovasSolucoes == qtdSolucoesAnteriores);
		
		
	} 
	
	private Trilha obterMelhorSolucao(List<Trilha> solucoes) {
		
		Trilha melhorSolucao = null;
		
		solucoes.sort(new Comparator<Trilha>() {

			@Override
			public int compare(Trilha o1, Trilha o2) {
				if(o1.getCusto() == o2.getCusto()) {
					if(o1.getPalestras().size() == o2.getPalestras().size()) {
						return 0;
					} else if(o1.getPalestras().size() > o2.getPalestras().size()) {
						return -1;
					} else {
						return 1;
					}
				} else if(o1.getCusto() > o2.getCusto()) {
					return -1;
				} else {
					return 1;
				}
			}
			
			
		});
		
		if(solucoes.size() > 0) {
			melhorSolucao = solucoes.get(0);
		}
		
		return melhorSolucao;
		
	}
	
	private List<Trilha> procuraSolucaoRecursivo(ArrayList<Palestra> palestrasProgramar, List<Trilha> solucoes, int custoIdeal, int qtdOriginalItens) throws Exception {
		
		if(palestrasProgramar == null || palestrasProgramar.size() == 0) {
			return solucoes;
		}
		
		boolean isPrimeiraIteracao = (palestrasProgramar.size() == qtdOriginalItens);
		Trilha solucao = calculaSementeNovaSolucao(isPrimeiraIteracao, solucoes);
		
		Palestra palestraInserir = palestrasProgramar.get(new Random().nextInt(palestrasProgramar.size()));
		
		if(buscaPalestraNaSolucao(solucao, palestraInserir) < 0) {
			
			solucao.getPalestras().add(palestraInserir);
			solucao.setCusto(solucao.getCusto() + palestraInserir.getDuracao());
			
			if(calculaAceiteSolucao(solucao, custoIdeal)) {
				if(buscaSolucao(solucoes, solucao) < 0) {
					solucoes.add(solucao);
				}
			}
			
		}

		palestrasProgramar.remove(palestraInserir);
		
		if(palestrasProgramar.size() > 0) {
			return procuraSolucaoRecursivo(Palestra.getClone(palestrasProgramar), solucoes, custoIdeal, qtdOriginalItens);
		} else {
			return solucoes;
		}
		
	}
	
	private Trilha calculaSementeNovaSolucao(boolean isPrimeiraIteracao, List<Trilha> solucoesExistentes) {
		Trilha solucao;
		
		if(isPrimeiraIteracao || solucoesExistentes.size() == 0) {
			solucao = new Trilha();
			solucao.setPalestras(new ArrayList<Palestra>());
		} else {
			solucao = solucoesExistentes.get(new Random().nextInt(solucoesExistentes.size())).getClone();
		}
		
		return solucao;
		
	}
	
	private int buscaPalestraNaSolucao(Trilha solucao, Palestra palestra) {
		int posicao = -1;
		for(int i=0; i < solucao.getPalestras().size(); i++) {
			if(solucao.getPalestras().get(i).getTitulo().equals(palestra.getTitulo())) {
				posicao = i;
				break;
			}
		}
		return posicao;
		
	}
	
	private int buscaSolucao(List<Trilha> listaSolucoes, Trilha solucao) {
		int posicao = -1;
		
		for(int i=0; i < listaSolucoes.size(); i++) {
			if(listaSolucoes.get(i).equals(solucao)) {
				posicao = i;
				break;
			}
		}
		return posicao;
	}
	
	private boolean calculaAceiteSolucao(Trilha solucao, int custoIdeal) {
		return (solucao.getCusto() <= custoIdeal);
	}
	
}
