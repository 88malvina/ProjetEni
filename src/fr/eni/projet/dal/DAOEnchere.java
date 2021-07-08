package fr.eni.projet.dal;
import java.sql.SQLException;

import fr.eni.projet.bo.Enchere;

 
public interface DAOEnchere extends DAO<Enchere>{

	public Enchere selectById(int no_utilisateur, int no_article) throws SQLException;
	
	public Enchere selectByNo_utilisateur(int no_utilisateur) throws SQLException;
	
	public Enchere selectByNo_article(int no_article) throws SQLException;
	
}
