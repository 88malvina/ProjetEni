package fr.eni.projet.bll;

import fr.eni.projet.bo.Utilisateur;

/**
 * Classe en charge de la gestion des formulaires
 * @author ablanchet2021
 *
 */


public class FormManager {

	UtilisateurManager mng = new UtilisateurManager();
	
	// TODO ANTOINE : intégrer ces méthodes dans la classe UtilisateurManager 

	//Methode pour v�rifier la connexion de l'utilisateur
	public Boolean verifConnexion (String pseudo, String password) {

		boolean connecte = false;

		//Connexion � la base pour attraper l'utilisateur en base via son pseudo

		Utilisateur u;

		try {
			u = new Utilisateur();
			u = mng.selectByPseudo(pseudo);

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

	//M�thode pour v�rifier l'existence d'un utilisateur par son pseudo
	public Boolean verifUtilisateurExiste (String pseudo) {

		boolean utilisateurExiste = false;

		//Connexion � la base pour attraper l'utilisateur en base via son pseudo

		Utilisateur u;

		try {
			u = new Utilisateur();
			u = mng.selectByPseudo(pseudo);

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
