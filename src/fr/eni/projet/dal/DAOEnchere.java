package fr.eni.projet.dal;
import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Enchere;

 
public interface DAOEnchere extends DAO<Enchere>{
	
	List<Enchere> selectEncheresByCategorie(int i) throws SQLException;
	
}
