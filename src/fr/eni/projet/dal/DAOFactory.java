 /**
 * 
 */
package fr.eni.projet.dal;

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.jdbc.DAOEnchereJDBCImpl;
import fr.eni.projet.dal.jdbc.DAOUtilisateurJDBCImpl;

/**
 * Classe en charge de fournir un DAO
 * 
 * @author PConchou
 * 
 */
public class DAOFactory {

	public static DAO<Utilisateur> getUtilisateurDAO() {
		return new DAOUtilisateurJDBCImpl();
	}
	
	public static DAO<Enchere> getEnchereDAO() {
		return new DAOEnchereJDBCImpl();
	}
	
	

}
