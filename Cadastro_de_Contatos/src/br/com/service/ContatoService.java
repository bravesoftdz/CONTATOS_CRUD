package br.com.service;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.model.Contato;
import br.com.repository.ContatoRepository;

public class ContatoService {

private ContatoRepository repository;
	
	public ContatoService() throws SQLException {
		this.repository = new ContatoRepository();
	}
	
	public void salvar(Contato contato) throws SQLException {
		this.repository.salvar(contato);
	}
	
	public ArrayList<Contato> listar() throws SQLException{
		return this.repository.listar();
	}
	
	public void delete(long idContato) throws SQLException {
		this.repository.delete(idContato);
	}
	
	public void alterar(Contato contato) throws SQLException {
		this.repository.alterar(contato);
	}
	
	public Contato buscar(long id) throws SQLException {
		return this.repository.buscar(id);
	}
	
	public ContatoRepository getRepository() {
		return repository;
	}

	public void setRepository(ContatoRepository repository) {
		this.repository = repository;
	}
	
	
}
