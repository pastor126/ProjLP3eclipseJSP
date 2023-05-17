package com.edu.controle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import com.edu.controle.i18n.I18nUtil;
import com.edu.controle.seguranca.Criptografia;
import com.edu.controle.util.ManipulaData;
import com.edu.dao.PapelDAO;
import com.edu.dao.UsuarioDao;
import com.edu.dao.util.Conexao;
import com.edu.modelo.Papel;
import com.edu.modelo.Usuario;

/**
 * Servlet implementation class indexControle
 */
@WebServlet("/publica")
public class indexControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    private UsuarioDao usuarioDAO;
    private PapelDAO papelDAO;

	public indexControle() {
		super();
	}	

	public void init() {
		usuarioDAO = new UsuarioDao();
		papelDAO = new PapelDAO();
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
	
	private void gravarUsuario(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
		String nome= request.getParameter("nome");
		String cpf= request.getParameter("cpf");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String login= request.getParameter("login");
		String data= request.getParameter("nascimento");
		
		ManipulaData manipulaData = new ManipulaData();
		Date dataNascimento = manipulaData.converterStringData(data);
		
		String senhaCriptografada = Criptografia.converterParaMD5(password);
		
		Usuario usuario = new Usuario(nome, cpf, dataNascimento, email, senhaCriptografada, login, false);
		
		Usuario usuarioGravado = usuarioDAO.inserirUsuario(usuario);
		Papel papel = papelDAO.buscarPapelPorTipo("USER");
		papelDAO.atribuirPapelUsuario(papel, usuarioGravado);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/publica-novo-usuario.jsp");
		Locale locale = (Locale) Config.get(request.getSession(), Config.FMT_LOCALE);
		if (locale == null) {
			locale = new Locale("pt", "BR");
		}
		
		I18nUtil i18nUtil = new I18nUtil();
		String texto = i18nUtil.getMensagem(locale, "publica-novo-usuario.mensagem");
		
		
		request.setAttribute("mensagem", texto);
		
		dispatcher.forward(request, response);
	}
	
}
