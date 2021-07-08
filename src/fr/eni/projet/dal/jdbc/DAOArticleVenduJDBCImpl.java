/**
 * 
 */
package fr.eni.projet.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.dal.DAOArticleVendu;

/**
 * @author pconchou2021
 *
 */
public class DAOArticleVenduJDBCImpl implements DAOArticleVendu {

	private String insert = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?);";

	private String delete = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";

	private String update = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=? WHERE no_article=?;";

	private String selectAll = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS;";

	private String selectById = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article=?;";

	private String selectByNom_article = "select * from ARTICLES_VENDUS WHERE nom_article=? ";

	private String selectByUtilisateur = "select * from ARTICLES_VENDUS WHERE no_utilisateur=? ";
	
	private String selectByCategorie = "select * from ARTICLES_VENDUS WHERE no_categorie=?";
	
	private String selectAvecPseudo = "select nom_article, prix_vente, date_fin_encheres, pseudo from ARTICLES_VENDUS join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur";
	
	private String selectVentesNonDebutees = "select nom_article, prix_vente, date_fin_encheres, pseudo from ARTICLES_VENDUS join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur where date_debut_encheres > GETDATE()";

	public List<ArticleVendu> selectVentesNonDebutees(){
		List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();
		try (
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();


				Statement smt = cnx.createStatement()) 
		{
			ResultSet rs = smt.executeQuery(selectVentesNonDebutees);
			ArticleVendu u = null;
			while(rs.next())
			{
				u = new ArticleVendu();
				u.setNomArticle(rs.getString("nom_article"));
				u.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				u.setPrixVente(rs.getInt("prix_vente"));
				u.setPseudo(rs.getString("pseudo"));

				articlesVendus.add(u);	
			}

			cnx.close();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return articlesVendus;
		
	}
	
	public List<ArticleVendu> selectAvecPseudo() {
		List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();
		try (
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();


				Statement smt = cnx.createStatement()) 
		{
			ResultSet rs = smt.executeQuery(selectAvecPseudo);
			ArticleVendu u = null;
			while(rs.next())
			{
				u = new ArticleVendu();
				u.setNomArticle(rs.getString("nom_article"));
				u.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				u.setPrixVente(rs.getInt("prix_vente"));
				u.setPseudo(rs.getString("pseudo"));

				articlesVendus.add(u);	
			}

			cnx.close();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return articlesVendus;
		
	}


	@Override
	public void insert(ArticleVendu u) {

		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setString(1, u.getNomArticle());
			psmt.setString(2, u.getDescription());
			psmt.setDate(3, Date.valueOf(u.getDateDebutEncheres()));
			psmt.setDate(4, Date.valueOf(u.getDateFinEncheres()));
			psmt.setInt(5, u.getMiseAPrix());
			psmt.setInt(6, u.getPrixVente());
			psmt.setInt(7, u.getNo_utilisateur());
			psmt.setInt(8, u.getNo_categorie());

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();
			if (rs.next()) {
				u.setNoArticle(rs.getInt(1));
			}

			cnx.close();

		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
	}

	@Override
	public void delete(ArticleVendu u) {
		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(delete, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, u.getNoArticle());

			psmt.executeUpdate();

			cnx.close();

		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
	}

	@Override
	public void update(ArticleVendu u) {
		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(update, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setString(1, u.getNomArticle());
			psmt.setString(2, u.getDescription());
			psmt.setDate(3, Date.valueOf(u.getDateDebutEncheres()));
			psmt.setDate(4, Date.valueOf(u.getDateFinEncheres()));
			psmt.setInt(5, u.getMiseAPrix());
			psmt.setInt(6, u.getPrixVente());
			psmt.setInt(7, u.getNo_utilisateur());
			psmt.setInt(8, u.getNo_categorie());
			psmt.setInt(9, u.getNoArticle());

			psmt.executeUpdate();

			cnx.close();

		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}

	}

	@Override
	public List<ArticleVendu> selectAll() throws SQLException {
		
		List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();

		try (
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();


				Statement smt = cnx.createStatement()) 
		{
			ResultSet rs = smt.executeQuery(selectAll);
			ArticleVendu u = null;
			while(rs.next())
			{
				u = new ArticleVendu();
				//en utilisant cette méthode j'ai eu une erreur comme quoi no_article n'est pas valide
				//pour l'instant je mets ceci dans le commentaire
				u.setNoArticle(rs.getInt("no_article"));
				u.setNomArticle(rs.getString("nom_article"));
				u.setDescription(rs.getString("description"));
				u.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				u.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				u.setMiseAPrix(rs.getInt("prix_initial"));
				u.setPrixVente(rs.getInt("prix_vente"));
				u.setNo_utilisateur(rs.getInt("no_utilisateur"));
				u.setNo_categorie(rs.getInt("no_categorie"));

				articlesVendus.add(u);	
			}

			cnx.close();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			
		}
		return articlesVendus;
	}

	@Override
	public ArticleVendu selectById(int id) {
		ArticleVendu u = null;

		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(selectById, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();

			if(rs.next())
			{
				u = new ArticleVendu();
				u.setNoArticle(rs.getInt("no_article"));
				u.setNomArticle(rs.getString("nom_article"));
				u.setDescription(rs.getString("description"));
				u.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				u.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				u.setMiseAPrix(rs.getInt("prix_initial"));
				u.setPrixVente(rs.getInt("prix_vente"));
				u.setNo_utilisateur(rs.getInt("no_utilisateur"));
				u.setNo_categorie(rs.getInt("no_categorie"));

				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public ArticleVendu selectByNom_article(String nom_article) throws SQLException {
		ArticleVendu u = null;

		try (

				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(selectByNom_article, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setString(1, nom_article);
			ResultSet rs = psmt.executeQuery();

			if(rs.next())
			{
				u = new ArticleVendu();
				u.setNoArticle(rs.getInt("no_article"));
				u.setNomArticle(rs.getString("nom_article"));
				u.setDescription(rs.getString("description"));
				u.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				u.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				u.setMiseAPrix(rs.getInt("prix_initial"));
				u.setPrixVente(rs.getInt("prix_vente"));
				u.setNo_utilisateur(rs.getInt("no_utilisateur"));
				u.setNo_categorie(rs.getInt("no_categorie"));

				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public List<ArticleVendu> selectByUtilisateur(int no_utilisateur) throws SQLException {
		List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();

		try (
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement psmt = cnx.prepareStatement(selectByUtilisateur, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, no_utilisateur);
			ResultSet rs = psmt.executeQuery(); 
		
			ArticleVendu u = null;
			while(rs.next())
			{
				u = new ArticleVendu();
				u.setNoArticle(rs.getInt("no_article"));
				u.setNomArticle(rs.getString("nom_article"));
				u.setDescription(rs.getString("description"));
				u.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				u.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				u.setMiseAPrix(rs.getInt("prix_initial"));
				u.setPrixVente(rs.getInt("prix_vente"));
				u.setNo_utilisateur(rs.getInt("no_utilisateur"));
				u.setNo_categorie(rs.getInt("no_categorie"));

				articlesVendus.add(u);	
			}

			cnx.close();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return articlesVendus;
	}
	
	public List<ArticleVendu> selectByCategorie(int no_categorie) throws SQLException {
		List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();
		
		ArticleVendu article=null;
		Connection cnx = ConnectionProvider.getConnection();
		  
		PreparedStatement psmt = cnx.prepareStatement(selectByCategorie);
		psmt.setInt(1,no_categorie);
		ResultSet rs=psmt.executeQuery();
		if(rs.next()) {
			article=new ArticleVendu();
			article.setNoArticle(rs.getInt("no_article"));
			article.setNomArticle(rs.getString("nom_article"));
			article.setDescription(rs.getString("description"));
			article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
			article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
			article.setMiseAPrix(rs.getInt("prix_initial"));
			article.setPrixVente(rs.getInt("prix_vente"));
			article.setNo_utilisateur(rs.getInt("no_utilisateur"));
			article.setNo_categorie(rs.getInt("no_categorie"));
			
			articlesVendus.add(article);	
		}
		psmt.close();
		cnx.close();
		return  articlesVendus;
	}

}
