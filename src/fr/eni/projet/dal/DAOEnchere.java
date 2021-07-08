package fr.eni.projet.dal;
import java.sql.SQLException;

import fr.eni.projet.bo.Enchere;

 
public interface DAOEnchere extends DAO<Enchere>{

	Enchere selectById(int no_utilisateur, int no_article) throws SQLException;
	
	
}
