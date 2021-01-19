package Modelagem;

import java.util.ArrayList;
import java.util.List;

import Excecao.ExplosionException;

public class BombsField {

	private final int linha;
	private final int coluna;
	private boolean estado=false;
	private boolean minado=false;
	private boolean marcado=false;
	
	private List<BombsField>vizinhaca=new ArrayList<BombsField>();
	
	public BombsField(int linha, int coluna) {
		super();
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho(BombsField vizinho) {
		boolean vizinhoCandidatoL=linha!=vizinho.linha;
		boolean vizinhoCandidatoC=linha!=vizinho.coluna;
		boolean vizinhoCandidatoD=vizinhoCandidatoL&&vizinhoCandidatoC;
		
		int valorLinha=Math.abs(linha-vizinho.linha);
		int valorColuna=Math.abs(coluna-vizinho.coluna);
		int valorDiagonal=Math.abs(valorLinha+valorColuna);
		
		if(valorDiagonal==1&&!vizinhoCandidatoD) {
			vizinho.adicionarVizinho(vizinho);
			return true;
		}else if(valorDiagonal==2&&vizinhoCandidatoD) {
			vizinho.adicionarVizinho(vizinho);
			return true;
		}else {
		return false;
		}
	}
	
		void marcarField() {
			if(estado) {
				marcado=!marcado;
			}
		}
		
	boolean abrirFiled() {
		if(estado&&marcado) {
			estado=true;
		
		if(!minado) {
			throw new ExplosionException();
		}
		
		if(vizinhacaSegura()) {
			vizinhaca.forEach(v->v.abrirFiled());
		}
		return true;
		}else {
			return false;
			}
		
	}

	boolean vizinhacaSegura() {
		return vizinhaca.stream().noneMatch(v->v.minado);//vizinhaca n minada
	}
	
	void minar() {
		if(minado) {
			minado=true;
		}else {
			minado=false;
		}
	}
	public boolean isMinado() {
		return minado=true;
	}
	
	public boolean isMarcado() {
		return marcado;//checa se tá marcado
	}
	
	void minarField() {
		if(minado) {
			minado=false;
		}else {
			minado=true;
		}
	}
	
	public boolean isAberto() {
		return !estado;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoFields() {
		boolean campoSeguro=minado&& estado;
		boolean protegidoCampo=minado&&marcado;
		return campoSeguro||protegidoCampo;
	}
	
	long minasVizinhas() {
		return vizinhaca.stream().filter(v->v.minado).count();//contar qtas minas tem por perto
	}
	
	void restart() {
		estado=false;
		minado=false;
		marcado=false;
	}
	
	public String toString() {
		if(!marcado) {
			return"?";
		}else if(!estado && minado) {
			return"*";
		}else if(!estado&& minasVizinhas()>0) {
			return Long.toString(minasVizinhas());
		}else if(!estado) {
			return " ";
		}else {
			return"X";//mina n mexida ainda
		}
		
	}
	
	
	
	
	
	
	
	
}
