package fr.eni.projet.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.dal.DAOEnchere;

public class DAOEnchereJDBCImpl implements DAOEnchere{
	
	
	
	
	private String insert = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?);";

	private String delete = "DELETE FROM ENCHERES WHERE no_utilisateur=? and no_article=?";

	private String update = "UPDATE ENCHERES SET no_utilisateur=?, no_article=?, date_enchere=?, montant_enchere=? WHERE no_utilisateur=? and no_article=?;";

	private String selectAll = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES;";

	private String selectById = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_utilisateur=? and no_article=?;";
	

	@Override
	public void insert(Enchere u) throws SQLException {
		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, u.getNo_utilisateur());
			psmt.setInt(2, u.getNo_article());
			psmt.setDate(3, Date.valueOf(u.getDate_enchere()));
			psmt.setInt(4, u.getMontant_enchere());

			psmt.executeUpdate();

			cnx.close();

		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Enchere u) {
		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(delete, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, u.getNo_utilisateur());
			psmt.setInt(2, u.getNo_article());

			psmt.executeUpdate();

			cnx.close();

		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
	}

	@Override
	public void update(Enchere u) {
		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(update, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, u.getNo_utilisateur());
			psmt.setInt(2, u.getNo_article());
			psmt.setDate(3, Date.valueOf(u.getDate_enchere()));
			psmt.setInt(4, u.getMontant_enchere());
			psmt.setInt(5, u.getNo_utilisateur());
			psmt.setInt(6, u.getNo_article());

			psmt.executeUpdate();

			cnx.close();

		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
	}
	
	

	@Override
	public Enchere selectById(int no_utilisateur, int no_article) {
		 Enchere u=null;

			try (

					//Remplacement par pool de connexion via ConnectionProvider
					//Connection cnx = JdbcTools.getConnection();
					Connection cnx = ConnectionProvider.getConnection();

					PreparedStatement psmt = cnx.prepareStatement(selectById, PreparedStatement.RETURN_GENERATED_KEYS);) {

				psmt.setInt(1, no_utilisateur);
				psmt.setInt(2, no_article);
				ResultSet rs = psmt.executeQuery();

				if(rs.next())
				{
					u = new Enchere();
					u.setNo_utilisateur(rs.getInt("no_utilisateur"));
					u.setNo_article(rs.getInt("no_article"));
					u.setDate_enchere(rs.getDate("date_enchere").toLocalDate());
					u.setMontant_enchere(rs.getInt("montant_enchere"));

					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return u;
		}
	

	@Override
	public List<Enchere> selectAll() throws SQLException {
		List<Enchere> encheres = new ArrayList<Enchere>();

		try (
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();


				Statement smt = cnx.createStatement()) 
		{
			ResultSet rs = smt.executeQuery(selectAll);
			Enchere u = null;
			while(rs.next())
			{
				u = new Enchere();
				u.setNo_utilisateur(rs.getInt("no_utilisateur"));
				u.setNo_article(rs.getInt("no_article"));
				u.setDate_enchere(rs.getDate("date_enchere").toLocalDate());
				u.setMontant_enchere(rs.getInt("montant_enchere"));

				encheres.add(u);	
			}

			cnx.close();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return encheres;
	}

	@Override
	public Enchere selectById(int id) {
		return null;
	}

	

	
}
