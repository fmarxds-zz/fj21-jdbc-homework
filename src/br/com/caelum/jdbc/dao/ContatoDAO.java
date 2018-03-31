package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDAO {
	
	// Conexão com o Banco de Dados
	private Connection cn = null;
	
	// Inicia a conexão com o Banco de Dados na instanciação de um objeto do tipo ContatoDAO
	public ContatoDAO() {
		this.cn = new ConnectionFactory().getConnection();
	}
	
	
	// Método que adiciona contatos no Banco de Dados
	public void adiciona(Contato contato) {
		
		// Query para inserir novo Contato no Banco de Dados
		String sql = "INSERT INTO contatos (nome, email, endereco, dataNascimento) VALUES (?,?,?,?);";
		
		try (PreparedStatement ps = this.cn.prepareStatement(sql);){
			
			// Insere as informações no Statement
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEmail());
			ps.setString(3, contato.getEndereco());
			ps.setDate(4, Date.valueOf(contato.getDataNascimento()));
			
			// Executa a Query
			ps.execute();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	// Método que retorna a lista de contatos do Banco de Dados
	public List<Contato> getList(){
		
		// Query para buscar os contatos no Banco de Dados
		String sql = "SELECT * FROM contatos;";
		
		try (PreparedStatement ps = this.cn.prepareStatement(sql); ResultSet rs = ps.executeQuery();){
			
			// Cria a lista que irá ser retornada à quem chamar o método
			List<Contato> lista = new ArrayList<>();
			
			while (rs.next()) {
				
				// Instancia um contato de acordo com os dados do ResultSet
				Contato contato = new Contato();
				contato.setId(rs.getLong(1));
				contato.setNome(rs.getString(2));
				contato.setEmail(rs.getString(3));
				contato.setEndereco(rs.getString(4));
				contato.setDataNascimento(rs.getDate(5).toLocalDate());
				
				// Adiciona o contato à lita
				lista.add(contato);
				
			}
			
			// Retorna a lista
			return lista;
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		
	}
	
	// Método que retorna um objeto de acordo com o ID passado, do Banco de Dados
	public Contato getByID(long id) {
		
		// Query SQL
		String sql = "SELECT * FROM contatos WHERE id = (?);";
		
		try(PreparedStatement ps = this.cn.prepareStatement(sql);){
			
			ps.setLong(1, id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Contato contato = new Contato();
			
			contato.setId(rs.getLong(1));
			contato.setNome(rs.getString(2));
			contato.setEmail(rs.getString(3));
			contato.setEndereco(rs.getString(4));
			contato.setDataNascimento(rs.getDate(5).toLocalDate());
			
			rs.close();
			
			return contato;
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	public List<Contato> getByName(String nome){
		
		// Query SQL
		String sql = "SELECT * FROM contatos WHERE nome LIKE '%%' ? '%%'";
		
		try (PreparedStatement ps = this.cn.prepareStatement(sql)){
			
			ps.setString(1, nome);
			
			ResultSet rs = ps.executeQuery();
			
			List<Contato> lista = new ArrayList<>();
			
			while (rs.next()) {
				
				Contato contato = new Contato();
				contato.setId(rs.getLong(1));
				contato.setNome(rs.getString(2));
				contato.setEmail(rs.getString(3));
				contato.setEndereco(rs.getString(4));
				contato.setDataNascimento(rs.getDate(5).toLocalDate());
				
				lista.add(contato);
			}
			
			rs.close();
			
			return lista;
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		
	}
	
	// Método para alterar dados com base em seu ID
	public void modifyById(Contato c, long id) {
		
		//Query SQL
		String sql = "UPDATE contatos SET nome=?, email=?, endereco=?, dataNascimento=? WHERE id=?;";
		
		try (PreparedStatement ps = this.cn.prepareStatement(sql)){
			
			ps.setString(1, c.getNome());
			ps.setString(2, c.getEmail());
			ps.setString(3, c.getEndereco());
			ps.setDate(4, Date.valueOf(c.getDataNascimento()));
			ps.setLong(5, id);
			
			ps.execute();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		
		
	}
	
	// Método para remover contato com base em seu ID
	public void removeById(long id) {
		
		//Query SQL
		String sql = "DELETE FROM contatos WHERE id=?;";
		
		try (PreparedStatement ps = this.cn.prepareStatement(sql)){
			
			ps.setLong(1, id);
			ps.execute();
			
			
		} catch (SQLException e) {
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
