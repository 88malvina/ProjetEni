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

import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bo.Enchere;

/**@author mavetyan2021
 * Servlet implementation class ServletPageDAccueil
 */
@WebServlet("/encheres/ServletPageDAccueil")
public class ServletPageDAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupérer valeurs table encheres
		
		//A régler avec vue statique
		
		EnchereManager manager = new EnchereManager();
		try {
			List<Enchere> list=manager.selectAll();
			request.setAttribute("encheres", list);
				
			
		} catch (SQLException e) {
			// TODO MALVINA gérer exception
			e.printStackTrace();
		}
		
		request.setAttribute("pageActuelle", "accueil");
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jspFiles/jspPageDAccueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager manager = new EnchereManager();
		
		List<Enchere> listAllEncheres;
		List<Enchere> list_encheres=new ArrayList<Enchere>();
	
		 List<Enchere> list_categorie=null;
		 List<Enchere> list_categorie2=null;
		try {
			//On pourrait avoir un filtre ? Il ne faut pas faire le selectall d'office car cela d�pend des filtres actifs ou pas
			// "tel filtre a �t� mis" = contrainte 
			// Conseil �ventuel : cr�er dynamiquement la requ�te selon les filtres s�lectionn�s
			// Select all = donc seulement si on a mis aucun filtre
			listAllEncheres = manager.selectAll();
			request.setAttribute("encheres", listAllEncheres);
		
			
		if(request.getParameter("rechercher")!=null) {
			String saisieUtilisateur=request.getParameter("saisieUtilisateur");
			String select=request.getParameter("select");
			int no_categorie=0;
			
			/*----cas chercher par nom------*/
			if(saisieUtilisateur!=null) {
				if(select==null) {
				
				listAllEncheres.stream()
				.filter(x-> x.getArticle_vendu().toLowerCase().contains(saisieUtilisateur.toLowerCase()) )
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
					list_categorie2=manager.selectEncheresByCategorie(no_categorie);
					request.setAttribute("cat", list_categorie2);
					
					if(list_categorie2!=null) {
					for(Enchere enchere:list_categorie2) {
						
						if(enchere.getArticle_vendu().toLowerCase().contains(saisieUtilisateur.toLowerCase()))
							list_encheres.add(enchere);
						}
							
					}
					else {
					request.setAttribute("aucune_trouvé", "Rien n'a été trouvé");
					}
					request.setAttribute("list_encheres", list_encheres);	
				}
			}
			else {
				if(select!=null) {

					 list_categorie=manager.selectEncheresByCategorie(no_categorie);
					
					 if(list_categorie!=null)
						{request.setAttribute("list_encheres", list_categorie);}
					 else {
						 request.setAttribute("aucune_trouvé", "Rien n'a été trouvé");}
					
				}
				else {
					request.setAttribute("aucune_trouvé", "Rien n'a été trouvé");
				}
			}
			
		}
		
			} catch (SQLException e) {
			// TODO MALVINA gérer exception
			e.printStackTrace();
			}

		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jspFiles/jspResultatDeRecherche.jsp");
		rd.forward(request, response);
	}

}
