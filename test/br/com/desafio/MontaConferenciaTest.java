package br.com.desafio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.desafio.control.ConferenciaControl;
import br.com.desafio.control.PalestraControl;
import br.com.desafio.model.Conferencia;
import br.com.desafio.model.Palestra;
import br.com.desafio.model.Trilha;

class MontaConferenciaTest {

	Conferencia conferencia;
	
	@BeforeEach
	void init() {
		try {
			ArrayList<Palestra> palestras = new PalestraControl().carregaArquivoTexto("input.txt");
			
			this.conferencia = new Conferencia();
			this.conferencia.setAlmocoDuracao(60);
			this.conferencia.setAlmocoInicio(12*60);
			this.conferencia.setAlmocoTitulo("Lunch");
			this.conferencia.setNetworkDuracao(60);
			this.conferencia.setNetworkInicioMinimo(16*60);
			this.conferencia.setNetworkInicioMaximo(17*60);
			this.conferencia.setNetworkTitulo("Networking Event");
			this.conferencia.setTrilhasManhaIni(9*60);
			this.conferencia.setTrilhasManhaFim(12*60);
			this.conferencia.setTrilhasTardeIni(13*60);
			this.conferencia.setTrilhasTardeFim(conferencia.getNetworkInicioMaximo());
			this.conferencia.setTrilhasTitulo("Track");
			
			ConferenciaControl ctrlConf = new ConferenciaControl();
			this.conferencia = ctrlConf.montaConferencia(this.conferencia, palestras);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Teste falhou ao buscar solução");
		}
		
	}
	
	@Test
	void testTemposRespeitados() {
		try {
			for(int i=0; i < conferencia.getTrilhas().size(); i++) {
				Trilha trilha = conferencia.getTrilhas().get(i);
				int totalTempoManha = 0;
				int totalTempoTarde = 0;
				
				for(int j=0; j < trilha.getPalestras().size(); j++) {
					Palestra palestra = trilha.getPalestras().get(j);
					
					
					if(palestra.getTempoTermino() <= conferencia.getAlmocoInicio()) {
						assertTrue((palestra.getTempoInicio() >= conferencia.getTrilhasManhaIni())
									&& (palestra.getTempoTermino() <= conferencia.getTrilhasManhaFim()));
						totalTempoManha += palestra.getDuracao();
					}
					
					if(palestra.getTitulo().equals(conferencia.getNetworkTitulo())) {
						assertTrue((palestra.getTempoInicio() >= conferencia.getNetworkInicioMinimo()) 
									&& (palestra.getTempoInicio() <= conferencia.getNetworkInicioMaximo()));
						
					} else if(palestra.getTempoInicio() >= (conferencia.getAlmocoInicio() + conferencia.getAlmocoDuracao())) {
						assertTrue((palestra.getTempoInicio() >= conferencia.getTrilhasTardeIni())
								&& (palestra.getTempoTermino() <= conferencia.getTrilhasTardeFim()));
						totalTempoTarde += palestra.getDuracao();
					}
					
				}
				
				assertTrue(totalTempoManha <= (conferencia.getTrilhasManhaFim() - conferencia.getTrilhasManhaIni()));
				assertTrue(totalTempoTarde <= (conferencia.getTrilhasTardeFim() - conferencia.getTrilhasTardeIni()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro em testTemposRespeitados");
		}
		
		
	}
	
	@Test
	void testPalestrasNaoForamRepetidas() {
		try {
			

			ArrayList<Palestra> todasPalestras = new ArrayList<Palestra>();
			
			for(int i=0; i < conferencia.getTrilhas().size(); i++) {
				Trilha trilha = conferencia.getTrilhas().get(i);
				todasPalestras.addAll(trilha.getPalestras());
			}
			
			for(int i=0; i < todasPalestras.size(); i++) {
				Palestra pA = todasPalestras.get(i);
				if(pA.getTitulo().equals(conferencia.getAlmocoTitulo()) || pA.getTitulo().equals(conferencia.getNetworkTitulo())) {
					continue;
				}
				
				for(int j=0; j < todasPalestras.size(); j++) {
					Palestra pB = todasPalestras.get(j);
					if(i == j) {
						assertTrue(pA.getTitulo().equals(pB.getTitulo()));
					} else {
						assertTrue(!pA.getTitulo().equals(pB.getTitulo()));
					}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro em: testPalestrasNaoForamRepetidas()");
		}
		
			
		
		
	}
	

}
