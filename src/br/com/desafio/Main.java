package br.com.desafio;

import java.util.ArrayList;

import br.com.desafio.control.ConferenciaControl;
import br.com.desafio.control.PalestraControl;
import br.com.desafio.model.Conferencia;
import br.com.desafio.model.Palestra;

public class Main {

	public static void main(String[] args) {

		try {
		
			ArrayList<Palestra> palestras = new PalestraControl().carregaArquivoTexto("input.txt");
			
			Conferencia conferencia = new Conferencia();
			conferencia.setAlmocoDuracao(60);
			conferencia.setAlmocoInicio(12*60);
			conferencia.setAlmocoTitulo("Lunch");
			conferencia.setNetworkDuracao(60);
			conferencia.setNetworkInicioMinimo(16*60);
			conferencia.setNetworkInicioMaximo(17*60);
			conferencia.setNetworkTitulo("Networking Event");
			conferencia.setTrilhasManhaIni(9*60);
			conferencia.setTrilhasManhaFim(12*60);
			conferencia.setTrilhasTardeIni(13*60);
			conferencia.setTrilhasTardeFim(conferencia.getNetworkInicioMaximo());
			conferencia.setTrilhasTitulo("Track");
			
			
			ConferenciaControl ctrlConf = new ConferenciaControl();
			conferencia = ctrlConf.montaConferencia(conferencia, palestras);
			
			ctrlConf.apresentaSolucao(conferencia);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
		
	}
}

