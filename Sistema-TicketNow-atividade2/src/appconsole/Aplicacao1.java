package appconsole;

import java.util.Random;

import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.Jogo;
import modelo.Time;

/**
 * SI - POO - Prof. Fausto Ayres
 * 
 * Teste das classes do sistema TicketNow
 *
 */
public class Aplicacao1 {
	public static void main(String[] args) {
		Time t1 = 	new Time("brasil", "br");
		Time t2 = 	new Time("argentina", "ar");
		Time t3 = 	new Time("chile", "ch");	
		Time t4 = 	new Time("bolivia", "bo");	

		Jogo j1 = new Jogo(1,"01/12/2022", "maracana", 10000, 20.0, t1, t2);
		Jogo j2 = new Jogo(2,"02/12/2022", "maracana", 10000, 20.0, t3, t4);
		Jogo j3 = new Jogo(3,"03/12/2022", "maracana", 10000, 20.0, t1, t3);
		Jogo j4 = new Jogo(4,"04/12/2022", "maracana", 10000, 20.0, t2, t4);

		t1.adicionar(j1);
		t1.adicionar(j3);	//j3
		t2.adicionar(j1);
		t2.adicionar(j4);
		t3.adicionar(j2);
		t3.adicionar(j3);
		t4.adicionar(j2);
		t4.adicionar(j4);

		Random sorteio = new Random();

		//ingressos individual
		IngressoIndividual i1 = new IngressoIndividual(sorteio.nextInt(1000000));
		i1.setJogo(j1);
		j1.adicionar(i1);

		IngressoIndividual i2 = new IngressoIndividual(sorteio.nextInt(1000000));
		i2.setJogo(j2);
		j2.adicionar(i2);

		IngressoIndividual i3 = new IngressoIndividual(sorteio.nextInt(1000000));
		i3.setJogo(j3);
		j3.adicionar(i3);

		IngressoIndividual i4 = new IngressoIndividual(sorteio.nextInt(1000000));
		i4.setJogo(j4); 
		j4.adicionar(i4);

		//ingressos gupo
		IngressoGrupo i5 = new IngressoGrupo(sorteio.nextInt(1000000));
		i5.adicionar(j1);
		i5.adicionar(j3);
		j1.adicionar(i5);
		j3.adicionar(i5);

		IngressoGrupo i6 = new IngressoGrupo(sorteio.nextInt(1000000));
		i6.adicionar(j2);
		i6.adicionar(j4);
		j2.adicionar(i6);
		j4.adicionar(i6);

		IngressoGrupo i7 = new IngressoGrupo(sorteio.nextInt(1000000));
		i7.adicionar(j1);
		i7.adicionar(j2);
		i7.adicionar(j3);
		j1.adicionar(i7);
		j2.adicionar(i7);
		j3.adicionar(i7);

		System.out.println("\n----times (nome, origem e id, data e local dos jogos)");
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		System.out.println(t4);

		System.out.println("\n----jogos (id,data,local,estoque,preco,nomes dos times e codigos dos ingressos)");
		System.out.println(j1);
		System.out.println(j2);
		System.out.println(j3);
		System.out.println(j4);

		System.out.println("\n----ingressos emitidos (codigo e id do(s) jogo(s)");
		System.out.println(i1);
		System.out.println(i2);
		System.out.println(i3);
		System.out.println(i4);
		System.out.println(i5);
		System.out.println(i6);
		System.out.println(i7);


		System.out.println("\n----valor de cada ingresso:");
		System.out.println("ingresso "+ i1.getCodigo() + " =" + i1.calcularValor());
		System.out.println("ingresso "+ i2.getCodigo() + " =" + i2.calcularValor());
		System.out.println("ingresso "+ i3.getCodigo() + " =" + i3.calcularValor());
		System.out.println("ingresso "+ i4.getCodigo() + " =" + i4.calcularValor());
		System.out.println("ingresso "+ i5.getCodigo() + " =" + i5.calcularValor());
		System.out.println("ingresso "+ i6.getCodigo() + " =" + i6.calcularValor());
		System.out.println("ingresso "+ i7.getCodigo() + " =" + i7.calcularValor());

		System.out.println("\n----valor arrecadado por cada jogo:");
		System.out.println("jogo 1 =" + j1.obterValorArrecadado());		//114.0
		System.out.println("jogo 2 =" + j2.obterValorArrecadado());		//114.0
		System.out.println("jogo 3 =" + j3.obterValorArrecadado());		//114.0
		System.out.println("jogo 4 =" + j4.obterValorArrecadado());		//60.0

		System.out.println("\n----valor arrecadado por cada time:");
		System.out.println("time 1 =" + t1.obterValorArrecadado());		//228.0
		System.out.println("time 2 =" + t2.obterValorArrecadado());		//174.0
		System.out.println("time 3 =" + t3.obterValorArrecadado());		//228.0
		System.out.println("time 4 =" + t4.obterValorArrecadado());		//174.0

		System.out.println("\nfim do programa");
	}

}
