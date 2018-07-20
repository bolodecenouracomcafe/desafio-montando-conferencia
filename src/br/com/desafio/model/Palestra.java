package br.com.desafio.model;

import java.util.ArrayList;
import java.util.List;

import br.com.desafio.control.PalestraControl;

public class Palestra {
	
	private String titulo;
	private int duracao;
	private int tempoInicio;
	private int tempoTermino;
    
	public Palestra() {
		
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getTempoInicio() {
		return tempoInicio;
	}

	public void setTempoInicio(int tempoInicio) {
		this.tempoInicio = tempoInicio;
	}

	public int getTempoTermino() {
		return tempoTermino;
	}

	public void setTempoTermino(int tempoTermino) {
		this.tempoTermino = tempoTermino;
	}
	
	public Palestra getClone() {
		Palestra p = new Palestra();
		p.setTitulo(this.titulo);
		p.setDuracao(this.duracao);
		p.setTempoInicio(this.tempoInicio);
		p.setTempoTermino(this.tempoTermino);
		return p;
	}
	
	public static ArrayList<Palestra> getClone(List<Palestra> lista) {
		ArrayList<Palestra> listaClone = null;
		if(lista != null) {
			listaClone = new ArrayList<Palestra>();
			for(Palestra pclone : lista) {
				listaClone.add(pclone.getClone());
			}
			
		}
		return listaClone; 
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Palestra) {
			Palestra p2 = (Palestra) obj;
			return this.titulo.equals(p2.getTitulo())
					&& this.duracao == p2.getDuracao() 
					&& this.tempoInicio == p2.getTempoInicio()
					&& this.tempoTermino == p2.getTempoTermino();
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		
		return "[" +
				"Titulo:"+getTitulo()+","+
				"Duracao:"+getDuracao()+","+
				"Inicio:"+PalestraControl.minutosToHoras(getTempoInicio())+"," +
				"Termino:"+PalestraControl.minutosToHoras(getTempoTermino())+"" +
				"]";
	}
	
	
	
}
