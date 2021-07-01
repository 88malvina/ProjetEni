package fr.eni.projet.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.CategorieManager;
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;

/**@author mavetyan2021
 * Servlet implementation class ServletPageDAccueil
 */
@WebServlet("/encheres/ServletPageDAccueil")
public class ServletPageDAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager manager=new EnchereManager();
		
		/*----r�cup�rer les valeurs de la table Ench�res-----*/
		
		
		try {
			List<Enchere> list=manager.selectAll();
			request.setAttribute("encheres", list);
				
			
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
		EnchereManager manager=new EnchereManager();
		int no_categorie=0;
		List<Enchere> listAllEncheres;
		List<Enchere> list_encheres_trouver_parNom=new ArrayList<Enchere>();
		List<Enchere> list3=new ArrayList<Enchere>();
		 List<Enchere> list_categorie=null;
		 List<Enchere> list_categorie2=null;
		try {
			listAllEncheres = EnchereManager.selectAll();
			request.setAttribute("encheres", listAllEncheres);
		
		if(request.getParameter("rechercher")!=null) {
			String saisieUtilisateur=request.getParameter("saisieUtilisateur");
			String select=request.getParameter("select");
			
			if(saisieUtilisateur!=null && select==null) {
				
				request.setAttribute("saisie", saisieUtilisateur);
				for(Enchere enchere:listAllEncheres) {
					if(enchere.getArticle_vendu().toLowerCase().contains(saisieUtilisateur.toLowerCase()))
					{
					
						list_encheres_trouver_parNom.add(enchere);
					}
					else {
						request.setAttribute("aucune_trouvé", "Rien n'a été trouvé");
					}
				}
				request.setAttribute("list_rechereche", list_encheres_trouver_parNom);
			}
			
			if(saisieUtilisateur==null && select!=null){
				System.out.println("mtav");
			//request.setAttribute("select", select);
			System.out.println(select);
			switch (select) {
			case "vetement": no_categorie=1;
				break;
			case "ameublement": no_categorie=2;
			break;
			case "sport": no_categorie=3;
			break;
			default:
				break;
			}
			System.out.println(no_categorie);
			
			 list_categorie=EnchereManager.selectEncheresByCategorie(no_categorie);
				request.setAttribute("categorie", list_categorie);
			}
			if(saisieUtilisateur!=null && select!=null) {
				switch (select) {
				case "vetement": no_categorie=1;
					break;
				case "ameublement": no_categorie=2;
				break;
				case "sport": no_categorie=3;
				break;
				default:
					break;
				}
				
				 list_categorie2=EnchereManager.selectEncheresByCategorie(no_categorie);
					request.setAttribute("categorie", list_categorie2);
					System.out.println(list_categorie2);
					for(Enchere enchere:listAllEncheres) {
						if(enchere.getArticle_vendu().toLowerCase().contains(saisieUtilisateur.toLowerCase()))
						{
						
							list3.add(enchere);
						}
						else {
							request.setAttribute("aucune_trouvé", "Rien n'a été trouvé");
						}
					}
					System.out.println(list_categorie2);
					request.setAttribute("list_rechereche", list_categorie2);
					
			}
			
				
		}
		
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jspFiles/jspResultatDeRecherche.jsp");
		rd.forward(request, response);
	}

}
