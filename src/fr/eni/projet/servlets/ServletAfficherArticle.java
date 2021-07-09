package fr.eni.projet.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.CategorieManager;
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bll.RetraitManager;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class ServletAfficherArticle
 */
@WebServlet("/ServletAfficherArticle")
public class ServletAfficherArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pageActuelle", "afficherArticle");
		
		String action = request.getParameter("action");
		System.out.println(action);
		int no_article = Integer.parseInt(action);
		
		HttpSession session = request.getSession();
		session.setAttribute("no_article", action);
		
		ArticleVenduManager mngArt = new ArticleVenduManager();
		ArticleVendu article = mngArt.selectById(no_article);
		System.out.println(article.getNomArticle());
		
		CategorieManager mngCat = new CategorieManager();
		Categorie c = new Categorie();
		try {
			c = mngCat.selectById(article.getNo_categorie());
			article.setCategorie(c);
		} catch (SQLException e) {
			// TODO PRISCILA
			e.printStackTrace();
		}
		
		int mise_a_prix = article.getMiseAPrix();
		int prix_vente = article.getPrixVente();
		if(prix_vente<mise_a_prix) {
			article.setPrixVente(mise_a_prix);
		}
		
		
		RetraitManager mngRet = new RetraitManager();
		Retrait retrait = mngRet.selectById(no_article);
		article.setRetrait(retrait);
		
		UtilisateurManager mngUti = new UtilisateurManager();
		Utilisateur vendeur = mngUti.selectById(article.getNo_utilisateur());
		article.setVendeur(vendeur);
		
		EnchereManager mngEnc = new EnchereManager();
		Enchere enchereGagnante = null;
		try {
			enchereGagnante = mngEnc.getEnchereGagnante(article);
		} catch (SQLException e) {
			// TODO PRISCILA
			e.printStackTrace();
		}
		
		request.setAttribute("enchereGagnante", enchereGagnante);
		request.setAttribute("article",article);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspAfficherArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
