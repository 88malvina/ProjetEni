package fr.eni.projet.forms;

import javax.servlet.http.HttpServletRequest;

import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Utilisateur;

/**
 * Classe pour la gestion des formulaires
 * @author ablanchet2021 t
 *
 */

public class ConnectionForm {
	
	//Attribut de classe ici//
	private String resultat;
	
	//Methode pour verifier la connexion de l'utilisateur qui utilise la requête et qui renvoie un resultat string
	
	public void verifierIdentifiants(HttpServletRequest request) {
		
		//On commence par récupérer la saisie utilisateur dans le formulaire
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		System.out.println("le pseudo saisi est : "  + pseudo);
		System.out.println("le password saisi est : " + password);
		
		//Ensuite on se connecte à la base pour attraper l'utilisateur en base via son pseudo
						
		Utilisateur u;
		
		try {
			u = new Utilisateur();
			u = UtilisateurManager.selectByPseudo(pseudo);
			
			if (u.getMotDePasse().equals(password)) {
				resultat = ("Vous êtes bien connecté, bienvenue " + u.getPseudo());
				} else {
					resultat = "Erreur de saisie de votre mot de passe";
				}
			
		} catch (NullPointerException e) {
			// Si l'utilisateur n'est pas en base on l'indique via le resultat
			resultat = "le pseudo n'est pas reconnu, réessayez ou créez un compte";
			System.out.println(resultat);
		}
				
	}

	//Getter and setters
	
	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

}
