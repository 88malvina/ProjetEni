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
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.FormManager;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Utilisateur;

/**
 * Antoine 
 * Servlet servant Ã  diriger vers la JSP Connexion
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

		//Récupération des paramètres de la requête
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");

		//Instanciation du gestionnaire de formulaire et on récupère ses infos
		FormManager identifiants = new FormManager();
		Boolean estConnecte = identifiants.verifConnexion(pseudo, password);

		//en cas de connexion ok, on passe l'utilisateur en attribut de session
		if (estConnecte) {
			Utilisateur u = new Utilisateur();
			u = UtilisateurManager.selectByPseudo(pseudo);
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", u);
		}

		//Variable message pour afficher dans la JSP
		String messageLog = null;
		if (estConnecte) {
			messageLog = "Bonjour " + pseudo + " Vous êtes bien connecte";
		} else {
			messageLog = "Identifiant ou mot de passe incorrect, réessayez";
		}

		request.setAttribute("messageLog", messageLog);

		//Puis on retourne tout cela Ã  la JSP
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/connexion.jsp").forward(request, response);
		System.out.println(messageLog);

	}

}

