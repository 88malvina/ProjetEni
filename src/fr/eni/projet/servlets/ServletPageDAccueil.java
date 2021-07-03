package fr.eni.projet.servlets;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		
		/*----r�cup�rer les valeurs de la table Ench�res-----*/
		
		//A r�gler avec vue statique
		
		EnchereManager manager = new EnchereManager();
		try {
			List<Enchere> list=manager.selectAll();
			request.setAttribute("encheres", list);
				
			
		} catch (SQLException e) {
			// TODO MALVINA gérer exception
			e.printStackTrace();
		}
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jspFiles/jspPageDAccueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager manager = new EnchereManager();
		int no_categorie=0;
		List<Enchere> listAllEncheres;
		List<Enchere> list_encheres_trouver_parNom=new ArrayList<Enchere>();
		
		List<Enchere> list3=new ArrayList<Enchere>();
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
			
			/*----cas chercher par nom------*/
			if(saisieUtilisateur!=null && select==null) {
				
				listAllEncheres.stream()
				.filter(Objects :: nonNull)
				.filter(x-> x.getArticle_vendu().toLowerCase().contains(saisieUtilisateur.toLowerCase()) )
				.forEach(x->list_encheres_trouver_parNom.add(x))
				;	
			}
			/*-----fin------*/
			
			
			/*-----cas chercher par categorie-----*/ 
			/*-------refaire avec les filtres-------*/
			if(saisieUtilisateur==null && select!=null){
			
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
			
			 list_categorie=manager.selectEncheresByCategorie(no_categorie);
			
			 if(list_categorie!=null)
				{request.setAttribute("categorie", list_categorie);}
			 else {
				 request.setAttribute("aucune_trouvé", "Rien n'a été trouvé");}
			}System.out.println(list_categorie);
			
			/*-------fin-------*/
			
			/*-----cas chercher par nom et categorie-----*/
			if(saisieUtilisateur!=null && select!=null) {
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
							list3.add(enchere);
						}
							
					}
					else {
					request.setAttribute("aucune_trouvé", "Rien n'a été trouvé");
					}
					request.setAttribute("list_rechereche_avecNom_et_categorie", list3);	
			}
			/*-----fin-----*/
				
		}
		
			} catch (SQLException e) {
			// TODO MALVINA gérer exception
			e.printStackTrace();
			}

		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jspFiles/jspResultatDeRecherche.jsp");
		rd.forward(request, response);
	}

}
