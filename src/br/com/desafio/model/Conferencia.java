package br.com.desafio.model;

import java.util.List;

public class Conferencia {

	private int trilhasManhaIni;
	private int trilhasManhaFim;
	private int trilhasTardeIni;
	private int trilhasTardeFim;
	private String trilhasTitulo;
	private int almocoInicio;
	private int almocoDuracao;
	private String almocoTitulo;
	private int networkInicioMinimo;
	private int networkInicioMaximo;
	private int networkDuracao;
	private String networkTitulo;
	
	private List<Trilha> trilhas;

	public List<Trilha> getTrilhas() {
		return trilhas;
	}

	public void setTrilhas(List<Trilha> trilhas) {
		this.trilhas = trilhas;
	}

	public int getTrilhasManhaIni() {
		return trilhasManhaIni;
	}

	public void setTrilhasManhaIni(int trilhasManhaIni) {
		this.trilhasManhaIni = trilhasManhaIni;
	}

	public int getTrilhasManhaFim() {
		return trilhasManhaFim;
	}

	public void setTrilhasManhaFim(int trilhasManhaFim) {
		this.trilhasManhaFim = trilhasManhaFim;
	}

	public int getTrilhasTardeIni() {
		return trilhasTardeIni;
	}

	public void setTrilhasTardeIni(int trilhasTardeIni) {
		this.trilhasTardeIni = trilhasTardeIni;
	}

	public int getTrilhasTardeFim() {
		return trilhasTardeFim;
	}

	public void setTrilhasTardeFim(int trilhasTardeFim) {
		this.trilhasTardeFim = trilhasTardeFim;
	}

	public int getAlmocoInicio() {
		return almocoInicio;
	}

	public void setAlmocoInicio(int almocoInicio) {
		this.almocoInicio = almocoInicio;
	}

	public int getAlmocoDuracao() {
		return almocoDuracao;
	}

	public void setAlmocoDuracao(int almocoDuracao) {
		this.almocoDuracao = almocoDuracao;
	}

	public int getNetworkInicioMinimo() {
		return networkInicioMinimo;
	}

	public void setNetworkInicioMinimo(int networkInicioMinimo) {
		this.networkInicioMinimo = networkInicioMinimo;
	}

	public int getNetworkInicioMaximo() {
		return networkInicioMaximo;
	}

	public void setNetworkInicioMaximo(int networkInicioMaximo) {
		this.networkInicioMaximo = networkInicioMaximo;
	}

	public int getNetworkDuracao() {
		return networkDuracao;
	}

	public void setNetworkDuracao(int networkDuracao) {
		this.networkDuracao = networkDuracao;
	}

	public String getAlmocoTitulo() {
		return almocoTitulo;
	}

	public void setAlmocoTitulo(String almocoTitulo) {
		this.almocoTitulo = almocoTitulo;
	}

	public String getNetworkTitulo() {
		return networkTitulo;
	}

	public void setNetworkTitulo(String networkTitulo) {
		this.networkTitulo = networkTitulo;
	}

	public String getTrilhasTitulo() {
		return trilhasTitulo;
	}

	public void setTrilhasTitulo(String trilhasTitulo) {
		this.trilhasTitulo = trilhasTitulo;
	}
	
}
