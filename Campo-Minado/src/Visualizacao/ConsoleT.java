package Visualizacao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Excecao.ExplosionException;
import Excecao.SairException;
import Modelagem.Tabula;

public class ConsoleT {

	private Tabula tabuleiro;
	private Scanner entrada=new Scanner(System.in);
	
	public ConsoleT(Tabula tabuleiro) {
		this.tabuleiro = tabuleiro;
		executarGame();
	}

	private void executarGame() {
		try {
			boolean continuar=true;
			while(continuar) {
				loopGame();
				System.out.println("Outra partida?(S/n)");
				String resposta=entrada.nextLine();
				if("n".equalsIgnoreCase(resposta)) {
					continuar=false;
				}else {
					tabuleiro.reStartGame();
				}
			}
		}catch(SairException e) {
			System.out.println("GOODBYE!!");
		}finally {
			entrada.close();
		}
	}
	
	private void loopGame() {
		try {
			while(!tabuleiro.checarSeObjtvAlcancado()) {
				System.out.println(tabuleiro);
				String digitado=capturarValorDigitado("Digite(x,y): ");
				//Arrays
				Iterator<Integer>xy=Arrays.stream(digitado.split(","))
				.map(e->Integer.parseInt(e.trim())).iterator();
				
				digitado= capturarValorDigitado("1--ABRIR ou 2--(Des)MARCAR");
				if("1".equals(digitado)) {
					tabuleiro.abrindoFields(xy.next(),xy.next());
				}else if("2".equals(digitado)) {
					tabuleiro.marcandoFields(xy.next(), xy.next());
				}
				System.out.println(xy.next());
			}
			System.out.println("YOU WIN!!!");
		} catch (ExplosionException e) {
			System.out.println("YOU LOOSE!!");
		}
	}
	
	private String capturarValorDigitado(String txt) {
		System.out.print(txt);
		String digitado=entrada.nextLine();
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
	
	
	
	
	
	
	
	
	
	
	
}
