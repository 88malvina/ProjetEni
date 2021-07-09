package fr.eni.projet.servlets;

import java.io.IOException;

import java.sql.SQLException;
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

/**@author mavetyan2021
 * Servlet implementation class ServletPageDAccueil
 */
@WebServlet("/encheres/ServletPageDAccueil")
public class ServletPageDAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupérer valeurs table encheres
		
		//A régler avec vue statique
		ArticleVenduManager manager = new ArticleVenduManager();
		
		try {
			List<ArticleVendu>list = manager.selectAvecPseudo();
			request.setAttribute("encheres", list);
			
			request.setAttribute("pageActuelle", "accueil");
			for(ArticleVendu art:list) {
				System.out.println(art.getPseudo());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jspFiles/jspPageDAccueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVenduManager manager = new ArticleVenduManager();
		
		List<ArticleVendu> list_encheres=new ArrayList<ArticleVendu>();
	
		try {
			
			List<ArticleVendu> listAllEncheres = manager.selectAvecPseudo();
			request.setAttribute("encheres", listAllEncheres);
		
			
		if(request.getParameter("rechercher")!=null) {
			
			String saisieUtilisateur=request.getParameter("saisieUtilisateur");
			String select=request.getParameter("select");
			int no_categorie=0;
			
 			/*----cas chercher par nom------*/
			if(saisieUtilisateur!=null) {
				if(select==null) {
				
				listAllEncheres.stream()
				.filter(x-> x.getNomArticle().toLowerCase().contains(saisieUtilisateur.toLowerCase()) )
				.forEach(x->list_encheres.add(x))
				;	
				request.setAttribute("list_encheres",list_encheres);
				
				}
				else
				{
					switch (select) {
					case "informatique": no_categorie=1;
						break;
					case "vetement": no_categorie=2;
					break;
					case "ameublement": no_categorie=3;
					break;
					case "sport": no_categorie=4;
					break;
					default:
						break;
					}
					listAllEncheres=manager.selectByCategorie(no_categorie);

					for(ArticleVendu enchere:listAllEncheres) {
						if(enchere.getNomArticle().toLowerCase().contains(saisieUtilisateur.toLowerCase()))
							list_encheres.add(enchere);		
			 		}
					request.setAttribute("list_encheres", list_encheres);	
				}
			}
			else 
				if(select!=null) {

					listAllEncheres=manager.selectByCategorie(no_categorie);
					request.setAttribute("list_encheres", listAllEncheres);}

		}
		
			} catch (SQLException e) {
			// TODO MALVINA gérer exception
			e.printStackTrace();
			}
		request.setAttribute("pageActuelle", "resultat_de_recherche");
		
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jspFiles/jspResultatDeRecherche.jsp");
		rd.forward(request, response);
	}

}
