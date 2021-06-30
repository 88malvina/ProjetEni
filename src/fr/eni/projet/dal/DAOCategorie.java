package fr.eni.projet.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Categorie;


public interface DAOCategorie extends DAO<Categorie>{
public void insert(Categorie t);
	
	public void delete (Categorie t);
	
	public void update(Categorie t);
	
	public List<Categorie> selectAll() throws SQLException;
	

	public Categorie selectById(int id);
}