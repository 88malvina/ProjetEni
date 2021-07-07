package fr.eni.projet.bo;

import java.time.LocalDate;
public class ArticleVendu { 
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private int no_utilisateur;
	private int no_categorie;
	private String pseudo;
	
	

	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, int no_utilisateur, int no_categorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}

	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int no_utilisateur, int no_categorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}

	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int prixVente, int no_utilisateur, int no_categorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixVente = prixVente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}


	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int no_utilisateur,
			int no_categorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}
	public ArticleVendu( String nomArticle, LocalDate dateFinEncheres, int miseAPrix, String pseudo)
	{
		this.nomArticle = nomArticle;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.pseudo =pseudo;
		
	}
	//---------------- constructeurs faits par Malvina (sans no_utilisateur et no_categorie)

//	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
//			LocalDate dateFinEncheres, int miseAPrix, int prixVente, String etatVente) {
//		super();
//		this.noArticle = noArticle;
//		this.nomArticle = nomArticle;
//		this.description = description;
//		this.dateDebutEncheres = dateDebutEncheres;
//		this.dateFinEncheres = dateFinEncheres;
//		this.miseAPrix = miseAPrix;
//		this.prixVente = prixVente;
//		this.etatVente = etatVente;
//	}
//
//	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
//			int miseAPrix, int prixVente, String etatVente) {
//		super();
//		this.nomArticle = nomArticle;
//		this.description = description;
//		this.dateDebutEncheres = dateDebutEncheres;
//		this.dateFinEncheres = dateFinEncheres;
//		this.miseAPrix = miseAPrix;
//		this.prixVente = prixVente;
//		this.etatVente = etatVente;
//	}

	public ArticleVendu() {
	}

	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo= pseudo;
	}
	
	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public int getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente;
	}
	
	
	
}
