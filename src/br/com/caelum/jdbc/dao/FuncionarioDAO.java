package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Funcionario;

public class FuncionarioDAO {
	
	// Variável utilizada para a conexão com o banco
	private Connection cn = null;
	
	// Cria conexão com o banco no momento da instanciação do objeto
	public FuncionarioDAO(){
		this.cn = new ConnectionFactory().getConnection();
	}
	
	/* Método utilizado para adicionar novos funcionarios no banco de dados */
	public void adiciona(Funcionario func) {
		
		// Query SQL
		String sql = "INSERT INTO funcionarios (nome, usuario, senha) VALUES (?,?,?);";
		
		try (PreparedStatement ps = this.cn.prepareStatement(sql)){
			
			ps.setString(1, func.getNome());
			ps.setString(2, func.getUsuario());
			ps.setString(3, func.getSenha());
			
			ps.execute();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}	
	}
	
	/* Método para buscar a lista de usuários */
	public List<Funcionario> getLista() {
		
		//Query SQL
		String sql = "SELECT id, nome, usuario FROM funcionarios;";
		
		try (PreparedStatement ps = this.cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
			
			List<Funcionario> lista = new ArrayList<>();
			
			while (rs.next()) {
				
				Funcionario func = new Funcionario();
				func.setId(rs.getLong(1));
				func.setNome(rs.getString(2));
				func.setUsuario(rs.getString(3));
				
				lista.add(func);
				
			}
			
			return lista;
			
		} catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}
		
		
	}
	
	// Método que encerra a conexão aberta no momento em que se instancia um objeto dessa classe
		public void closeResources() {
			if (!this.cn.equals(null)) {
				try {
					this.cn.close();
				} catch (SQLException e) { }
			}
		}

}
