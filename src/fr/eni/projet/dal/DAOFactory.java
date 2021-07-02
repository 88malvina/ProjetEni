 /**
 * 
 */
package fr.eni.projet.dal;

import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.jdbc.DAOCategorieJDBCImpl;
import fr.eni.projet.dal.jdbc.DAOEnchereJDBCImpl;
import fr.eni.projet.dal.jdbc.DAOUtilisateurJDBCImpl;

/**
 * Classe en charge de fournir un DAO
 * 
 * @author PConchou
 * 
 */

//Doit retourner DAOUtilisateur et pas le type (comme c'est fait pour utilisateur)

public class DAOFactory {

	public static DAOUtilisateur getUtilisateurDAO() {
		return new DAOUtilisateurJDBCImpl();
	}
	
	public static DAOEnchere getEnchereDAO() {
		return new DAOEnchereJDBCImpl();
	}
	
	public static DAOCategorie getCategorieDAO() {
		return new DAOCategorieJDBCImpl();
	}
	
	

}
