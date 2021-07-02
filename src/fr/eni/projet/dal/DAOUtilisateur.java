package fr.eni.projet.dal;

import java.util.List;


import fr.eni.projet.bo.Utilisateur;

/**
 * Interface générique répresentant un DAOUtilisateur
 * @author pconchou2021
 *
 */

//Elle h�rite de dao et pr�cise les utilisateurs

public interface DAOUtilisateur extends DAO<Utilisateur> {
	
// a virer car elles sont dans l'interface DAO
	
//	//public void delete(Utilisateur t);
//	
//	public void update(Utilisateur t);
//	
//	public List<Utilisateur> selectAll();
//
//	public Utilisateur selectById(int id);
	
	public Utilisateur selectByPseudo(String pseudo);

}
