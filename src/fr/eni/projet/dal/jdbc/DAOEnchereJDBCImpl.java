package fr.eni.projet.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.DAO;
import fr.eni.projet.dal.DAOEnchere;

public class DAOEnchereJDBCImpl implements DAO<Enchere>{
	private String selectAll="select ARTICLES_VENDUS.nom_article, ENCHERES.montant_enchere, ENCHERES.date_enchere, UTILISATEURS.pseudo from ENCHERES \r\n"
			+ "inner join ARTICLES_VENDUS on ARTICLES_VENDUS.no_article=ENCHERES.no_article\r\n"
			+ "inner join UTILISATEURS on UTILISATEURS.no_utilisateur=ENCHERES.no_utilisateur";

	private String selectEncheresByCategorie="select ARTICLES_VENDUS.nom_article from ENCHERES join ARTICLES_VENDUS on ENCHERES.no_article=ARTICLES_VENDUS.no_article where ARTICLES_VENDUS.no_categorie=?";
	
	public DAOEnchereJDBCImpl() {}
	
	

	@Override
	public void insert(Enchere t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Enchere t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Enchere t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enchere selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public List<Enchere> selectAll() throws SQLException {

		List<Enchere> list=new ArrayList<Enchere>();
		Enchere enchere=null;
		
		//Remplacement par pool de connexion ligne close en bas du code
		//Connection cnx = JdbcTools.getConnection();
		
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
		
		Statement smt = cnx.createStatement(); 
		
		PreparedStatement psmt = cnx.prepareStatement(selectEncheresByCategorie, PreparedStatement.RETURN_GENERATED_KEYS);
		psmt.setInt(1,no_categorie);
		ResultSet rs=psmt.executeQuery();
		if(rs.next()) {
			enchere=new Enchere();
			enchere.setArticle_vendu("nom_article");
			enchere.setMontant_enchere(rs.getInt("montant_enchere"));
			enchere.setDate_enchere(rs.getDate("date_enchere"));
			enchere.setUtilisateur(rs.getString("pseudo"));
			list.add(enchere);
		}
		cnx.close();
		return  list;
		
	}
	
	@Override
	public Utilisateur selectByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}





}
