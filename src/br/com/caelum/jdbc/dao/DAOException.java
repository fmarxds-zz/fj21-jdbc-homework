package br.com.caelum.jdbc.dao;

public class DAOException extends RuntimeException{
	
	/**
	 * Exceção para as classes que se conectam com o Banco de dados
	 */
	
	private static final long serialVersionUID = 1L;

	public DAOException (String msg) {
		super(msg);
	}
	
	public DAOException (String msg, Throwable t) {
		super(msg, t);
	}

}
