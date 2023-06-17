package com.pastor126.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.pastor126.dao.util.Conexao;
import com.pastor126.modelo.Moto;



public class MotoDAO {
	
	private Connection connection;
	
	private void conectar() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = Conexao.getConexao();
		}
	}

	private void desconectar() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
	public Moto inserirMoto(Moto moto) throws SQLException {
		String sql = "INSERT INTO moto (marca, modelo, cor)"
				+ " VALUES (?, ?, ?)";		    
		
		conectar();

		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, moto.getMarca());
		statement.setString(2, moto.getModelo());
		statement.setString(3, moto.getCor());
		
		statement.executeUpdate();
		
		ResultSet resultSet = statement.getGeneratedKeys();
		long id = 0;
		if(resultSet.next())
			id = resultSet.getInt("id");
		statement.close();

		desconectar();
		
		moto.setId(id);
		return moto;
	}

		public List<Moto> listarMoto()throws SQLException{
			List<Moto> listamotos = new ArrayList<Moto>();
			String sql = "SELECT * FROM moto ORDER BY marca, modelo";
			conectar();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				long id = resultSet.getLong("id"); //1
				String marca = resultSet.getString("marca");
				String modelo = resultSet.getString("modelo");
				String cor = resultSet.getString("cor");

				Moto moto = new Moto(marca, modelo, cor);
				moto.setId(id);
				listamotos.add(moto);
			}
			resultSet.close();
			statement.close();

			desconectar();

			return listamotos;
		}
		

		
		
		
		public void apagarMoto(Moto moto)throws SQLException{
			
			String sql = "DELETE FROM moto WHERE id = ?";		
			conectar();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, moto.getId());
		
			statement.close();
			desconectar();
		}
	
		public Moto buscarMotoPorId(long id)throws SQLException{
			Moto moto = null;
			String sql = "SELECT * FROM moto WHERE id = ?";
			conectar();
			PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setLong(1, id);
	        ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				
				String marca = resultSet.getString("marca");
				String modelo = resultSet.getString("modelo");		
				String cor = resultSet.getString("cor");
		
				moto = new Moto(id, marca, modelo, cor);
				  
			   
			}
			resultSet.close();
			statement.close();

			desconectar();

			return moto;
		}
		
		public boolean editaMoto(Moto moto) throws SQLException {
			String sql = "UPDATE moto SET marca = ?, modelo = ?, cor = ?"
					+ " WHERE id = ?";		    
			
			conectar();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, moto.getMarca());
			statement.setString(2, moto.getModelo());
			statement.setString(3, moto.getCor());
			statement.setLong(4, moto.getId());
			
			boolean editado  = statement.executeUpdate() > 0;
			statement.close();

			desconectar();
			return editado;
		}

		

		
		public boolean excluirMoto(Moto moto)throws SQLException{
			
			String sql = "DELETE FROM moto WHERE id = ?";		
			conectar();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, moto.getId());
			boolean excluido = statement.executeUpdate() > 0;
			statement.close();
			desconectar();
			return excluido;
		}


		
	
}
