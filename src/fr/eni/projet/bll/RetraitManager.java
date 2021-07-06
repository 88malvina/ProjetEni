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
}
