package modelo;

import java.util.ArrayList;

public class Jogo {
	private static int uid;
	private int id;
	private String data;
	private String local;
	private int estoque;
	private double preco;
	private Time time1;
	private Time time2;
	private ArrayList<Ingresso> ingressos = new ArrayList<>();

	public Jogo(int id, String data, String local, int estoque, double preco, Time time1, Time time2) {
		super();
		this.id = id;
		this.data = data;
		this.local = local;
		this.estoque = estoque;
		this.preco = preco;
		this.time1 = time1;
		this.time2 = time2;
	}
	
	public Jogo(String data, String local, int estoque, double preco, String nometime1, String nometime2) {
		uid++;
		this.id = uid;
		this.data = data;
		this.local = local;
		this.estoque = estoque;
		this.preco = preco;
		
	}

	public int getId() {
		return id;
	}
	
	public String getData() {
		return data;
	}

	public String getLocal() {
		return local;
	}

	public double getPreco() {
		return preco;
	}
	
	public int getEstoque() {
		return estoque;
	}
	
	public Time getTime1() {
		return time1;
	}
	
	public Time getTime2() {
		return time2;
	}
	
	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}
	
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	
	public void setTime1(Time time) {
		time1 = time;
	}
	
	public void setTime2(Time time) {
		time2 = time;
	}
	
	public double obterValorArrecadado() {
		double valor = 0;
		for(Ingresso i : ingressos) {
			valor = valor + i.calcularValor();
		}
		return valor;
	}
	
	public void adicionar(Ingresso ingresso) {
		ingressos.add(ingresso);
	}
	
	public void remover(int codigo) {
		for(Ingresso i:ingressos) {
			if(i.getCodigo() == codigo)
				ingressos.remove(i);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb1 = new StringBuilder();
		for (Ingresso i : ingressos) {
			sb1.append(i.getCodigo() + ",");
		}
		
		return "id=" + id + ", data=" + data + ", local=" + local + ", estoque=" + estoque + ", preco=" + preco
				+ ", time1=" + time1.getNome() + ", time2=" + time2.getNome() + "\ningressos:" + sb1.toString();
	}

	

	
}
