package fr.eni.projet.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
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

	public void insert(Enchere u) throws SQLException {
		daoEnchere.insert(u);
	}


	public void update(Enchere u) {
		daoEnchere.update(u);
	}


	public void delete(Enchere u) {
		daoEnchere.delete(u);
	}

	public String verifierEnchere(Enchere enchere, ArticleVendu article_a_vendre, Utilisateur payant, Utilisateur vendeur) {
		String message = "";

		if(!verifierCredit(payant, article_a_vendre)) {
			message="Vous n'avez pas assez de crédits :(";
		} else if (!verifierMontant(article_a_vendre, enchere)){
			message="La valeur doit être plus signifiante que le prix de vente.";
		} else if(estLeVendeur(vendeur, payant)) {
			message="Vous ne pouvez pas enchérir votre propre article !";
		} else if(article_a_vendre.getEnchereGagnante()==enchere) {
			message = "Vous êtes le gagneur pour l'instant !";
		} else if(!verifierDate(article_a_vendre, enchere)) { 
			message="La date de fin de l'enchère est passé.";
		} else {
			message="Vérification d'enchère réussite.";
		} 
		
		return message;
	}

	public boolean verifierMontant(ArticleVendu article_a_vendre, Enchere enchere) {
		if(article_a_vendre.getPrixVente()>=enchere.getMontant_enchere()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean estLeVendeur(Utilisateur vendeur, Utilisateur payant) {	
		if (vendeur.getNoUtilisateur()==payant.getNoUtilisateur()) {
			return true;
		} else {
			return false;
		}
	}


	public boolean verifierCredit(Utilisateur payant, ArticleVendu article_a_vendre) {
		int prix = article_a_vendre.getPrixVente();
		int credit = payant.getCredit();

		if(prix>credit) {
			return false;
		} else {
			return true;
		}
	}

	public Enchere getEnchereGagnante(ArticleVendu article_a_vendre) throws SQLException {
		Enchere gagnante=null;
		List<Enchere> encheres = daoEnchere.selectByNo_article(article_a_vendre.getNoArticle());
		int plusGdValeur=0;

		for(Enchere e : encheres) {
			if(e.getMontant_enchere()>plusGdValeur) {
				gagnante = e;
			}
		}

		return gagnante;
	}

	public boolean verifierDate(ArticleVendu article_a_vendre, Enchere offre) {
		if(article_a_vendre.getDateFinEncheres().compareTo(offre.getDate_enchere())>=0) {
			return true;
		} else {
			return false;
		}
	}

}
