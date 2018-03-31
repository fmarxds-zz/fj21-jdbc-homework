package br.com.caelum.jdbc.teste;

import java.time.LocalDate;
import java.util.Scanner;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaInsert {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Contato contato = new Contato();
		
		System.out.print("     NOME: ");
		contato.setNome(sc.nextLine());
		
		System.out.print("\n    EMAIL: ");
		contato.setEmail(sc.nextLine());
		
		System.out.print("\n ENDEREÇO: ");
		contato.setEndereco(sc.nextLine());
		
		System.out.print("\nNASCIMENTO:\n");
		int diaNasc = sc.nextInt();
		int mesNasc = sc.nextInt();
		int anoNasc = sc.nextInt();
		contato.setDataNascimento(LocalDate.of(anoNasc, mesNasc, diaNasc));
		
		sc.close();
		
		ContatoDAO dao = new ContatoDAO();
		
		dao.adiciona(contato);
		dao.closeResources();
		
		System.out.println("Contato gravado com sucesso!");

	}

}
