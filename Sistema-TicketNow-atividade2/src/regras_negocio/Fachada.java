package regras_negocio;
/**********************************
 * Projeto2 de POO (2022.2)
 * 
 * Grupo de alunos: 
 * fulano, beltrano e cicrano
 * 
 **********************************/

import java.util.ArrayList;
import java.util.Random;

import modelo.Ingresso;
import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.Jogo;
import modelo.Time;
import repositorio.Repositorio;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();	
	private Fachada() {}	

	
	public static ArrayList<Time> listarTimes() {
		//retorna todos os times do reposit�rio
		return repositorio.getTimes();
	}
	public static ArrayList<Jogo> listarJogos() {
		//retorna todos os jogos do reposit�rio
		return repositorio.getJogos();
	}
	
	public static ArrayList<Ingresso> listarIngressos() {
		//retorna todos os ingressos do reposit�rio 
		return repositorio.getIngressos();
	}
	public static ArrayList<Jogo> listarJogos(String data) {
		//retorna os jogos do reposit�rio na data fornecida
		//...
		ArrayList<Jogo> lista = new ArrayList<>();
		for (Jogo j:repositorio.getJogos()) {
			if (j.getData().equals(data))
				lista.add(j);
		}
		return lista;

	}
	public static Ingresso localizarIngresso(int codigo) {
		//retorna o ingresso do reposit�rio com o c�digo fornecido
		//...
		return repositorio.localizarIngresso(codigo);
	}
	
	public static Jogo	localizarJogo(int id) {
		//retorna o jogo do reposit�rio com o id fornecido
		//...
		return repositorio.localizarJogo(id);
	}

	public static Time 	criarTime(String nome, String origem) throws Exception {
		//Exce��o: nome existente no repositorio
		//criar o time
		//adicionar este time no reposit�rio
		//salvar o repositorio em arquivo
		for(Time t: repositorio.getTimes()) {
			if(t.getNome().equals(nome))
				throw new Exception ("nome existente no repositorio");
		}
		Time t = new Time(nome,origem);
		repositorio.adicionar(t);
		repositorio.salvar();
		return t;
	}

	public static Jogo 	criarJogo(String data, String local, int estoque, double preco, String nometime1, String nometime2)  throws Exception {
		
		//Exce��o: nometime1 ou nometime2 inexistente no repositorio, 
		//  local ou data vazios, estoque ou pre�o menor ou igual a zero
		//gerar id sequencial do jogo 
		//criar o jogo, 
		//relacionar o jogo com os dois times 
		//adicionar este jogo no reposit�rio
		//salvar o repositorio em arquivo
		int cont1 = 0, cont2=0;
		if(preco <= 0)
			throw new Exception("Selecione um preco valido");
		if(estoque <= 0)
			throw new Exception("Selecione um valor valido");
		if(local.length() == 0)
			throw new Exception("Escolha o local");
		if(data.length() == 0)
			throw new Exception("Escolha uma data");
		for(Time t:repositorio.getTimes()) {
			if (!t.getNome().equals(nometime1))
				cont1++;
			if (!t.getNome().equals(nometime2))
				cont2++;
		}
		if (cont1 == repositorio.getTimes().size() && cont2 == repositorio.getTimes().size())
			throw new Exception("nometime1 e nometime2 inexistente no repositorio");
		else if(cont2 == repositorio.getTimes().size())
			throw new Exception("nometime1 inexistente no repositorio");
		else if(cont1 == repositorio.getTimes().size())
		throw new Exception("nometime2 inexistente no repositorio");
		
		Jogo j = new Jogo(data,local,estoque,preco,nometime1,nometime2);
		for(Time t : repositorio.getTimes()) {
			if(t.getNome().equals(nometime1)) {
				t.adicionar(j);
				j.setTime1(t);
			}
			else if (t.getNome().equals(nometime2)) {
				t.adicionar(j);
				j.setTime2(t);
			}
		}
		repositorio.adicionar(j);
		repositorio.salvar();
		return j;
	}
	
	public static void 	apagarJogo(int id) throws Exception{
		//Exce��o: id inexistente no repositorio
		//remover o jogo do reposit�rio
		//salvar o repositorio em arquivo
		int cont=0;
		for(Jogo j:repositorio.getJogos()) {
			if (j.getId() != id)
				cont++;
		}
		if (cont == repositorio.getJogos().size())
			throw new Exception("id inexistente no repositorio");
		
		for(Jogo j:repositorio.getJogos()) {
			if(j.getId() == id)
				repositorio.remover(j);
		}
		repositorio.salvar();
	}

	public static IngressoIndividual criarIngressoIndividual(int id) throws Exception{
		//Exce��o: id inexistente no repositorio
		//gerar codigo aleat�rio e verificar unicididade do codigo no jogo indicado
		//criar o ingresso individual 
		//relacionar este ingresso com o jogo indicado
		//adicionar este ingresso no reposit�rio
		//salvar o repositorio em arquivo
		int cont=0;
		for(Jogo j:repositorio.getJogos()) {
			if (j.getId() != id)
				cont++;
		}
		if (cont == repositorio.getJogos().size())
			throw new Exception("id inexistente no repositorio");
		
		boolean unico = false;
		Random sorteio = new Random();
		int sort = sorteio.nextInt(1000000);
		while (unico != true) {
			unico = true;
			for(Ingresso i:repositorio.getIngressos()) {
				if (i.getCodigo() == sort) {
					sort = sorteio.nextInt(1000000);
					unico = false;
				}
			}
		}
		
		IngressoIndividual i1 = new IngressoIndividual(sort);
		for (Jogo j:repositorio.getJogos()) {
			if (j.getId() == id) {
				i1.setJogo(j);
				j.adicionar(i1);
			}
		}
		repositorio.adicionar(i1);
		repositorio.salvar();
		return i1;
		
		
	}
	
	public static IngressoGrupo	criarIngressoGrupo(int[] id) throws Exception{
		//Exce��o: id inexistente no repositorio 
		//gerar codigo aleat�rio e verificar unicididade do codigo nos jogos indicados
		//criar o ingresso de grupo, 
		//relacionar este ingresso com os jogos indicados 
		//adicionar este ingresso no reposit�rio
		//salvar o repositorio em arquivo
		int cont = 0, cont1,cont2 = 0;
		
		while (cont < id.length) {
			cont1 = 0;
			for(Jogo j:repositorio.getJogos()) {
				if (j.getId() != id[cont])
					cont1++;
			}
			if (cont1 == repositorio.getJogos().size())
				throw new Exception("id inexistente no repositorio");
			cont++;
		}
		
		boolean unico = false;
		Random sorteio = new Random();
		int sort = sorteio.nextInt(1000000);
		while (unico != true) {
			unico = true;
			for(Ingresso i:repositorio.getIngressos()) {
				if (i.getCodigo() == sort) {
					sort = sorteio.nextInt(1000000);
					unico = false;
				}
			}
		}
		
		IngressoGrupo i1 = new IngressoGrupo(sort);
		while (cont2 < id.length) {
			for (Jogo j:repositorio.getJogos()) {
				if (j.getId() == id[cont2]) {
					i1.adicionar(j);
					j.adicionar(i1);
				}
			}
		cont2++;
		}
		repositorio.adicionar(i1);
		repositorio.salvar();
		return i1;
	}
	
	public static void	cancelarIngresso(int codigo) throws Exception {
		//Exce��o: codigo inexistente no repositorio
		//remover o relacionamento entre ele e o(s) jogo(s) ligados a ele
		//remover ingresso do reposit�rio 
		//salvar o repositorio em arquivo
		
		int cont=0;
		for(Ingresso i:repositorio.getIngressos()) {
			if (i.getCodigo() != codigo)
				cont++;
		}
		if (cont == repositorio.getIngressos().size())
			throw new Exception("codigo inexistente no repositorio");
	
		ArrayList<Ingresso> a = repositorio.getIngressos();
		for(int i=0;i<repositorio.getIngressos().size();i++) {
			if(a.get(i).getCodigo() == codigo) {
				a.get(i).cancelar();
				repositorio.remover(a.get(i));

			}
		}
		
//		for (Ingresso i:repositorio.getIngressos()) {
//			if (i.getCodigo() == codigo) {
//				i.cancelar();
//				repositorio.remover(i);
//			}
//		}
//		repositorio.salvar();
	}
}	



