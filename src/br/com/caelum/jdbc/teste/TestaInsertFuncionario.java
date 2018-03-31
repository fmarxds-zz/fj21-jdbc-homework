package br.com.caelum.jdbc.teste;

import java.util.Scanner;

import br.com.caelum.jdbc.dao.FuncionarioDAO;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaInsertFuncionario {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Funcionario func = new Funcionario();
		
		System.out.print("NOME: ");
		func.setNome(sc.nextLine());
		
		System.out.print("USUARIO: ");
		func.setUsuario(sc.nextLine());
		
		System.out.print("SENHA: ");
		func.setSenha(sc.nextLine());
		
		sc.close();
		
		FuncionarioDAO dao = new FuncionarioDAO();
		
		dao.adiciona(func);
		dao.closeResources();
		
		System.out.println("\nUSUARIO CADASTRADO COM SUCESSO!");

	}

}
