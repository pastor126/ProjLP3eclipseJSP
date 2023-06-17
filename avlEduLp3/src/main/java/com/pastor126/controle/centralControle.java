package com.pastor126.controle;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pastor126.dao.MotoDAO;
import com.pastor126.dao.util.Conexao;
import com.pastor126.modelo.Moto;

/**
 * Servlet implementation class indexControle
 */
@WebServlet("/pub")
public class centralControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private MotoDAO motoDAO;

		public centralControle() {
			super();
		}	

		public void init() {
			motoDAO = new MotoDAO();
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
			novaMoto(request, response);
			break;
			
		case "inserir":
			salvarMoto(request, response);
			break;	
			
		case "listar":
			listarMoto(request, response);
			break;

		case "editar":
			editarMoto(request, response);
			break;
			
		case "update":
			edicao(request, response);
			break;
			
			
		case "excluir":
			excluirMoto(request, response);
			break;	
			
			}
		}catch(Exception ex) {
			throw new ServletException(ex);
		}
		
	}
		

	private void novaMoto (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conexaoJDBC = Conexao.getConexao();
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/pub-cadastro.jsp");
		dispatcher.forward(request, response);		
	}
	
	private void salvarMoto (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String marca= request.getParameter("marca");
		String modelo= request.getParameter("modelo");
		String cor= request.getParameter("cor");
		
		Moto moto = new Moto(marca, modelo, cor);
		
		Moto motoGravada = motoDAO.inserirMoto(moto);
		List<Moto> motos = motoDAO.listarMoto();
		
		request.setAttribute("listarMoto", motos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/listar-moto.jsp");
		request.setAttribute("mensagem", "Moto cadastrada com sucesso");
		dispatcher.forward(request, response);
	}
		
		
	
	
	private void listarMoto(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		List<Moto> motos = motoDAO.listarMoto();
		
		request.setAttribute("listarMoto", motos);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/listar-moto.jsp");
		
		dispatcher.forward(request, response);
	}
	
	
	
	private void editarMoto (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		Moto moto = motoDAO.buscarMotoPorId(id);
		request.setAttribute("moto", moto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/editar-moto.jsp");		
		dispatcher.forward(request, response);
	}

	
	private void edicao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String marca= request.getParameter("marca");
		String modelo= request.getParameter("modelo");
		String cor= request.getParameter("cor");
		
		Moto moto = new Moto(id, marca, modelo, cor);
		
		motoDAO.editaMoto(moto);
		List<Moto> motos = motoDAO.listarMoto();
		
		request.setAttribute("listarMoto", motos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/listar-moto.jsp");
		request.setAttribute("mensagem", "Alterações Salva");
		dispatcher.forward(request, response);
	}
		

	

	
	private void excluirMoto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {

		long id = Long.parseLong(request.getParameter("id"));

		Moto moto = new Moto();
		moto.setId(id);
		motoDAO.excluirMoto(moto);
		String path = request.getContextPath() + request.getServletPath() + "?acao=listar";

		response.sendRedirect(path);
	}


	
	


	
	
	
	
	
}
