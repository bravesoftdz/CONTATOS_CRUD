package br.com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.connection.Conexao;
import br.com.model.Contato;

public class ContatoRepository {
	
private Connection con;
	
	public ContatoRepository() throws SQLException {
		
		this.con = Conexao.getConnection();
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public boolean salvar(Contato contato) throws SQLException {
		
		this.con = Conexao.getConnection();
		String insert = "INSERT INTO contato (nome,email,telefone)" 
				+" VALUES (?,?,?)" ;
		
		PreparedStatement state = this.con.prepareStatement(insert);
		
		state.setString(1, contato.getNome() );
		state.setString(2, contato.getEmail());
		state.setString(3, contato.getTelefone());
		
		boolean saved = state.executeUpdate() > 0;
		state.close();
		
		return saved;
	}
	
	public ArrayList<Contato> listar() throws SQLException{
		
		ArrayList <Contato> contatos = new ArrayList<>();
		this.con = Conexao.getConnection();
		
		String select = "SELECT * FROM contato ";
		
		PreparedStatement state = this.con.prepareStatement(select);
		ResultSet result = state.executeQuery();
		
		while (result.next()) {
			Contato contato = new Contato();
			
			contato.setId(result.getLong("id"));
			contato.setNome(result.getString("nome"));
			contato.setEmail(result.getString("email"));
			contato.setTelefone(result.getString("telefone"));
			
			contatos.add(contato);
		}
		
		result.close();
		state.close();
		this.con.close();
		
		return contatos;
	}
	
	public boolean delete(Long idContato) throws SQLException {
		
		this.con = Conexao.getConnection();
		String delete = "DELETE FROM contato WHERE id = ? ";
		
		PreparedStatement state = this.con.prepareStatement(delete);
		state.setLong(1, idContato);
		
		Boolean deletado = state.executeUpdate() > 0;
		state.close();
		this.con.close();
		
		return deletado;
	}
	
	public boolean alterar(Contato contato) throws SQLException {
		
		this.con = Conexao.getConnection();
		
		String update = "UPDATE contato SET nome = ?, email = ?, telefone = ? "
				+ " WHERE id = ? ";
		
		PreparedStatement state = this.con.prepareStatement(update);
		
		state.setString(1, contato.getNome());
		state.setString(2, contato.getEmail());
		state.setString(3, contato.getTelefone());
		state.setLong(4, contato.getId());
		
		boolean atualizado = state.executeUpdate() > 0;
		state.close();
		state.close();
		
		return atualizado;
	}
	
	public Contato buscar (Long id) throws SQLException{
		
		this.con = Conexao.getConnection();
		Contato contato = new Contato();
		String select = "SELECT * FROM contato WHERE id = ? ";
		
		PreparedStatement state = this.con.prepareStatement(select);
		state.setLong(1, id);
		
		ResultSet result = state.executeQuery();
		while(result.next()) {
			
			contato.setId(result.getLong("id"));
			contato.setNome(result.getString("nome"));
			contato.setEmail(result.getString("email"));
			contato.setTelefone(result.getString("telefone"));
		}
		
		state.close();
		result.close();
		this.con.close();
		
		return contato;
	}

}
