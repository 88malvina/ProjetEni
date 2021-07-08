package fr.eni.projet.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	private String update = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? WHERE no_article=?;";

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
	public void update(Retrait u) {
		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(update, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setString(1, u.getRue());
			psmt.setString(2, u.getCode_postal());
			psmt.setString(3, u.getVille());
			psmt.setInt(4, u.getNo_article());
			
			psmt.executeUpdate();

			cnx.close();

		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
	}

	@Override
	public List<Retrait> selectAll() throws SQLException {
		
		List<Retrait> retraits = new ArrayList<Retrait>();

		try (
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();


				Statement smt = cnx.createStatement()) 
		{
			ResultSet rs = smt.executeQuery(selectAll);
			Retrait u = null;
			while(rs.next())
			{
				u = new Retrait();
				u.setNo_article(rs.getInt("no_article"));
				u.setRue(rs.getString("nom_article"));
				u.setCode_postal(rs.getString("description"));
				u.setVille(rs.getString("date_debut_encheres"));

				retraits.add(u);	
			}

			cnx.close();

		} 
		catch (SQLException e) 
		{
			// TODO PRISCILA gérer exception

			e.printStackTrace();
		}
		return retraits;
	}

	@Override
	public Retrait selectById(int id) {
		Retrait u=null;

		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(selectById, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();

			if(rs.next())
			{
				u = new Retrait();
				u.setNo_article(rs.getInt("no_article"));
				u.setRue(rs.getString("rue"));
				u.setCode_postal(rs.getString("code_postal"));
				u.setVille(rs.getString("ville"));

				cnx.close();
			}
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception

			e.printStackTrace();
		}
		return u;
	}

}
