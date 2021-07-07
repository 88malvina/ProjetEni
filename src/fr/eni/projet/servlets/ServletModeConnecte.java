package fr.eni.projet.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bo.ArticleVendu;

/**
 * Servlet implementation class ServletModeConnecte
 */
@WebServlet("/ServletModeConnecte")
public class ServletModeConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVenduManager manager = new ArticleVenduManager();
		
		List<ArticleVendu> list = new ArrayList<ArticleVendu>();
		try {
			list = manager.selectAvecPseudo();
			request.setAttribute("encheres", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("pageActuelle", "accueil");
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jspFiles/jspPageDAccueilModeConnecté.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate date=LocalDate.now();
		ArticleVenduManager manager = new ArticleVenduManager();
		List<ArticleVendu> list=manager.selectAll();
		
		List<ArticleVendu> list_en_cours = new ArrayList<ArticleVendu>();
		
		List<ArticleVendu> list_remportés = new ArrayList<ArticleVendu>();
		
		for(ArticleVendu article: list) {
			if(article.getDateFinEncheres().isAfter(date)) {
				list_remportés.add(article);
			}
			if(article.getDateFinEncheres().isBefore(date)) {
				list_en_cours.add(article);
			}
			
		}
		
		if(request.getParameter("rechercher")!=null) {
			
			if(request.getParameter("radio_button")!=null) {
				System.out.println(request.getParameter("encheres"));
				switch (request.getParameter("encheres")) {
				case "encheres_ouvertes": request.setAttribute("encheres_afficher", list); 
				System.out.println(list);
				case "encheres_en_cours": request.setAttribute("encheres_afficher", list_en_cours); 
				case "remportés": request.setAttribute("encheres_afficher", list_remportés); 
					break;

				default:
					break;
				}
			}
			
		}
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jspFiles/jspPageDAccueilModeConnecté.jsp");
		rd.forward(request, response);
	}

}
