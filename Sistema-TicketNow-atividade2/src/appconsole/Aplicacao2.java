package appconsole;

import java.util.Scanner;

import modelo.Ingresso;
import modelo.Jogo;
import modelo.Time;
import regras_negocio.Fachada;

/**
 * SI - POO - Prof. Fausto Ayres
 * 
 * Teste das classes do sistema TicketNow
 *
 */
public class Aplicacao2 {
	public static void main(String[] args) {
		try {
			Fachada.criarTime("brasil", "br");
			Fachada.criarTime("argentina", "ar");
			Fachada.criarTime("chile", "ch");	
			Fachada.criarTime("bolivia", "bo");	
		}
		catch(Exception ex) {
			System.out.println("problema ao criar time-->"+ex.getMessage());
		}

		try {
			Fachada.criarJogo("02/12/2022", "maracana", 10000, 20.0, "brasil", "argentina");
			Fachada.criarJogo("02/12/2022", "maracana", 10000, 20.0, "chile", "bolivia");
			Fachada.criarJogo("04/12/2022", "maracana", 10000, 20.0, "brasil", "chile");
			Fachada.criarJogo("04/12/2022", "maracana", 10000, 20.0, "argentina", "bolivia");
		}
		catch(Exception ex) {
			System.out.println("problema ao criar jogo-->"+ex.getMessage());
		}

		try {
			Fachada.criarIngressoIndividual(1);		//id do jogo
			Fachada.criarIngressoIndividual(2);		//id do jogo
			Fachada.criarIngressoIndividual(3);		//id do jogo
			Fachada.criarIngressoIndividual(4);		//id do jogo
		}
		catch(Exception ex) {
			System.out.println("problema ao criar ingresso individual-->"+ex.getMessage());
		}

		try {
			Fachada.criarIngressoGrupo(new int[]{1,3} );		//id dos jogos
			Fachada.criarIngressoGrupo(new int[]{2,4} );		//id dos jogos
			Fachada.criarIngressoGrupo(new int[]{1,2,3} );		//id dos jogos
		}
		catch(Exception ex) {
			System.out.println("problema ao criar ingresso grupo-->"+ex.getMessage());
		}


		//---------------------
		//arrecadacao
		//---------------------
		System.out.println("\n---listar valor dos ingressos---");
		for (Ingresso i : Fachada.listarIngressos())
			System.out.println(i.getCodigo()+", valor="+ i.calcularValor());

		System.out.println("\n---listar arrecadacao dos jogos---");
		for (Jogo j : Fachada.listarJogos())
			System.out.println(j.getId() +", arrecadacao="+ j.obterValorArrecadado());

		System.out.println("\n---listar arrecadacao dos times---");
		for (Time t : Fachada.listarTimes())
			System.out.println(t.getNome() + ", arrecadacao="+ t.obterValorArrecadado());


		//---------------------
		//listagem final
		//---------------------
		System.out.println("\n---listar times---");
		for (Time t : Fachada.listarTimes())
			System.out.println(t);

		System.out.println("\n---listar jogos---");
		for (Jogo j : Fachada.listarJogos())
			System.out.println(j);

		System.out.println("\n---listar ingressos---");
		for (Ingresso i : Fachada.listarIngressos())
			System.out.println(i);

		System.out.println("\n---listar jogos na data 02/12/2022---");
		for (Jogo j : Fachada.listarJogos("02/12/2022"))
			System.out.println(j);

		//---------------------
		//consulta jogo 4
		//---------------------
		try {
			Jogo jogo =	Fachada.localizarJogo(4);
			if(jogo==null)
				System.out.println("jogo nao localizado");
			else
				System.out.println("times:"+jogo.getTime1().getNome() + " x " + jogo.getTime2().getNome());
		}
		catch(Exception ex) {
			System.out.println("problema na localizacao do jogo-->"+ex.getMessage());
		}
	
		//---------------------
		//cancelamento do ingresso de grupo
		//---------------------
		Scanner teclado = new Scanner(System.in);

		try {
			System.out.println("\ndigite o codigo do ingresso grupo para ser cancelado");
			int codigo = teclado.nextInt();
			Ingresso ing = Fachada.localizarIngresso(codigo);
			if(ing==null)
				System.out.println("ingresso nao localizado");
			else {
				System.out.println("cancelando o ingresso:"+ing);
				Fachada.cancelarIngresso(codigo);
				
				System.out.println("\n---listar ingressos depois do cancelamento---");
				for (Ingresso i : Fachada.listarIngressos())
					System.out.println(i);
			}
		}
		catch(Exception ex) {
			System.out.println("problema no cancelamento do ingresso-->"+ex.getMessage());
		}


		teclado.close();
		System.out.println("\nfim do programa");
	}

}
