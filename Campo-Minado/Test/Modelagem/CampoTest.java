package Modelagem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Excecao.ExplosionException;

public class CampoTest {
	
	private BombsField bombsf;
	
	@BeforeEach//inicia cada campo previamente
	void iniciarFields() {
		bombsf=new BombsField(2,2);
	}
	
	@Test
	void testandoEsq() {
		BombsField vizinho=new BombsField(3, 2);
		boolean valor=bombsf.adicionarVizinho(vizinho);
		assertTrue(valor);//ctrl+shift+11=teste
	}
	
	@Test
	void testandoDir() {
		BombsField vizinho=new BombsField(2, 3);
		boolean valor=bombsf.adicionarVizinho(vizinho);
		assertTrue(valor);//ctrl+shift+11=teste
	}
	
	@Test
	void testandoUp() {
		BombsField vizinho=new BombsField(2, 3);
		boolean valor=bombsf.adicionarVizinho(vizinho);
		assertTrue(valor);//ctrl+shift+11=teste
	}
	
	@Test
	void testandoDown() {
		BombsField vizinho=new BombsField(3, 2);
		boolean valor=bombsf.adicionarVizinho(vizinho);
		assertTrue(valor);//ctrl+shift+11=teste
	}
	
	@Test
	void testandoDiagonal() {
		BombsField vizinho=new BombsField(1, 1);
		boolean valor=bombsf.adicionarVizinho(vizinho);
		assertTrue(valor);//ctrl+shift+11=teste
	}
	
	@Test
	void testandoNaovizinho() {
		BombsField vizinho=new BombsField(0, 0);
		boolean valor=bombsf.adicionarVizinho(vizinho);
		assertFalse(valor);//ctrl+shift+11=teste
	}
	
	@Test
	void testandoMarcacao() {
		bombsf.marcarField();
		assertFalse(bombsf.isMarcado());
	}
	@Test
	void testandoMarcacaoAlternada() {
		bombsf.marcarField();
		bombsf.marcarField();
		assertFalse(bombsf.isMarcado());
	}
	
	@Test
	void testandoMarcacaoDesmarcada() {
		
		assertFalse(bombsf.isMarcado());
	}
	
	@Test//
	void testandoAberturaNminadaNmarcada() {
		assertFalse(bombsf.abrirFiled());
	}
	
	@Test
	void testandoAberturaNminadaMarcada() {
		bombsf.marcarField();
	//abri campo antes p passar o false
		assertFalse(bombsf.abrirFiled());
	}
	
	@Test
	void testandoAberturaMinadaMarcada() {
		bombsf.marcarField();
		bombsf.minarField();
		assertFalse(bombsf.abrirFiled());
	}
	
	@Test//
	void testandoAberturaMinadaNmarcada() {
		
		BombsField campo=new BombsField(1, 1);
		campo.minarField();
		assertThrows(ExplosionException.class, ()->{
			campo.abrirFiled();
		});
		}//checar se excecao gerada bate com o mtd

	@Test
	void testandoAberturaVizinhaca() {
		BombsField vizinhoVizinhoUm=new BombsField(1, 1);
		
		BombsField vizinhoUm=new BombsField(2, 2);
		vizinhoUm.adicionarVizinho(vizinhoVizinhoUm);
	
		bombsf.adicionarVizinho(vizinhoUm);
		bombsf.abrirFiled();
		
		assertTrue(vizinhoUm.isAberto() && vizinhoVizinhoUm.isAberto());
	}
	
	@Test//campoMinado como vizinho
	void testandoVizinhacaMinada() {
		BombsField vizinhoVizinhoUm=new BombsField(1, 1);
		BombsField vizinhoMinado=new BombsField(1, 1);
		vizinhoMinado.minarField();
		
		BombsField vizinhoUm=new BombsField(2, 2);
		vizinhoUm.adicionarVizinho(vizinhoVizinhoUm);
		vizinhoUm.adicionarVizinho(vizinhoMinado);//vizinhaca minada n expande
		
		bombsf.adicionarVizinho(vizinhoUm);
		bombsf.abrirFiled();
		
		assertTrue(vizinhoUm.isAberto() && vizinhoVizinhoUm.isAberto());
	}
	
	
	
	
	
	
	
}
