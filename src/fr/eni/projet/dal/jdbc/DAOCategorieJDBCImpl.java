package fr.eni.projet.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.DAO;
import fr.eni.projet.dal.DAOCategorie;

public class DAOCategorieJDBCImpl implements DAO<Categorie>{
	
	String selectAll="Select * from CATEGORIES";

	@Override
	public Utilisateur selectByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Categorie t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Categorie t) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

}
