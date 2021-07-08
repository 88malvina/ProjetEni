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
		try {
			List<ArticleVendu> list_encheres=new ArrayList<ArticleVendu>();
			List<ArticleVendu>list = manager.selectAvecPseudo();
		
		List<ArticleVendu> listAllEncheres=manager.selectAvecPseudo();
		List<ArticleVendu> list_en_cours = new ArrayList<ArticleVendu>();
		List<ArticleVendu> list_remportés = new ArrayList<ArticleVendu>();
		List<ArticleVendu> ventes_non_debutees = manager.selectVentesNonDebutees();
		
		
		if(request.getParameter("rechercher")!=null) {
			
			String saisieUtilisateur=request.getParameter("saisieUtilisateur");
			String select=request.getParameter("select");
			int no_categorie=0;
			
			/*----cas chercher par nom------*/
			if(request.getParameter("radio_button")==null) {
				if(saisieUtilisateur!=null) {
					if(select==null) {
					
					listAllEncheres.stream()
					.filter(x-> x.getNomArticle().toLowerCase().contains(saisieUtilisateur.toLowerCase()) )
					.forEach(x->list_encheres.add(x))
					;	
					request.setAttribute("list_encheres",list_encheres);
					System.out.println(list_encheres);
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
						request.setAttribute("cat", listAllEncheres);
						
						if(listAllEncheres!=null) {
						for(ArticleVendu enchere:listAllEncheres) {
							if(enchere.getNomArticle().toLowerCase().contains(saisieUtilisateur.toLowerCase()))
								list_encheres.add(enchere);
							}
								
				 		}
						else {
						request.setAttribute("aucune_trouvé", "Rien n'a été trouvé");
						}
						request.setAttribute("list_encheres", list_encheres);	
					}	
				}
			}
			
			if(request.getParameter("radio_button")!=null) {
				if(request.getParameter("radio_button").equals("radio-achats")){		
				switch (request.getParameter("encheres")) {
				
				case "encheres_ouvertes":
					for(ArticleVendu article: list) {
						if(article.getDateFinEncheres().isAfter(date)) {
							list_en_cours.add(article);
						} }
					request.setAttribute("mes_articles", list_en_cours);
					break;
				case "encheres_en_cours":
					for(ArticleVendu article: list) {
						if(article.getDateFinEncheres().isAfter(date)) {
							list_en_cours.add(article);
						} }
					request.setAttribute("mes_articles", list_en_cours);
					break;
				case "remportees": System.out.println(list_remportés+"k");
					for(ArticleVendu article: list) {
						if(article.getDateFinEncheres().isBefore(date)) {
							
							list_remportés.add(article);	
						} }
					request.setAttribute("mes_articles", list_remportés);
					
					break;

				default:
					break;
				}
			}
				else 
					if(request.getParameter("radio_button").equals("radio-mes_ventes")){
					switch (request.getParameter("ventes")) {
					case "ventes_en_cours":
						for(ArticleVendu article: list) {
							if(article.getDateFinEncheres().isAfter(date)) {
								list_en_cours.add(article);
							} }
						request.setAttribute("mes_articles", list_en_cours);
						break;
					case "ventes_non_debutees":
						if(ventes_non_debutees==null) {
							System.out.println("null");
							}
						else
							request.setAttribute("mes_articles", ventes_non_debutees);
						
						break;
					case "ventes_terminees":
						for(ArticleVendu article: list) {
							if(article.getDateFinEncheres().isBefore(date)) {
								System.out.println(article);
								list_remportés.add(article);	
							} }
						request.setAttribute("mes_articles", list_remportés);
						
						break;

					default:
						break;
					}
				}
					else {
						request.setAttribute("null", "Aucune trouvé");
					}
			}
			
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			request.setAttribute("pageActuelle", "resultat_de_recherche");
			request.setAttribute("aucune_trouvé","Aucune trouvé");
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jspFiles/jspResultatDeRecherche.jsp");
		rd.forward(request, response);
	}

}
