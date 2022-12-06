package modelo;

public abstract class Ingresso {
	private int codigo;
	
	public Ingresso(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public abstract double calcularValor();

	@Override
	public String toString() {
		return "codigo=" + codigo;
	}

	public void cancelar() {		
	}

	
}
