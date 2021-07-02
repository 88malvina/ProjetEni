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

	public static String controleInscription(Utilisateur u, String confirmation_mot_de_passe) {

		// TODO PRISCILA check nom, prenom, ville, motDePasse

		List<Utilisateur> utilisateurs = null;
		String messageErreur = "";
		boolean motDePasseOk = false;
		boolean confirmationMotDePasseOk = false;
		boolean pseudoOk = false;
		boolean nomOk = false;
		boolean prenomOk = false;
		boolean telephoneOk = false;
		boolean emailOk = false;
		boolean villeOk = false;

		// initialise la liste d'utilisateurs

		try {
			utilisateurs = daoUtilisateur.selectAll();
		} catch (SQLException e1) {
			// TODO PRISCILA gérer exception
			e1.printStackTrace();
		}


		//-------------check pseudo

		// verifie s'il y a des caracteres speciaux dans pseudo
		boolean noSpecialChars = false;
		char[] charsPseudo = u.getPseudo().toCharArray();
		for(char c : charsPseudo) {
			if(c>=48 && c<=57 || c>=65 && c<=90 || c>=97 && c<=122 ) {
				noSpecialChars=true;
			} else {
				noSpecialChars=false;
				break;
			}
		}

		// vérifie si le pseudo existe déjà
		Utilisateur recherchePseudo = null; 
		try {
			recherchePseudo = daoUtilisateur.selectByPseudo(u.getPseudo());
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}


		// teste toutes les conditions
		if(!noSpecialChars) {
			messageErreur="Le pseudo ne doit pas avoir des caractères spéciaux.";
		} else if(recherchePseudo != null) {
			messageErreur="Pseudo déjà utilisé.";
		} else if (u.getPseudo().contains(" ")) {
			messageErreur="Le pseudo ne doit pas avoir des espaces.";
		} else if (u.getPseudo().length()<5 || u.getPseudo().isEmpty()) {
			messageErreur="Le pseudo doit avoir au moins 5 caracteres";
		} else if (u.getPseudo().length()>20) {
			messageErreur="Le pseudo doit avoir au maximum 20 caracteres";
		} else {
			pseudoOk=true;
		}

		// -------------------- check confirmation mot de passe

		if(!u.getMotDePasse().equals(confirmation_mot_de_passe)) {
			messageErreur="Confimation de mot de passe incorrecte.";
		} else {
			confirmationMotDePasseOk=true;
		}


		// -------------------- check e-mail

		//TODO PRISCILA créer méthode selectByEmail

		//check e-mail

		for (Utilisateur ut : utilisateurs) {
			if(ut.getEmail().equals(u.getEmail())) {
				System.out.println("email déjà utilisé");
			}
		}

		// ------------------- check telephone

		//TODO PRISCILA créer méthode selectByTelephone

		if(u.getTelephone()!=null) {
			for (Utilisateur ut : utilisateurs) {
				if(ut.getTelephone().equals(u.getTelephone())) {
					System.out.println("telephone déjà utilisé");
				}
			}	
		}

		// verifier si toutes les conditions sont remplies
		if(motDePasseOk && confirmationMotDePasseOk  && pseudoOk && nomOk && prenomOk && telephoneOk && emailOk && villeOk) {
			messageErreur="Verificaton réussite.";
		}

		return messageErreur;

	}

}
