package fr.eni.projet.bll;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.DAOUtilisateur;

/**
 * 
 * @author ablanchet2021
 *
 */

public class UtilisateurManager {
	
	private static DAOUtilisateur daoUtilisateur = (DAOUtilisateur) DAOFactory.getUtilisateurDAO();
	
	public static Utilisateur selectByPseudo(String pseudo) {
		
		return daoUtilisateur.selectByPseudo(pseudo);
		
	}
	
}
	 