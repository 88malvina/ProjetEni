package fr.eni.projet.bll;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.DAOUtilisateur;

/**
 * 
 * @author Priscila
 *
 */

public class UtilisateurManager {

	// DAOUtilisateur
	private DAOUtilisateur daoUtilisateur = DAOFactory.getUtilisateurDAO();

	// Méthodes ==========================================================================

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

	// ==========================================================================

	public Utilisateur selectByEmail(String email) {

		Utilisateur resultat = null;
		try {
			resultat = daoUtilisateur.selectByEmail(email);
		} catch (SQLException e) {
			// TODO ANTOINE gérer exception
			e.printStackTrace();
		}
		return resultat;	
	}

	// ==========================================================================

	public Utilisateur selectByTelephone(String telephone) {

		Utilisateur resultat = null;
		try {
			resultat = daoUtilisateur.selectByTelephone(telephone);
		} catch (SQLException e) {
			// TODO ANTOINE gérer exception
			e.printStackTrace();
		}
		return resultat;	
	}

	//  ==========================================================================

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

	//  ==========================================================================

	public void insert(Utilisateur u) {
		try {
			daoUtilisateur.insert(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//  ==========================================================================

	public void update(Utilisateur u) {
		daoUtilisateur.update(u);
	}

	//  ==========================================================================

	public void delete(Utilisateur u) {
		daoUtilisateur.delete(u);
	}

	//  ==========================================================================

	public Utilisateur selectById(int id) {
		Utilisateur resultat = null;
		resultat = daoUtilisateur.selectById(id);
		return resultat;	
	}

	//  ==========================================================================

	public Boolean verifConnexion (String pseudo, String password) {

		boolean connecte = false;

		//Connexion à la base pour attraper l'utilisateur en base via son pseudo

		Utilisateur u;

		try {
			u = new Utilisateur();
			u = this.selectByPseudo(pseudo);

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


	//  ==========================================================================

	//Méthode pour vérifier l'existence d'un utilisateur par son pseudo
	public Boolean verifUtilisateurExiste (String pseudo) {

		boolean utilisateurExiste = false;

		//Connexion à la base pour attraper l'utilisateur en base via son pseudo

		Utilisateur u;

		try {
			u = new Utilisateur();
			u = this.selectByPseudo(pseudo);

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

	//  ==========================================================================

	//Methode pour vérifier que la validation mot de passe de suppression de compte est ok

	public Boolean controleMotDePasse (String motDePasseInitial, String motDePasse, String confirmationMotDePasse) {

		Boolean retourBooleanApresControle = false;

		if ((motDePasseInitial.equals(motDePasse)) && (motDePasse.equals(confirmationMotDePasse))) {
			retourBooleanApresControle = true;
		}

		return retourBooleanApresControle;
	}

	//  ==========================================================================

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


		// vérifie si le pseudo existe déjà
		Utilisateur recherche = null; 
		try {
			recherche = daoUtilisateur.selectByPseudo(u.getPseudo());
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}

		// teste toutes les conditions
		if(!OutilsVerification.noSpecialChars(u.getPseudo())) {
			messageErreur="Le pseudo ne doit pas avoir des caractères spéciaux.";
		} else if(recherche != null) {
			messageErreur="Pseudo déjà utilisé.";
		} else if (u.getPseudo().contains(" ")) {
			messageErreur="Le pseudo ne doit pas avoir des espaces.";
		} else if (u.getPseudo().length()<4 || u.getPseudo().isEmpty()) {
			messageErreur="Le pseudo doit avoir au moins 4 caracteres.";
		} else if (u.getPseudo().length()>20) {
			messageErreur="Le pseudo doit avoir au maximum 20 caracteres.";
		} else {
			pseudoOk=true;
		}

		System.out.println(messageErreur);

		// -------------------- check nom

		if(u.getNom().length()<3) {
			messageErreur="Le nom doit avoir au moins 3 caracteres.";
		} else if (u.getNom().length()>20) {
			messageErreur="Le nom doit avoir au maximum 20 caracteres.";
		} else if (!OutilsVerification.noSpecialChars(u.getNom())) {
			messageErreur="Le nom ne doit pas avoir des caractères spéciaux.";
		} else {
			nomOk=true;
		}

		// -------------------- check prenom

		if(u.getPrenom().length()<3) {
			messageErreur="Le prenom doit avoir au moins 3 caracteres.";
		} else if (u.getPrenom().length()>20) {
			messageErreur="Le prenom doit avoir au maximum 20 caracteres.";
		} else if (!OutilsVerification.noSpecialChars(u.getPrenom())) {
			messageErreur="Le prenom ne doit pas avoir des caractères spéciaux.";
		} else {
			prenomOk=true;
		}

		// -------------------- check mot de passe

		if(u.getMotDePasse().equals(u.getPrenom()+123) || u.getMotDePasse().equals(u.getNom()+123)){
			messageErreur="La securité du mot de passe est trop faible.";
			// TODO ANTOINE : corriger la méthode copié
		} else if(u.getMotDePasse().equals(u.getPseudo()+123)) {
			messageErreur="La securité du mot de passe est trop faible.";
		} else if(u.getMotDePasse().equals("motdepasse")) {
			messageErreur="La securité du mot de passe est trop faible.";
		} else if (u.getMotDePasse().length()<8 || u.getPseudo().isEmpty()) {
			messageErreur="Le mot de passe doit avoir au moins 8 caracteres.";
		} else if (u.getMotDePasse().length()>20) {
			messageErreur="Le mot de passe doit avoir au maximum 20 caracteres.";
		} else if (OutilsVerification.noSpecialChars(u.getMotDePasse())) {
			messageErreur="Le mot de passe doit avoir au moins 1 caractere special.";
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

		// check si l'e-mail est déjà utilisé

		recherche = null; 
		try {
			recherche = daoUtilisateur.selectByEmail(u.getEmail());
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
		if(recherche==null) {
			emailOk=true;
		} else {
			messageErreur="Email déjà utilisé.";
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

	//  ==========================================================================

	// C'est la même méthode que pour l'inscription à la différence que l'on prend aussi en paramètre
	// l'utilisateur en attribut de session avant les mises à jours
	// Pour pouvoir gérer le fait que lors d'une mise à jour de profil, on a le droit de garder son pseudo ou email déjà existant
	// (même si celui-ci sera déjà en BDD)


	/**
	 * 
	 * @param u l'utilisateur avec les infos que l'on veut mettre à jour (après la saisie de formulaire)
	 * @param old l'utilisateur initial (avant saisie de formulaire)
	 * @param confirmation_mot_de_passe
	 * @return
	 */
	public String ControleModifProfil(Utilisateur u, Utilisateur old, String confirmation_mot_de_passe) {

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

		// vérifie si le pseudo existe déjà ou si c'est le pseudo de l'utilisateur old
		Utilisateur recherche = null; 

		try {
			recherche = daoUtilisateur.selectByPseudo(u.getPseudo());
			System.out.println(old.getPseudo());
			System.out.println(u.getPseudo());
			if (old.getPseudo().equals(u.getPseudo())) {
				recherche = null;
			}

		} catch (SQLException e) {
			// TODO ANTOINE gérer exception
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

		recherche = null; 
		try {
			recherche = daoUtilisateur.selectByEmail(u.getEmail());
			if (old.getEmail().equals(u.getEmail())) {
				recherche = null;
			}

		} catch (SQLException e) {
			// TODO ANTOINE gérer exception
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
			if(old.getTelephone().equals(u.getTelephone())) {
				recherche = null;
			}
		} catch (SQLException e) {
			// TODO ANTOINE gérer exception
			e.printStackTrace();
		}
		if(recherche==null) {
			telephoneOk=true;
		}

		System.out.println(messageErreur);

		// ---------------------- verifier si toutes les conditions sont remplies
		// Petite modification versus l'inscription : l'utilisateur a le droit de reprendre le même pseudo ou email. Celui ci est
		// déjà dans la base, mais c'est bon quand même si c'est le sien

		if(
				motDePasseOk 
				&& confirmationMotDePasseOk
				&& pseudoOk
				&& nomOk 
				&& prenomOk 
				&& telephoneOk 
				&& emailOk
				) 
		{
			messageErreur="Verificaton réussite.";
		}

		System.out.println(messageErreur);

		return messageErreur;

	}



}
