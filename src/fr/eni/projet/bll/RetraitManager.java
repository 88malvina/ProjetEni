package fr.eni.projet.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Retrait;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.DAORetrait;

public class RetraitManager {

	// DAOUtilisateur
	private DAORetrait daoRetrait = DAOFactory.getRetraitDAO();

	// Méthodes ==========================================================================

	public Retrait selectById(int id) {

		Retrait resultat = null;
		resultat = daoRetrait.selectById(id);
		return resultat;	
	}

	public List<Retrait> selectAll(){
		List<Retrait> retraits= new ArrayList<Retrait>();
		try {
			retraits=daoRetrait.selectAll();
		} catch (SQLException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
		return retraits;
	}

	public void insert(Retrait u) {
		try {
			daoRetrait.insert(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void update(Retrait u) {
		daoRetrait.update(u);
	}


	public void delete(Retrait u) {
		daoRetrait.delete(u);
	}

	public String verifRetrait(Retrait u) {
		String messageErreur = "";
		boolean codePostalOk = false;
		boolean rueOk = false;
		boolean villeOk = false;

		// ------------------- check code postal

		if(!OutilsVerification.onlyNumbers(u.getCode_postal()) || u.getCode_postal().length()<5) {
			messageErreur="Le code postal doit avoir 5 chiffres.";
		} else {
			codePostalOk=true;
		}
		
		// ------------------- check rue
		
		if(!OutilsVerification.onlyLetters(u.getRue())) {
			messageErreur="Le nom de la rue ne doit pas avoir des caractères spéciaux.";
		} else if (u.getRue().length()>30){
			messageErreur="Le nom de la rue doit avoir au maximun 30 caractères.";
		} else {
			rueOk=true;
		}
		
		// ------------------- check ville
		
		if (u.getVille().length()>30){
			messageErreur="Le nom de la ville doit avoir au maximun 30 caractères.";
		} else if (!OutilsVerification.onlyLetters(u.getVille())) {
			messageErreur="Le nom de la ville ne doit pas avoir des caractères spéciaux.";
		} else {
			villeOk=true;
		}
		
		// ---------------------- verifier si toutes les conditions sont remplies
		
		if(rueOk && villeOk && codePostalOk) {
			messageErreur="Verificaton réussite.";
		}

		System.out.println(messageErreur);

		return messageErreur;
	}
}
