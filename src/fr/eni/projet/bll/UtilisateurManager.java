package fr.eni.projet.bll;

import java.util.List;


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
	public static List<Utilisateur> sellectAll(){
		return daoUtilisateur.selectAll();
	}
	
	public static void insert(Utilisateur u) {
		daoUtilisateur.insert(u);
	}
	
	public static boolean controleInscription(Utilisateur u, String confirmMotDePasse) {
		
		List<Utilisateur> utilisateurs = daoUtilisateur.selectAll();
		
		// check mot de passe 
		if(!u.getMotDePasse().equals(confirmMotDePasse)) {
			System.out.println("erreur confimation mot de passe");
			return false;
		}
		
		//check pseudo
		for (Utilisateur ut : utilisateurs) {
			if(ut.getPseudo().equals(u.getPseudo())) {
				System.out.println("pseudo déjà utilisé");
				return false;
			}
		}
		
		//check e-mail
		for (Utilisateur ut : utilisateurs) {
			if(ut.getEmail().equals(u.getEmail())) {
				System.out.println("email déjà utilisé");
				return false;
			}
		}
		
		//check telephone
		if(u.getTelephone()!=null) {
			for (Utilisateur ut : utilisateurs) {
				if(ut.getTelephone().equals(u.getTelephone())) {
					System.out.println("telephone déjà utilisé");
					return false;
				}
			}
		}
			
		return true;
	}

	// =========================== Vérification formulaires et ajout utilisateur en attribut de session connecté
	
	//Methode pour verifier la connexion de l'utilisateur
	
		public String verifierIdentifiants(HttpServletRequest request) {
			
			//Attributs de classe ici//
			String resultat;
			HttpSession session = null;
			
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
					session = request.getSession();
					System.out.println("On a une session pour l'utilisateur code = " + request.getSession());
					session.setAttribute("utilisateur", u);
												
					} else {
						resultat = "Erreur de saisie de votre mot de passe";
					}
				
			} catch (NullPointerException e) {
				// Si l'utilisateur n'est pas en base on l'indique via le resultat
				resultat = "le pseudo n'est pas reconnu, réessayez ou créez un compte";
				System.out.println(resultat);
			}
				
			return resultat;
		}

}
	 