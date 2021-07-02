package fr.eni.projet.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;


public interface DAOCategorie extends DAO<Categorie>{

	public List<Enchere> selectEncheresByCategorie(int i) throws SQLException;
	
}