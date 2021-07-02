package fr.eni.projet.dal;

import fr.eni.projet.bo.Categorie;


public interface DAOCategorie extends DAO<Categorie>{

	public Categorie selectById(int id);
	
}