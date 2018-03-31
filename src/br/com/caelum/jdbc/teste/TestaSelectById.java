package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDAO;

public class TestaSelectById {
	
	public static void main(String[] args) {
		
		ContatoDAO dao = new ContatoDAO();
		System.out.println(dao.getByID(1).getNome());
		
		dao.closeResources();
	}

}