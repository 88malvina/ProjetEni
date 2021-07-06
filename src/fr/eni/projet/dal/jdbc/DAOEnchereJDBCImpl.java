package fr.eni.projet.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.dal.DAOEnchere;

public class DAOEnchereJDBCImpl implements DAOEnchere{
	
	private String selectAll="select ARTICLES_VENDUS.nom_article, ENCHERES.montant_enchere, ENCHERES.date_enchere, UTILISATEURS.pseudo from ENCHERES \r\n"
			+ "inner join ARTICLES_VENDUS on ARTICLES_VENDUS.no_article=ENCHERES.no_article\r\n"
			+ "inner join UTILISATEURS on UTILISATEURS.no_utilisateur=ENCHERES.no_utilisateur";

	private String selectEncheresByCategorie="select ARTICLES_VENDUS.nom_article,ENCHERES.montant_enchere,ENCHERES.date_enchere, UTILISATEURS.pseudo  from ENCHERES inner join ARTICLES_VENDUS on ENCHERES.no_article=ARTICLES_VENDUS.no_article\r\n"
			+ "inner join UTILISATEURS on ENCHERES.no_utilisateur=UTILISATEURS.no_utilisateur\r\n"
			+ "where ARTICLES_VENDUS.no_categorie=?";
	
	private String insertEncere="insert into ENCHERES (no_utilisateur,no_article,date_enchere, montant_enchere) Values (?,?,?,?);";
	
	public DAOEnchereJDBCImpl() {}
	
	

	@Override
	public void insert(Enchere t) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();

		PreparedStatement psmt = cnx.prepareStatement(insertEncere, PreparedStatement.RETURN_GENERATED_KEYS); {
			
			psmt.setString(1,t.getUtilisateur());
			psmt.setString(2,t.getArticle_vendu());
			psmt.setDate(3,t.getDate_enchere());
			psmt.setInt(4, t.getMontant_enchere());
			psmt.executeUpdate();

		}
		psmt.close();
		cnx.close();
	}

	@Override
	public void delete(Enchere t) {
		// TODO MALVINA définir méthode
		
	}

	@Override
	public void update(Enchere t) {
		// TODO MALVINA définir méthode
		
	}

	@Override
	public Enchere selectById(int id) {
		// TODO MALVINA définir méthode
		return null;
	}

	
	
	@Override
	public List<Enchere> selectAll() throws SQLException {

		List<Enchere> list=new ArrayList<Enchere>();
		Enchere enchere=null;
		
		// Remplacement par pool de connexion ligne close en bas du code
		// Connection cnx = JdbcTools.getConnection();
		Connection cnx = ConnectionProvider.getConnection();
		
		Statement smt = cnx.createStatement(); 
		
			ResultSet rs = smt.executeQuery(selectAll);
		while(rs.next()) {
			enchere= new Enchere();
			enchere.setArticle_vendu(rs.getString("nom_article"));
			enchere.setMontant_enchere(rs.getInt("montant_enchere"));
			enchere.setDate_enchere(rs.getDate("date_enchere"));
			enchere.setUtilisateur(rs.getString("pseudo"));
			list.add(enchere);
		}
		cnx.close();
		return  list;
	}  
	 
	public List<Enchere> selectEncheresByCategorie(int no_categorie) throws SQLException{
		List<Enchere> list=new ArrayList<Enchere>();
		Enchere enchere=null;
		Connection cnx = ConnectionProvider.getConnection();
		
		PreparedStatement psmt = cnx.prepareStatement(selectEncheresByCategorie);
		psmt.setInt(1,no_categorie);
		ResultSet rs=psmt.executeQuery();
		if(rs.next()) {
			enchere=new Enchere();
			enchere.setArticle_vendu(rs.getString("nom_article"));
			enchere.setMontant_enchere(rs.getInt("montant_enchere"));
			enchere.setDate_enchere(rs.getDate("date_enchere"));
			enchere.setUtilisateur(rs.getString("pseudo"));
			list.add(enchere);
		}
		psmt.close();
		cnx.close();
		return  list;
		
	}
	
}
