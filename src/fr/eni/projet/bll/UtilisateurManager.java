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
	private DAOUtilisateur daoUtilisateur = DAOFactory.getUtilisateurDAO();


	// Méthodes
	public Utilisateur selectByPseudo(String pseudo) {

		Utilisateur resultat = null;
		try {
			resultat = daoUtilisateur.selectByPseudo(pseudo);
		} catch (SQLException e) {
			// TODO ANTOINE gérer exception
			e.printStackTrace();
		}
		return resultat;	
	}



	public List<Utilisateur> selectAll(){
		List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
		try {
			utilisateurs=daoUtilisateur.selectAll();
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
		return utilisateurs;
	}

	public void insert(Utilisateur u) {
		daoUtilisateur.insert(u);
	}

	public String controleInscription(Utilisateur u, String confirmation_mot_de_passe) {

		String messageErreur = "";
		boolean motDePasseOk = false;
		boolean confirmationMotDePasseOk = false;
		boolean pseudoOk = false;
		boolean nomOk = false;
		boolean prenomOk = false;
		boolean telephoneOk = false;
		boolean emailOk = false;


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
		Utilisateur recherche = null; 
		try {
			recherche = daoUtilisateur.selectByPseudo(u.getPseudo());
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}


		// teste toutes les conditions
		if(!noSpecialChars) {
			messageErreur="Le pseudo ne doit pas avoir des caractères spéciaux.";
		} else if(recherche != null) {
			messageErreur="Pseudo déjà utilisé.";
		} else if (u.getPseudo().contains(" ")) {
			messageErreur="Le pseudo ne doit pas avoir des espaces.";
		} else if (u.getPseudo().length()<5 || u.getPseudo().isEmpty()) {
			messageErreur="Le pseudo doit avoir au moins 5 caracteres.";
		} else if (u.getPseudo().length()>20) {
			messageErreur="Le pseudo doit avoir au maximum 20 caracteres.";
		} else {
			pseudoOk=true;
		}

		System.out.println(messageErreur);

		// -------------------- check nom

		if(u.getNom().length()<2) {
			messageErreur="Le nom doit avoir au moins 3 caracteres.";
		} else if (u.getNom().length()>20) {
			messageErreur="Le nom doit avoir au maximum 20 caracteres.";
		} else {
			nomOk=true;
		}


		// -------------------- check prenom

		if(u.getPrenom().length()<2) {
			messageErreur="Le prenom doit avoir au moins 3 caracteres.";
		} else if (u.getPrenom().length()>20) {
			messageErreur="Le prenom doit avoir au maximum 20 caracteres.";
		} else {
			prenomOk=true;
		}


		// -------------------- check mot de passe

		if(u.getMotDePasse().equals(u.getPrenom()+123) || u.getMotDePasse().equals(u.getNom()+123)){
			messageErreur="La securité du mot de passe est trop faible.";
		} else if(u.getMotDePasse().equals("motdepasse")) {
			messageErreur="La securité du mot de passe est trop faible.";
		} else if(u.getMotDePasse().equals("motdepasse")) {
			messageErreur="La securité du mot de passe est trop faible.";
		} else {
			motDePasseOk=true;
		}


		// -------------------- check confirmation mot de passe

		if(!u.getMotDePasse().equals(confirmation_mot_de_passe)) {
			messageErreur="Confimation de mot de passe incorrecte.";
		} else {
			confirmationMotDePasseOk=true;
		}

		System.out.println(messageErreur);

		// -------------------- check e-mail

		//check si l'e-mail est déJá utilisé

		recherche = null; 
		try {
			recherche = daoUtilisateur.selectByEmail(u.getEmail());
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
		if(recherche==null) {
			emailOk=true;
		}

		System.out.println(messageErreur);

		// ------------------- check telephone

		recherche = null; 
		try {
			recherche = daoUtilisateur.selectByTelephone(u.getTelephone());
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
		if(recherche==null) {
			telephoneOk=true;
		}

		System.out.println(messageErreur);

		// ---------------------- verifier si toutes les conditions sont remplies

		if(motDePasseOk && confirmationMotDePasseOk  && pseudoOk && nomOk && prenomOk && telephoneOk && emailOk) {
			messageErreur="Verificaton réussite.";
		}

		System.out.println(messageErreur);

		return messageErreur;

	}

}
