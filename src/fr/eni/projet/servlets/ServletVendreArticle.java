package fr.eni.projet.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.CategorieManager;
import fr.eni.projet.bll.RetraitManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet dirigeant vers la jspVendreArticle 
 */
@WebServlet("/ServletVendreArticle")
public class ServletVendreArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pageActuelle", "vendreArticle");
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspVendreArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleVendu u = new ArticleVendu();
		HttpSession session = request.getSession();
		ArticleVenduManager mngArt = new ArticleVenduManager();
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");

		u.setNomArticle(request.getParameter("nom_article"));
		u.setDescription(request.getParameter("description"));
		u.setDateDebutEncheres(LocalDate.parse(request.getParameter("date_debut_encheres")));
		u.setDateFinEncheres(LocalDate.parse(request.getParameter("date_fin_encheres")));
		u.setNo_utilisateur(vendeur.getNoUtilisateur());
		u.setNo_categorie(Integer.valueOf(request.getParameter("no_categorie")));
		if(!request.getParameter("prix_initial").equals("")) {
			u.setMiseAPrix(Integer.valueOf(request.getParameter("prix_initial")));
		}
		
		
		CategorieManager mngCat = new CategorieManager();
		Categorie c = new Categorie();
		try {
			c = mngCat.selectById(u.getNo_categorie());
			u.setCategorie(c);
		} catch (SQLException e) {
			// TODO PRISCILA
			e.printStackTrace();
		}
		
		
		Retrait r = new Retrait();
		RetraitManager mngRet = new RetraitManager();
		
		r.setRue(request.getParameter("rue"));
		r.setCode_postal(request.getParameter("code_postal"));
		r.setVille(request.getParameter("ville"));
		u.setRetrait(r);

		String message_erreur = mngArt.verifArticle(u);
		message_erreur = mngRet.verifRetrait(r);

		if (message_erreur.equals("Verificaton réussite.")) {

			mngArt.insert(u);
			
			r.setNo_article(u.getNoArticle());
			mngRet.insert(r);
			
			// TODO PRISCILA changer lien 
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspPageDAccueil.jsp").forward(request, response);

		} else {
			session.setAttribute("message_erreur_article", message_erreur);
			doGet(request, response);
			//this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspVendreArticle.jsp").forward(request, response);
		}
	}

}
