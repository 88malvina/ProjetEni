package fr.eni.projet.bll;

import java.sql.SQLException;
import java.util.ArrayList;
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

	// DAOUtilisateur
	private static DAOUtilisateur daoUtilisateur = DAOFactory.getUtilisateurDAO();


	// Méthodes
	public static Utilisateur selectByPseudo(String pseudo) {

		Utilisateur resultat = null;
		try {
			resultat = daoUtilisateur.selectByPseudo(pseudo);
		} catch (SQLException e) {
			// TODO ANTOINE gérer exception
			e.printStackTrace();
		}
		return resultat;	
	}



	public static List<Utilisateur> selectAll(){
		List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
		try {
			utilisateurs=daoUtilisateur.selectAll();
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
		return utilisateurs;
	}

	public static void insert(Utilisateur u) {
		daoUtilisateur.insert(u);
	}

	public static boolean controleInscription(Utilisateur u, String confirmMotDePasse) {
		
		// TODO PRISCILA finir méthode	
		
		List<Utilisateur> utilisateurs = null;
		try {
			utilisateurs = daoUtilisateur.selectAll();
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}

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
