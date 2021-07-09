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
import fr.eni.projet.businessException.BusinessException;

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
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
		
		String action = request.getParameter("action");
		System.out.println(action+"g");
		int no_article = Integer.parseInt(action);
		System.out.println(no_article);
		try {
			 u=mngArt.selectNomByNumero(no_article);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("nomArticle", u.getNomArticle());
		
		request.setAttribute("description", u.getDescription());
		//request.setAttribute("getLibelleCategorie", u.getLibelleCategorie());
		request.setAttribute("prixVente", u.getMiseAPrix());
		request.setAttribute("dateDebutEncheres", u.getDateDebutEncheres());
		request.setAttribute("dateFinEncheres", u.getDateFinEncheres());
		request.setAttribute("dateFinEncheres", u.getDateFinEncheres());
		
		request.setAttribute("rue",vendeur.getRue());
		request.setAttribute("cp",vendeur.getCodePostal());
		request.setAttribute("ville", vendeur.getVille());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspModifierArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
