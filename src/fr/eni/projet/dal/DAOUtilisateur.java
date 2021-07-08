package fr.eni.projet.dal;
import java.sql.SQLException;

import fr.eni.projet.bo.Utilisateur;

/**
 * Interface générique répresentant un DAOUtilisateur
 * @author pconchou2021
 *
 */


public interface DAOUtilisateur extends DAO<Utilisateur> {

	// A noter : ajout dal exception sera sans doute à faire
	
	public Utilisateur selectByPseudo(String pseudo) throws SQLException, DALException;

	public Utilisateur selectByEmail(String email) throws SQLException;
	
	public Utilisateur selectByTelephone(String tel) throws SQLException;
	
}
