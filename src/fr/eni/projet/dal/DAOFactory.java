/**
 * 
 */
package fr.eni.projet.dal;

import fr.eni.projet.bo.Utilisateur;

/**
 * Classe en charge de
 * 
 * @author Julien version DAOFactory.java v1.0
 * @date 23 juin 2021
 */
public class DAOFactory {

	public static DAO<Utilisateur> getUtilisateurDAO() {
		return new DAOUtilisateurJDBCImpl();
	}

}
