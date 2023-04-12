package com.pastor126.controle;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pastor126.dao.util.Conexao;

/**
 * Servlet implementation class indexControle
 */
@WebServlet("/pub")
public class centralControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public centralControle() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processaReq(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processaReq(request, response);
	}
	
	private void processaReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		try {
			switch (acao) {
			
		case "novo":
			novoCad(request, response);
			break;
			
		case "salvar":
			salvarForm(request, response);
			break;	
			
			}
		}catch(Exception ex) {
			throw new ServletException(ex);
		}
		
	}
		
	private void novoCad (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conexaoJDBC = Conexao.getConexao();
//		Testando conexão com DB.
//		if(conexaoJDBC != null) {
//			System.out.println("Conectado");
//		}else {
//			System.out.println("Sem conexão");
//		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/pub-cadastro.jsp");
		dispatcher.forward(request, response);		
	}
	
	private void salvarForm (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
