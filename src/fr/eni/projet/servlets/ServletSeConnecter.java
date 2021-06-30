package fr.eni.projet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.forms.ConnectionForm;

/**
 * Antoine t
 * Servlet servant à diriger vers la JSP Connexion
 * Servlet implementation class ServletSeConnecter
 */
@WebServlet("/ServletSeConnecter")
public class ServletSeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On dirige vers la JSP connexion //
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/connexion.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Instanciation du gestionnaire de formulaire et on récupère ses infos
		
		ConnectionForm form = new ConnectionForm();
		form.verifierIdentifiants(request);
		request.setAttribute("form", form);
		
		//Puis on retourne tout cela à la JSP
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/connexion.jsp").forward(request, response);
		System.out.println("form getresultat arrive :");
		System.out.println(form.getResultat());
		
	}
		
}
	
