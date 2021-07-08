package fr.eni.projet.dal;
import java.sql.SQLException;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.businessException.BusinessException;

/**
 * Interface générique répresentant un DAOUtilisateur
 * @author pconchou2021
 *
 */


public interface DAOUtilisateur extends DAO<Utilisateur> {

	// A noter : ajout dal exception sera sans doute à faire
	
	public Utilisateur selectByPseudo(String pseudo) throws SQLException, BusinessException;

	public Utilisateur selectByEmail(String email) throws SQLException;
	
	public Utilisateur selectByTelephone(String tel) throws SQLException;
	
}
