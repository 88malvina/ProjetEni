package fr.eni.projet.bll;

import java.sql.SQLException;


import java.util.List;

import fr.eni.projet.bo.Categorie;
import fr.eni.projet.dal.DAOCategorie;
import fr.eni.projet.dal.DAOFactory;
 
public class CategorieManager {
	private DAOCategorie daoCategorie = DAOFactory.getCategorieDAO();
	
	public List<Categorie> selectAll() throws SQLException {
		return daoCategorie.selectAll();
	} 
	
}

