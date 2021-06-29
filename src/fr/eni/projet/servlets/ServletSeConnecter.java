package fr.eni.projet.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet servant � diriger vers la JSP Connexion
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
		
		// On r�cup�re les donn�es du formulaire
				
		String pseudo = request.getParameter("pseudo");
		System.out.println(pseudo);
		String password = request.getParameter("password");
		String souvenirDeMoi = request.getParameter("souvenirDeMoi");
		
		// ====== V�rification de la saisie utilisateur ====== 
		
		// TODO V�rification de validit� � faire ?
		
		// V�rification que l'utilisateur est bien en base de donn�es
		
		try {
			
			Utilisateur utilisateur = UtilisateurManager.selectByPseudo(pseudo);
			
			//V�rification que l'utilisateur est en base, si c'est le cas, redirection
			if(password.equals(utilisateur.getMotDePasse())) {
				
				//Int�grer un attribut "connect�" � l'utilisateur ? ici
				
				//Redirection � faire sur page avec les 2 lignes en dessous
				
				//RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/connexionTest.jsp");
				//rd.forward(request, response);
				
				
				
				request.setAttribute("pseudo", pseudo);
				request.setAttribute("password", password);
				request.setAttribute("souvenirDeMoi", souvenirDeMoi);
				
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/testReussiteConnexion.jsp");
				rd.forward(request, response);
				
			} else {
				// TODO Si l'identification est mauvaise : message d'erreur et on recharge la page
				PrintWriter out = new PrintWriter("Erreur de log");
				out.close();
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/testEchecConnexion.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("erreur", e);
			//Envoi de l'erreur via une page ?
			//RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/connexionTest.jsp");
			//rd.forward(request, response)

		}
	
	}
		
}
	