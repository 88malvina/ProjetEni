package fr.eni.projet.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.dal.DAOEnchere;
import fr.eni.projet.dal.DAOFactory;


/**  
 * 
 * @author mavetyan2021
 *	
 */
public class EnchereManager {


	private DAOEnchere daoEnchere = DAOFactory.getEnchereDAO();


	// Méthodes ==========================================================================

	public  List<Enchere> selectAll() throws SQLException {
		return daoEnchere.selectAll();
	}

	public Enchere selectById(int no_utilisateur, int no_article) throws SQLException{

		Enchere resultat = daoEnchere.selectById(no_utilisateur,no_article);
		return resultat;	
	}

	public Enchere selectByNo_utilisateur(int no_utilisateur) throws SQLException{

		Enchere resultat = daoEnchere.selectByNo_utilisateur(no_utilisateur);
		return resultat;
	}
	
	public Enchere selectByNo_article(int no_article) throws SQLException{

		Enchere resultat = daoEnchere.selectByNo_article(no_article);
		return resultat;
	}
	
	public void insert(Enchere u) throws SQLException {
		daoEnchere.insert(u);
	}


	public void update(Enchere u) {
		daoEnchere.update(u);
	}


	public void delete(Enchere u) {
		daoEnchere.delete(u);
	}
}
