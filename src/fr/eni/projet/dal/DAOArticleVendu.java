/**
 * 
 */
package fr.eni.projet.dal;

import java.util.List;
import java.sql.SQLException;

import fr.eni.projet.bo.ArticleVendu;

/**
 * @author pconchou2021
 *
 */
public interface DAOArticleVendu extends DAO<ArticleVendu> {
	
	public ArticleVendu selectByNom_article(String nom_article) throws SQLException;
	
	public List<ArticleVendu> selectByUtilisateur(int no_utilisateur) throws SQLException;
	
}
