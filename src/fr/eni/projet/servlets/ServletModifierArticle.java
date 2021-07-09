package fr.eni.projet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifierArticle
 */
@WebServlet("/ServletModifierArticle")
public class ServletModifierArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVendu u = new ArticleVendu();
		HttpSession session = request.getSession();
		ArticleVenduManager mngArt = new ArticleVenduManager();
		Utilisateur courant = (Utilisateur) session.getAttribute("utilisateur");
		
		
		
		String action = request.getParameter("action");
		int no_article = Integer.parseInt(action);
		System.out.println(no_article);
		u= mngArt.selectById(no_article);
		 System.out.println(u);
		request.setAttribute("nomArticle", u.getNomArticle());
		System.out.println(u.getNomArticle());
		request.setAttribute("description", u.getDescription());
		//request.setAttribute("getLibelleCategorie", u.getLibelleCategorie());
		request.setAttribute("prixVente", u.getMiseAPrix());
		request.setAttribute("dateDebutEncheres", u.getDateDebutEncheres());
		request.setAttribute("dateFinEncheres", u.getDateFinEncheres());
		request.setAttribute("dateFinEncheres", u.getDateFinEncheres());
		
		request.setAttribute("rue",courant.getRue());
		request.setAttribute("cp",courant.getCodePostal());
		request.setAttribute("ville", courant.getVille());
		
		if(u.getNo_utilisateur()==courant.getNoUtilisateur()) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspModifierArticle.jsp").forward(request, response);
		} else {
			request.setAttribute("action", no_article);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspAfficherArticle.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
