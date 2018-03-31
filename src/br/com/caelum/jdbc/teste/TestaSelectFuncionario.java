package br.com.caelum.jdbc.teste;

import java.util.List;
import java.util.stream.Stream;

import br.com.caelum.jdbc.dao.FuncionarioDAO;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaSelectFuncionario {
	
	public static void main(String[] args) {
		
		FuncionarioDAO dao = new FuncionarioDAO();
		
		List<Funcionario> listaFunc = dao.getLista();
		
		Stream<Funcionario> stFunc = listaFunc.stream();
		
		stFunc
			.sorted((s1, s2) -> s1.getNome().compareTo(s2.getNome()))
			.forEach(f -> System.out.printf("ID: %-5d NOME: %-20s USUARIO: %-20s", f.getId(), f.getNome(), f.getUsuario()));
		
		dao.closeResources();
		
	}

}
