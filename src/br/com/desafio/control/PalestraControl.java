package br.com.desafio.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import br.com.desafio.model.Palestra;

public class PalestraControl {

	public ArrayList<Palestra> carregaArquivoTexto(String nomeArquivo) throws Exception {
		ArrayList<Palestra> palestras = new ArrayList<Palestra>();
		
		BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
		try {
			while(br.ready()){
			   String linha = br.readLine();
			   Palestra palestra = this.stringToModel(linha);
			   palestras.add(palestra);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}

		return palestras;
		
	}
	public Palestra stringToModel(String linha) {
		
		Palestra palestra = null;
		if(linha != null && !linha.trim().equals("")) {
			try {
				palestra = new Palestra();
				String strDuracao = linha.replaceAll("[^0-9]", "");
				if(strDuracao.equals("")) {
					int duracao = 5;
					String titulo = linha.split("lightning")[0].trim();
					palestra.setDuracao(duracao);
					palestra.setTitulo(titulo);
				
				} else {
					int duracao = Integer.parseInt(strDuracao);
					String titulo = linha.split(strDuracao)[0].trim();
					palestra.setDuracao(duracao);
					palestra.setTitulo(titulo);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				palestra = null; 
			}	
		}
		
		return palestra;
		
	}
	
	public static String minutosToHoras(int qtdMinutos) {
		
		String amPm = "AM";
		if(qtdMinutos > 720) {
			amPm = "PM";
			qtdMinutos -= 720;
		}
		
		int horas = (qtdMinutos / 60);
		int minutos = (qtdMinutos % 60);
		
		return String.format("%02d", horas) + ":" + String.format("%02d", minutos) + amPm;
		
	}

}
