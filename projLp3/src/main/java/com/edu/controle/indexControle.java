package com.edu.controle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controle.util.ManipulaData;
import com.edu.dao.UsuarioDao;
import com.edu.dao.util.Conexao;
import com.edu.modelo.Usuario;

/**
 * Servlet implementation class indexControle
 */
@WebServlet("/publica")
public class indexControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    private UsuarioDao usuarioDAO;

	public indexControle() {
		super();
	}	

	public void init() {
		usuarioDAO = new UsuarioDao();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processarRequisicao(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processarRequisicao(request, response);
	}

	private void processarRequisicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		System.out.println(acao);
		try {
			switch (acao) {
			case "novo":
				novoUsuario(request, response);
				break;
			
			case "inserir":
				gravarUsuario(request, response);
				break;	
				
			}
			
			
			
		}
		catch (Exception ex) {
			throw new ServletException(ex);
		}
		
	}

	private void novoUsuario(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		Connection co = Conexao.getConexao();
		
//		Testando conexão:
//		if (co!= null) {
//			System.out.println("Conectou");
//		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/publica-novo-usuario.jsp");
		dispatcher.forward(request, response);
	}
	
	private void gravarUsuario(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		String nome= request.getParameter("nome");
		String cpf= request.getParameter("cpf");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String login= request.getParameter("login");
		String data= request.getParameter("nascimento");
		
		ManipulaData manipulaData = new ManipulaData();
		Date dataNascimento = manipulaData.converterStringData(data);
		
		Usuario usuario = new Usuario(nome, cpf, dataNascimento, email, password, login, false);
		
		Usuario usuarioGravado = usuarioDAO.inserirUsuario(usuario);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/publica-novo-usuario.jsp");
		request.setAttribute("mensagem", "Usuário cadastrado com sucesso");
		dispatcher.forward(request, response);
	}
	
}
