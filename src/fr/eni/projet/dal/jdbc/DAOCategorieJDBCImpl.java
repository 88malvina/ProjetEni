package fr.eni.projet.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.dal.DAOCategorie;

public class DAOCategorieJDBCImpl implements DAOCategorie{
	
	String selectAll="Select * from CATEGORIES";
	
	String selectEncheresByCategorie="select UTILISATEURS.pseudo,ARTICLES_VENDUS.nom_article,ENCHERES.date_enchere,ENCHERES.montant_enchere from ENCHERES inner join UTILISATEURS on UTILISATEURS.no_utilisateur=ENCHERES.no_utilisateur inner join ARTICLES_VENDUS on ARTICLES_VENDUS.no_article=ENCHERES.no_article WHERE no_categorie=?";

	@Override
	public void insert(Categorie t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Categorie t) {
		// TODO MALVINA dÃ©finir mÃ©thode
		
	}

	@Override
	public void update(Categorie t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Categorie> selectAll() throws SQLException {
		List<Categorie> list=new ArrayList<Categorie>();
		Categorie categorie=null;
	
		Connection cnx = ConnectionProvider.getConnection();
		
		Statement smt = cnx.createStatement(); 
		
			ResultSet rs = smt.executeQuery(selectAll);
		while(rs.next()) {
			categorie= new Categorie();
			categorie.setLibelle(rs.getString("libelle"));
			
			list.add(categorie);
		}
		cnx.close();
		return list;
	}

	@Override
	public Categorie selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> selectEncheresByCategorie(int i) throws SQLException {
		Enchere enchere=null;
		List<Enchere> list=new ArrayList<Enchere>();
		try (
	
		Connection cnx = ConnectionProvider.getConnection();
		
		PreparedStatement psmt = cnx.prepareStatement(selectEncheresByCategorie, PreparedStatement.RETURN_GENERATED_KEYS);) {

	psmt.setInt(1, i);
	ResultSet rs = psmt.executeQuery();
	if(rs.next()) {
		enchere=new Enchere();
		enchere.setArticle_vendu(rs.getString("nom_article"));
		enchere.setMontant_enchere(rs.getInt("montant_enchere"));
		enchere.setDate_enchere(rs.getDate("date_enchere"));
		enchere.setUtilisateur(rs.getString("pseudo"));
		
		list.add(enchere);
	}
		return list;
	}
}
}
