package Modelagem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Excecao.ExplosionException;

public class Tabula {

	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<BombsField>campos=new ArrayList<>();
	
	public Tabula( int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();//iniciar campo dentro do construtor
		associarVizinhos();
		sortiarMinas();
	}
	public void abrindoFields(int linha,int coluna) {
		campos.parallelStream().filter(c->c.getLinha()==linha&&c.getColuna()==coluna)
		.findFirst()/*retorna um elemento opitional campo*/
		.ifPresent(c->c.abrirFiled());
	}
	
	public void abrir(int linha,int coluna) {
		try {
			campos.parallelStream().filter(c->c.getLinha()==linha&&c.getColuna()==coluna)
			.findFirst()/*retorna um elemento opitional campo*/
			.ifPresent(c->c.abrirFiled());
		} catch (ExplosionException e) {
			campos.forEach(c->c.isAberto());
			throw e;
		}
	}
	
	
	public void marcandoFields(int linha,int coluna) {
		campos.parallelStream().filter(c->c.getLinha()==linha&&c.getColuna()==coluna)
		.findFirst()/*retorna um elemento opitional campo*/
		.ifPresent(c->c.marcarField());
	}
	
	
	private void gerarCampos() {
		for(int i=0;i<linhas;i++) {
			for(int j=0;j<colunas;j++) {
				campos.add(new BombsField(i, j));
			}
		}
	}
	
	private void associarVizinhos() {
		for(BombsField campo1:campos) {
			for(BombsField campo2:campos) {
				campo1.adicionarVizinho(campo2);
			}
		}
	}
	
	private void sortiarMinas() {
		long minasDistribuidas=0;
		do {
			int valorAleatorio=(int)(Math.random()*campos.size());
			campos.get(valorAleatorio).minarField();
			minasDistribuidas=campos
					.stream()
					.filter(c->c.isMinado())//filtrar so campos minados
					.count();
			//cast p tornar int
			//vai minar campo  
		}while(minasDistribuidas<minas);//minasArmadas menor q total de minas
	}
	
	public boolean checarSeObjtvAlcancado() {
		return campos.stream().allMatch(c->c.objetivoFields());//lambda passando obj do jogo
	}
	
	public void reStartGame() {
		campos.stream().forEach(c->c.restart());
		sortiarMinas();
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();/*trabalha com grande qtd de strings no caso concatenar*/
		int a=0;/*var pra percorrer campos*/
		for(int i=0;i<linhas;i++) {
			for(int j=0;j<colunas;j++) {
				sb.append(" ");
				sb.append(campos.get(a));/*prgando valor do campo*/
				sb.append(" ");
				a++;
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public int getMinas() {
		return minas;
	}

	public void setMinas(int minas) {
		this.minas = minas;
	}

}

	













