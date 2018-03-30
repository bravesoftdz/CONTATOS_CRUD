package br.com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.model.Contato;
import br.com.service.ContatoService;

@WebServlet("/ContatoController")
public class ContatoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Contato contato;
	private ContatoService service;
	
    public ContatoController() throws SQLException {
    	this.service = new ContatoService();
    	this.contato = new Contato();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		//if(action == null)	
			//action = "atualizar";
		
		
		try {
			switch (action) {
				
			case "editar":
				editarContato(request, response);
				break;
					
			case "deletar": 
				deletarContato(request, response);
				break;
					
			case "atualizar":
				atualizarContato(request, response);
				break;
					
			case "listar":
				listarContatos(request, response);
				break;
					
			default:
				listarContatos (request, response);
				break;
			}
				
		}catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void listarContatos (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		ArrayList<Contato> contatos = this.service.listar();
		request.setAttribute("contatos", contatos);
		RequestDispatcher rd = request.getRequestDispatcher("cadastro_contato.jsp");
		rd.forward(request, response);
	}
	
	private void deletarContato (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		long id = Long.parseLong(request.getParameter("id"));
		this.service.delete(id);
		listarContatos(request, response);
	}
	
	private void editarContato (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		long id = Long.parseLong(request.getParameter("id"));
		
		Contato contatoExistente = this.service.buscar(id);
		RequestDispatcher rd = request.getRequestDispatcher("atualizar_contato.jsp");
		request.setAttribute("contato", contatoExistente);
		rd.forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		
		this.contato.setNome(nome);
		this.contato.setEmail(email);
		this.contato.setTelefone(telefone);
		
		try {
			service.salvar(contato);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		/*response.getWriter().
		append("<html>")
		.append("<head> <title>Usuarios Cadastrados</title> <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\"> </head>")
		.append("<body> <h2> Usuario " +contato.getNome() + " Cadastrado com sucesso!!</h2></body>")
		.append("</html>");*/
		
		try {
			listarContatos(request, response);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	private void atualizarContato (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		long id = Long.parseLong(request.getParameter("id"));
		
		System.out.println("Chegou no Atualizar");
		String nome = request.getParameter("nome");
		System.out.println(nome);
		String email = request.getParameter("email");
		System.out.println(email);
		String telefone = request.getParameter("telefone");
		System.out.println(telefone);
		
		Contato contatoAtualizar = new Contato();
		
		contatoAtualizar.setNome(nome);
		contatoAtualizar.setEmail(email);
		contatoAtualizar.setTelefone(telefone);
		contatoAtualizar.setId(id);
		
		this.service.alterar(contatoAtualizar);
		listarContatos(request, response);
	}

}
