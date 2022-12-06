package modelo;

import java.util.ArrayList;

public class IngressoGrupo extends Ingresso{
	private ArrayList<Jogo> jogos = new ArrayList<>();

	public IngressoGrupo(int codigo) {
		super(codigo);
	}
	
	public ArrayList<Jogo> getJogos() {
		return jogos;
	}

	public void adicionar(Jogo jogo) {
		jogos.add(jogo);
		for(Jogo j : jogos) {
			if (j.getId() == jogo.getId())
				j.setEstoque(j.getEstoque() -1);
		}
	}
	
	@Override
	public void cancelar() {
		for (Jogo j : jogos) {
			j.setEstoque(j.getEstoque() +1);
		}
	}
	
	@Override
	public double calcularValor(){
	double valor = 0;
		for(Jogo j : jogos) {
			valor = j.getPreco() + valor;
		}
		return valor * 0.9;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Jogo j : jogos) {
			sb.append(j.getId()).append(",");
		}
		return super.toString() + ", jogos=" + sb.toString();
	}
	
	

}
