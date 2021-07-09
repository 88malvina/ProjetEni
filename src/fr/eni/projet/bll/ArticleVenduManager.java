/**
 * 
 */
package fr.eni.projet.bll;

import java.sql.SQLException;
import java.time.LocalDate;
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
		public List<ArticleVendu> selectAvecPseudo() throws SQLException {
			return daoArtVendu.selectAvecPseudo();
		}
		
		public List<ArticleVendu> selectVentesNonDebutees() throws SQLException {
			return daoArtVendu.selectVentesNonDebutees();
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
		
		public List<ArticleVendu> selectByCategorie(int no_categorie) throws SQLException{
			
			return daoArtVendu.selectByCategorie(no_categorie);
		}
		
		public String verifArticle (ArticleVendu a) {
			
			String messageErreur="";
			
			boolean nomArtOk=false;
			boolean descriptionOk=false;
			boolean dateDebutOk=false;
			boolean dateFinOk=false;
			boolean prixInitialOk=false;
			
			// ---------------------- verifier nom article
			
			if(a.getNomArticle().length()<3) {
				messageErreur="Le nom de l'article doit avoir au moins 3 caracteres.";
			} else if (a.getNomArticle().length()>20) {
				messageErreur="Le nom de l'article doit avoir au maximum 20 caracteres.";
			} else if (!OutilsVerification.onlyLetters(a.getNomArticle())) {
				messageErreur="Le nom de l'article ne doit pas avoir des caractères spéciaux.";
			} else {
				nomArtOk=true;
			}
			
			// ---------------------- verifier description
			
			if(a.getDescription().length()>300) {
				messageErreur="La description de l'article doit avoir au maximum 300 caracteres.";
			} else {
				descriptionOk=true;
			}
			
			// ---------------------- verifier date de début de l'enchère 
			
			if(a.getDateDebutEncheres().compareTo(LocalDate.now())<0) {
				messageErreur="Date de début invalide.";
			}else {
				dateDebutOk=true;
			}
			
			System.out.println(messageErreur);
			
			// ---------------------- verifier date de fin de l'enchère 
					
			
			if(a.getDateDebutEncheres().compareTo(a.getDateFinEncheres())>0 
					|| a.getDateDebutEncheres().compareTo(a.getDateFinEncheres())==0) {
				messageErreur="La date de fin doit être postérieure à la date de début.";
			} else {
				dateFinOk=true;
			}
			
			System.out.println(messageErreur);
			
			// ---------------------- verifier prix initial
			
			if(a.getMiseAPrix()<0) {
				messageErreur="Le prix initial doit être positif.";
			} else {
				prixInitialOk=true;
			}
			
			// ---------------------- verifier si toutes les conditions sont remplies
			
			if(nomArtOk && descriptionOk && dateDebutOk && dateFinOk && prixInitialOk) {
				messageErreur="Verificaton réussite.";
			}

			System.out.println(messageErreur);

			return messageErreur;
		}
		

}
