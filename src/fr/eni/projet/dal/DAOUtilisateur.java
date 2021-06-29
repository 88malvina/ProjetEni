package fr.eni.projet.dal;

import java.util.List;


import fr.eni.projet.bo.Utilisateur;

/**
 * Interface générique répresentant un DAOUtilisateur
 * @author pconchou2021
 *
 */

public interface DAOUtilisateur extends DAO<Utilisateur> {

public void insert(Utilisateur t);
	
	public void delete(Utilisateur t);
	
	public void update(Utilisateur t);
	
	public List<Utilisateur> selectAll();

	public Utilisateur selectById(int id);

}
