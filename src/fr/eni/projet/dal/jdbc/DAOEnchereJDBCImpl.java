package fr.eni.projet.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.eni.projet.dal.DALException;
import fr.eni.projet.dal.DAO;
import fr.eni.projet.dal.DAOEnchere;
import fr.eni.projet.dal.jdbc.JdbcTools;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;

public class DAOEnchereJDBCImpl<T> implements DAOEnchere{
	private String selectAll="select ARTICLES_VENDUS.nom_article, ENCHERES.montant_enchere, ENCHERES.date_enchere, UTILISATEURS.pseudo from ENCHERES \r\n"
			+ "inner join ARTICLES_VENDUS on ARTICLES_VENDUS.no_article=ENCHERES.no_article\r\n"
			+ "inner join UTILISATEURS on UTILISATEURS.no_utilisateur=ENCHERES.no_utilisateur";

	
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
	public List<T> selectAll() throws SQLException {
		List<Enchere> list=new ArrayList<Enchere>();
		Enchere enchere=null;
		Connection cnx = JdbcTools.getConnection();
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
		return (List<T>) list;
	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void insert(Object t) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void delete(Object t) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(Object t) {
		// TODO Auto-generated method stub
		
	}

}
