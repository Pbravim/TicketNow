package modelo;
import java.util.ArrayList;

public class Time {
	private String nome;
	private String origem;
	private ArrayList<Jogo> jogos = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	
	public String getOrigem() {
		return origem;
	}
	
	public Time(String nome, String origem) {
		super();
		this.nome = nome;
		this.origem = origem;
	}

	public double obterValorArrecadado() {
		double valor = 0;
		for(Jogo j : jogos) {
			valor = valor + j.obterValorArrecadado();
		}
		return valor;
	}
	
	public void adicionar(Jogo jogo) {
		jogos.add(jogo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Jogo j : jogos) {
			sb.append(j.getId()).append(" = " + j.getData()).append(", " + j.getLocal());
		}
		return "nome=" + nome + ", origem=" + origem + "\njogos= " + sb.toString();
	}
	
	
}

