package modelo;

public class IngressoIndividual extends Ingresso{
	private Jogo jogo;
	
	public IngressoIndividual(int codigo) {
		super(codigo);
		// TODO Auto-generated constructor stub
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
		this.jogo.setEstoque(this.jogo.getEstoque() -1);
	}
	
	public double calcularValor() {
		return jogo.getPreco() * 1.2;
	}
	
	@Override
	public void cancelar() {
		jogo.setEstoque(jogo.getEstoque() +1);
		
	}
	
	@Override
	public String toString() {
		return super.toString() + ", jogo=" + jogo.getId();
	}
	

}
