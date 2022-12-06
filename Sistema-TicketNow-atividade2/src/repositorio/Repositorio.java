/**********************************
 * Projeto2 de POO (2022.2)
 * 
 **********************************/

package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Ingresso;
import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.Jogo;
import modelo.Time;

public class Repositorio {
	private ArrayList<Time> times = new ArrayList<>(); 
	private ArrayList<Jogo> jogos = new ArrayList<>(); 
	private ArrayList<Ingresso> ingressos = new ArrayList<>();

	public Repositorio() {
		carregarObjetos();
	}

	public void adicionar(Time t)	{
		times.add(t);
	}
	public void remover(Time t)	{
		times.remove(t);
	}

	public Time localizarTime(String nome)	{
		for(Time t : times)
			if(t.getNome().equals(nome))
				return t;
		return null;
	}

	public void adicionar(Jogo j)	{
		jogos.add(j);
	}
	public void remover(Jogo j)	{
		jogos.remove(j);
	}
	public Jogo localizarJogo(int id)	{
		for(Jogo j : jogos)
			if(j.getId() == id)
				return j;
		return null;
	}


	public void adicionar(Ingresso i)	{
		ingressos.add(i);
	}
	public void remover(Ingresso i)	{
		ingressos.remove(i);
	}
	public Ingresso localizarIngresso(int codigo)	{
		for(Ingresso i: ingressos)
			if(i.getCodigo() == codigo)
				return i;
		return null;
	}

	public ArrayList<Time> getTimes() 	{
		return times;
	}
	public ArrayList<Jogo> getJogos() 	{
		return jogos;
	}
	public ArrayList<Ingresso> getIngressos() 	{
		return ingressos;
	}


	public int getTotalTimes()	{
		return times.size();
	}
	public int getTotalJogos()	{
		return jogos.size();
	}
	public int getTotalIngressos()	{
		return ingressos.size();
	}


	//--------------------------------------------------------------------
	public void carregarObjetos()  	{
	//--------------------------------------------------------------------
		// carregar para o repositorio os objetos dos arquivos csv

		try {
			//caso os arquivos nao existam, serao criados vazios
			File f1 = new File( new File(".\\times.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\jogos.csv").getCanonicalPath() ) ; 
			File f3 = new File( new File(".\\ingressos.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() || !f3.exists() ) {
				//System.out.println("criando arquivo .csv vazio");
				FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
				FileWriter arquivo3 = new FileWriter(f3); arquivo3.close();
				return;
			}
		}
		catch(Exception ex)		{
			throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
		}

		String linha;	
		String[] partes;	
		Time time;
		Jogo jogo;
		Time time1;
		Time time2;
		IngressoIndividual individual;
		IngressoGrupo grupo;
		String id, nome, origem, data,local,nome1,nome2, estoque, preco, tipo, codigo;

		Scanner arquivo=null;
		try	{
			File f = new File( new File(".\\times.csv").getCanonicalPath() )  ;
			arquivo = new Scanner(f);	 //  pasta do projeto
			while(arquivo.hasNextLine()) 	{
				linha = arquivo.nextLine().trim();		
				partes = linha.split(";");	
				//System.out.println(Arrays.toString(partes));
				nome = partes[0];
				origem = partes[1];
				time = new Time(nome, origem);
				this.adicionar(time);
			} 
			arquivo.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de times:"+ex.getMessage());
		}

		try	{
			File f = new File( new File(".\\jogos.csv").getCanonicalPath() )  ;
			arquivo = new Scanner(f);	 //  pasta do projeto
			while(arquivo.hasNextLine()) 	{
				linha = arquivo.nextLine().trim();		
				partes = linha.split(";");	
				//System.out.println(Arrays.toString(partes));
				id = partes[0];
				data = partes[1];
				local = partes[2];
				estoque = partes[3];
				preco = partes[4];
				nome1 = partes[5];
				nome2 = partes[6];
				time1 = this.localizarTime(nome1);
				time2 = this.localizarTime(nome2);
				jogo = new Jogo(Integer.parseInt(id), data, local,
						Integer.parseInt(estoque), Double.parseDouble(preco),time1,time2);
				time1.adicionar(jogo);
				time2.adicionar(jogo);
				this.adicionar(jogo);
			} 
			arquivo.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de jogos:"+ex.getMessage());
		}

		try	{
			File f = new File( new File(".\\ingressos.csv").getCanonicalPath())  ;
			arquivo = new Scanner(f);	 //  pasta do projeto
			while(arquivo.hasNextLine()) 	{
				linha = arquivo.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				tipo = partes[0];
				codigo = partes[1];
				id = partes[2];
				if(tipo.equals("INDIVIDUAL")) {
					individual = new IngressoIndividual(Integer.parseInt(codigo));
					jogo = this.localizarJogo(Integer.parseInt(id));
					individual.setJogo(jogo);
					jogo.adicionar(individual);
					this.adicionar(individual);
				}
				else {
					grupo = new IngressoGrupo(Integer.parseInt(codigo));
					for(String idjogo : id.split(",")){
						jogo = this.localizarJogo(Integer.parseInt(idjogo));
						grupo.adicionar(jogo);
						jogo.adicionar(grupo);
					}
					this.adicionar(grupo);
				}
			}
			arquivo.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de ingressos:"+ex.getMessage());
		}
	}

	//--------------------------------------------------------------------
	public void	salvar()  {
	//--------------------------------------------------------------------
		//gravar nos arquivos csv os objetos que est�o no reposit�rio
		FileWriter arquivo=null;
		try	{
			File f = new File( new File(".\\times2.csv").getCanonicalPath())  ;
			arquivo = new FileWriter(f); 
			for(Time t : times) 	{
				arquivo.write(t.getNome()+";"+t.getOrigem()+"\n");	
			} 
			arquivo.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na cria��o do arquivo de times "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\jogos2.csv").getCanonicalPath())  ;
			arquivo = new FileWriter(f); 
			for(Jogo j : jogos) 	{
				arquivo.write(j.getId()+";"+j.getData()+";"+j.getLocal()+";"+j.getEstoque()+";"+j.getPreco()+";"+j.getTime1().getNome()+";"+j.getTime2().getNome()+"\n");	
			} 
			arquivo.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na cria��o do arquivo de jogos "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\ingressos2.csv").getCanonicalPath())  ;
			arquivo = new FileWriter(f) ; 

			for(Ingresso ingresso : ingressos) {
				if(ingresso instanceof IngressoIndividual) 
					arquivo.write("INDIVIDUAL;" +ingresso.getCodigo() +";" + ((IngressoIndividual) ingresso).getJogo().getId()+"\n");	
				else {
					//montar uma lista com os id dos jogos do ingresso de grupo
					ArrayList<String> listaId = new ArrayList<>();
					ArrayList<Jogo> jogos = ((IngressoGrupo)ingresso).getJogos();
					for(Jogo j : jogos) {
						listaId.add(j.getId()+"");
					}
					String sequencia = String.join(",", listaId);
					arquivo.write("GRUPO;" +ingresso.getCodigo() +";" + sequencia +"\n");	
				}
			} 
			arquivo.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na cria��o do arquivo de ingressos "+e.getMessage());
		}

	}
}

