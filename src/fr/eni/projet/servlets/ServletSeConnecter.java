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

/**
 * Antoine
 * Servlet servant à diriger vers la JSP Connexion
 * Servlet implementation class ServletSeConnecter
 */
@WebServlet("/ServletSeConnecter")
public class ServletSeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On dirige vers la JSP connexion  
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/connexion.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// On récupère les données du formulaire
				
		String pseudo = request.getParameter("pseudo");
		System.out.println(pseudo);
		String password = request.getParameter("password");
		System.out.println(password);
		String souvenirDeMoi = request.getParameter("souvenirDeMoi");
		
		// ====== Vérification de la saisie utilisateur ====== 
		
		// TODO Vérification de validité à faire ?
		
		// Vérification que l'utilisateur est bien en base de données
	
		try {
			
			
			RequestDispatcher rd=null;
			List<Utilisateur> list= UtilisateurManager.sellectAll();
			for(Utilisateur ut:list) {
				if((ut.getPseudo().equals(pseudo)) && (ut.getMotDePasse().equals(password))) {
					
					request.setAttribute("pseudo", pseudo);
					request.setAttribute("password", password);
					request.setAttribute("souvenirDeMoi", souvenirDeMoi);
					
					rd = request.getRequestDispatcher("WEB-INF/jspFiles/testReussiteConnexion.jsp");
					
				}
				else {
					PrintWriter out = new PrintWriter("Erreur de log");
					out.close();
					rd = request.getRequestDispatcher("WEB-INF/jspFiles/testEchecConnexion.jsp");
					
				}
			}rd.forward(request, response);
			//Vérification que l'utilisateur est en base, si c'est le cas, redirection
			
			
		}catch (Exception e) {
			request.setAttribute("erreur", e);
			//Envoi de l'erreur via une page ?
			//RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/connexionTest.jsp");
			//rd.forward(request, response)

		}
	
	}
		
}
	
