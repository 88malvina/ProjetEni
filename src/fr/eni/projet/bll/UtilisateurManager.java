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

}
	 