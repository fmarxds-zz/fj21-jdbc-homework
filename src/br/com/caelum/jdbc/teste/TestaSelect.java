package br.com.caelum.jdbc.teste;

import java.util.List;
import java.util.stream.Stream;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaSelect {
	
	public static void main(String[] args) {
		
		ContatoDAO dao = new ContatoDAO();
		
		List<Contato> listaContatos = dao.getList();
		
		Stream<Contato> stContatos = listaContatos.stream();
		
		stContatos
			.sorted((s1, s2) -> s1.getNome().compareTo(s2.getNome()))
			.forEach(x -> System.out.printf("ID: %-5d NOME: %-10s EMAIL: %-25s ENDERECO: %-30s DT_NASC: %s\n", x.getId(), x.getNome(), x.getEmail(), x.getEndereco(), x.getDataNascimento()));

		dao.closeResources();
		
	}
	

}
