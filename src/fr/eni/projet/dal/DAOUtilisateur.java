package fr.eni.projet.dal;
import java.sql.SQLException;

import fr.eni.projet.bo.Utilisateur;

/**
 * Interface générique répresentant un DAOUtilisateur
 * @author pconchou2021
 *
 */


public interface DAOUtilisateur extends DAO<Utilisateur> {

	public Utilisateur selectByPseudo(String pseudo) throws SQLException;

	public Utilisateur selectByEmail(String email) throws SQLException;
	
	public Utilisateur selectByTelephone(String tel) throws SQLException;
	
}
