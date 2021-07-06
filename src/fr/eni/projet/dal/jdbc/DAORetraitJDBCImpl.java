package fr.eni.projet.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Retrait;
import fr.eni.projet.dal.DAORetrait;

/**
 * @author pconchou2021
 *
 */
public class DAORetraitJDBCImpl implements DAORetrait {

	private String insert = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (?,?,?,?);";

	private String delete = "DELETE FROM RETRAITS WHERE no_article=?";

	private String update = "UPDATE RETRAITS SET no_article=?, rue=?, code_postal=?, ville=? WHERE no_article=?;";

	private String selectAll = "SELECT no_article, rue, code_postal, ville FROM RETRAITS;";

	private String selectById = "SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article=?;";
	
	@Override
	public void insert(Retrait u) {
		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, u.getNo_article());
			psmt.setString(2, u.getRue());
			psmt.setString(3, u.getCode_postal());
			psmt.setString(4, u.getVille());

			psmt.executeUpdate();

			cnx.close();

		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Retrait u) {
		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(delete, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, u.getNo_article());

			psmt.executeUpdate();

			cnx.close();

		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
	}

	@Override
	public void update(Retrait t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Retrait> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retrait selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
