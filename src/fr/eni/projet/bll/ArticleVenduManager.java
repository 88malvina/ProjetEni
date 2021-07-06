/**
 * 
 */
package fr.eni.projet.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.dal.DAOArticleVendu;
import fr.eni.projet.dal.DAOFactory;

/**
 * @author pconchou2021
 *
 */
public class ArticleVenduManager {
	// DAOUtilisateur
	private DAOArticleVendu daoArtVendu = DAOFactory.getArticleVenduDAO();

	// Méthodes ==========================================================================
	
		public ArticleVendu selectById(int id) {

			ArticleVendu resultat = null;
			resultat = daoArtVendu.selectById(id);
			return resultat;	
		}

		public List<ArticleVendu> selectAll(){
			List<ArticleVendu> artVendus= new ArrayList<ArticleVendu>();
			try {
				artVendus=daoArtVendu.selectAll();
			} catch (SQLException e) {
				// TODO PRISCILA gérer exception
				e.printStackTrace();
			}
			return artVendus;
		}
		
		public void insert(ArticleVendu u) {
			try {
				daoArtVendu.insert(u);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public void update(ArticleVendu u) {
			daoArtVendu.update(u);
		}
		
		
		public void delete(ArticleVendu u) {
			daoArtVendu.delete(u);
		}
	
		public ArticleVendu selectByNom_article(String nom_article) {
			
			try {
				daoArtVendu.selectByNom_article(nom_article);
			} catch (SQLException e) {
				// TODO PRISCILA gérer exception
				e.printStackTrace();
			}
			return null;
		}
		
		public List<ArticleVendu> selectByUtilisateur(int no_utilisateur) {
			List<ArticleVendu> artVendus = null;
			try {
				artVendus = daoArtVendu.selectByUtilisateur(no_utilisateur);
			} catch (SQLException e) {
				// TODO PRISCILA gérer exception
				e.printStackTrace();
			}
			return artVendus;
		}
		
		public String verifArticle (ArticleVendu a) {
			
			String messageErreur="";
			
			boolean nomArtOk=false;
//			boolean descriptionOk=false;
//			boolean dateDebutOk=false;
//			boolean dateFinOk=false;
//			boolean prixInitialOk=false;
			
			// ---------------------- verifier nom article
			
			if(a.getNomArticle().length()<3) {
				messageErreur="Le nom de l'article doit avoir au moins 3 caracteres.";
			} else if (a.getNomArticle().length()>20) {
				messageErreur="Le nom de l'article doit avoir au maximum 20 caracteres.";
			} else if (!OutilsVerification.noSpecialChars(a.getNomArticle())) {
				messageErreur="Le nom de l'article ne doit pas avoir des caractères spéciaux.";
			} else {
				nomArtOk=true;
			}
			
			// ---------------------- verifier si toutes les conditions sont remplies
			
			// && descriptionOk  && dateDebutOk && dateFinOk && prixInitialOk
			
			if(nomArtOk ) {
				messageErreur="Verificaton réussite.";
			}

			System.out.println(messageErreur);

			return messageErreur;
		}
}
