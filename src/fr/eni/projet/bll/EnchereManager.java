package fr.eni.projet.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.dal.DAOEnchere;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.DAOUtilisateur;


/**
 * 
 * @author mavetyan2021
 *	
 */
public class EnchereManager {
	private static DAOEnchere daoEnchere = (DAOEnchere) DAOFactory.getEnchereDAO();
	
	public static List<Enchere> selectAll() throws SQLException {
		return daoEnchere.selectAll();
		
	} 
	
}
