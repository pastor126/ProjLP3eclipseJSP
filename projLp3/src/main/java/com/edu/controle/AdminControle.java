package com.edu.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.dao.UsuarioDao;
import com.edu.modelo.Usuario;

/**
 * Servlet implementation class AdminControle
 */
@WebServlet("/auth/admin")
public class AdminControle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private UsuarioDao usuarioDAO;

	public AdminControle() {
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
			case "listar":
				listarUsuario(request, response);
				break;
			
			case "apagar":
				excluirUsu(request, response);
				break;
			}
			
			
			
		}
		catch (Exception ex) {
			throw new ServletException(ex);
			}
		
		}
		
		private void listarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			
			List<Usuario> usuarios = usuarioDAO.listarUsuario();
			
			request.setAttribute("listarUsuario", usuarios);
			
			String path =  request.getServletPath() + "/admin-listar-usuario.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			
			dispatcher.forward(request, response);
		}
	

		private void excluirUsu(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			
			long id = Long.parseLong(request.getParameter("id"));
			
			Usuario usuario = new Usuario();
			usuario.setId(id);
			usuarioDAO.apagarUsuario(usuario);
			
			String path = request.getContextPath() + request.getServletPath() + "?acao=listar";
	
			response.sendRedirect(path);
		}

}
