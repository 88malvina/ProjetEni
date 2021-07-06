package fr.eni.projet.bo;

import java.sql.Date; 

public class Enchere {
	
	private Utilisateur utilisateur;
	private ArticleVendu article_vendu;
	private Date date_enchere;
	private int montant_enchere;
	
	
	public Enchere(Utilisateur utilisateur, ArticleVendu article_vendu, Date date_enchere, int montant_enchere) {
		this.utilisateur = utilisateur;
		this.article_vendu = article_vendu;
		this.date_enchere=date_enchere;
		this.montant_enchere=montant_enchere;
	}

	public Enchere( ArticleVendu article_vendu,int montant_enchere, Date date_enchere, Utilisateur utilisateur) {
		this.article_vendu = article_vendu;
		this.montant_enchere=montant_enchere;
		this.date_enchere=date_enchere;
		this.utilisateur = utilisateur;
	}
	
	public Enchere() {
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public ArticleVendu getArticle_vendu() {
		return article_vendu;
	}


	public void setArticle_vendu(ArticleVendu article_vendu) {
		this.article_vendu = article_vendu;
	}


	public Date getDate_enchere() {
		return date_enchere;
	}


	public void setDate_enchere(Date date) {
		this.date_enchere = date;
	}


	public int getMontant_enchere() {
		return montant_enchere;
	}


	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}


	@Override
	public String toString() {
		return "Enchere [utilisateur=" + utilisateur.getPseudo() + ", article_vendu=" + article_vendu + ", date_enchere="
				+ date_enchere + ", montant_enchere=" + montant_enchere + "]";
	}
	
	
	
	
}
