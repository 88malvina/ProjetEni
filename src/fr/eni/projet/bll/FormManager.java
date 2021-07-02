package fr.eni.projet.bll;

import fr.eni.projet.bo.Utilisateur;

/**
 * Classe en charge de la gestion des formulaires
 * @author ablanchet2021
 *
 */


public class FormManager {

	//Methode pour vérifier la connexion de l'utilisateur
	public Boolean verifConnexion (String pseudo, String password) {

		boolean connecte = false;

		//Connexion à la base pour attraper l'utilisateur en base via son pseudo

		Utilisateur u;

		try {
			u = new Utilisateur();
			u = UtilisateurManager.selectByPseudo(pseudo);

			if (u.getMotDePasse().equals(password)) {
				connecte = true;

			} else {
				connecte = false;
			}

		} catch (NullPointerException e) {
			connecte = false;

		}

		return connecte;
	}
	
	//Méthode pour vérifier l'existence d'un utilisateur par son pseudo
	public Boolean verifUtilisateurExiste (String pseudo) {

		boolean utilisateurExiste = false;

		//Connexion à la base pour attraper l'utilisateur en base via son pseudo

		Utilisateur u;

		try {
			u = new Utilisateur();
			u = UtilisateurManager.selectByPseudo(pseudo);

			if (u.getPseudo()!=null) {
				utilisateurExiste = true;

			} else {
				utilisateurExiste = false;
			}

		} catch (NullPointerException e) {
			utilisateurExiste = false;

		}

		return utilisateurExiste;
	}
	
	
}
