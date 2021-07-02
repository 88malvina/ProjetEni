package fr.eni.projet.dal.jdbc;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.dal.DAOCategorie;

public class DAOCategorieJDBCImpl implements DAOCategorie {
	
	String selectAll="Select * from CATEGORIES";

	@Override
	public void insert(Categorie t) {
		// TODO MALVINA définir méthode
		
	}

	
	
	@Override
	public void delete(Categorie t) {
		// TODO MALVINA définir méthode
		
	}

	@Override
	public void update(Categorie t) {
		// TODO MALVINA définir méthode
		
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
		// TODO MALVINA définir méthode
		return null;
	}

	public List<Enchere> selectEncheresByCategorie(int i) throws SQLException {
		// TODO MALVINA définir méthode
		return null;
	}

}
