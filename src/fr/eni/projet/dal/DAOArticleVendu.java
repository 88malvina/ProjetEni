/**
 * 
 */
package fr.eni.projet.dal;

import java.util.List;
import java.sql.SQLException;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.businessException.BusinessException;

/**
 * @author pconchou2021
 *
 */
public interface DAOArticleVendu extends DAO<ArticleVendu> {
	
	public ArticleVendu selectByNom_article(String nom_article) throws SQLException;
	
	public List<ArticleVendu> selectByUtilisateur(int no_utilisateur) throws SQLException;
	
	public List<ArticleVendu> selectByCategorie(int no_categorie) throws SQLException;
	
	public List<ArticleVendu> selectAvecPseudo() throws SQLException;
	
	public List<ArticleVendu> selectVentesNonDebutees();
	
	public ArticleVendu selectNomByNumero(int id) throws BusinessException;
	
	public List<ArticleVendu> selectVentesNonDebuteesParCategorie(int no_categorie) throws BusinessException;
	
	public List<ArticleVendu> selectMesEncheresEnCours(int no_categorie) throws BusinessException;
	
	public List<ArticleVendu> selectMesEncheresTermineesByCategorie(int no_categorie) throws BusinessException;
	
	public List<ArticleVendu> selectMesEncheresEnCoursByCategorieEtNom(int no_categorie,String nom) throws BusinessException;
	
	public List<ArticleVendu> selectMesEncheresNonDebuteesByCategorieEtNom(int no_categorie,String nom) throws BusinessException;
	
	public List<ArticleVendu> selectMesEncheresTermineesByCategorieEtNom(int no_categorie,String nom) throws BusinessException;
	
	
	
}
