package br.com.desafio.model;

import java.util.ArrayList;

public class Trilha {
	
	private int custo;
	private ArrayList<Palestra> palestras;
	
	public ArrayList<Palestra> getPalestras() {
		return palestras;
	}
	public void setPalestras(ArrayList<Palestra> palestras) {
		this.palestras = palestras;
	}
	public int getCusto() {
		return custo;
	}
	public void setCusto(int custo) {
		this.custo = custo;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Trilha) {
			Trilha s2 = (Trilha) obj;
			if(this.getPalestras().size() != s2.getPalestras().size()) {
				return false;
			} else {
				boolean itensIguais = true;
				for(int i=0; i < this.palestras.size(); i++) {
					if(!this.palestras.get(i).equals(s2.getPalestras().get(i))) {
						itensIguais = false;
						break;
					}
				}
				if(itensIguais) {
					return this.custo == s2.custo;
				} else {
					return false;
				}
			}
			 
		} else {
			return false;
		}
	}
	
	public Trilha getClone()  {
		Trilha sol = new Trilha();
		sol.setCusto(this.custo);
		if(this.palestras != null ) {
			sol.setPalestras(new ArrayList<Palestra>());
			for(Palestra p : this.palestras) {
				sol.getPalestras().add(p.getClone());
			}
		}
		return sol;
	}
	
	@Override
	public String toString() {
		String retorno;
		String palestras = "";
		if(getPalestras() != null) {
			for(Palestra p : getPalestras()) {
				if(!palestras.equals("")) palestras += ",";
				palestras += p.toString() + "";
			}
		}
		
		retorno =  "[SOLUCAO:" +
		"[Custo:"+getCusto()+"]," +
		"[Qtd Palestras:"+((getPalestras() == null) ? 0 : getPalestras().size()) +"]" +
		"[Palestras:"+palestras+"]" +
		"]";
		
		return retorno;
	}
	

}
