package com.site.campoMinado;

import Modelagem.Tabula;
import Visualizacao.ConsoleT;

public class Application {

	public static void main(String[] args) {
	/*main temporario*/	
		Tabula tabuleiro=new Tabula(8, 8, 6);
		
		new ConsoleT(tabuleiro);
		
		
		
		System.out.println(tabuleiro);
		
		
		
		
		
	}
	
}
