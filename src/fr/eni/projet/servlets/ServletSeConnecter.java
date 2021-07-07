package fr.eni.projet.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Enchere;
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
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/jspConnexion.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Récuperation des parametres de la requete
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");

		//Instanciation du gestionnaire de formulaire et on recupere ses infos
		UtilisateurManager identifiants = new UtilisateurManager();
		Boolean estConnecte = identifiants.verifConnexion(pseudo, password);

		//en cas de connexion ok, on passe l'utilisateur en attribut de session
		if (estConnecte) {
			
			UtilisateurManager mng = new UtilisateurManager();
			Utilisateur u = new Utilisateur();
			u = mng.selectByPseudo(pseudo);
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", u);
			
			//On enverra aussi un petit message a afficher en jsp accueil
			String messageLog = "Bonjour " + pseudo + " Vous êtes bien connecte";
			request.setAttribute("messageLog", messageLog);
			
			ArticleVenduManager manager = new ArticleVenduManager();
			List<ArticleVendu> list=manager.selectAll();

			request.setAttribute("encheres", list);
			
			request.setAttribute("pageActuelle", "accueil");
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspPageDAccueilModeConnecté.jsp").forward(request, response);
		}
		
		//En cas de connexion not ok, on renvoie vers la jsp se connecter avec un message d'erreur

		if (!estConnecte) {
			String erreurLog = "Erreur, vous n'avez pas été connecté";
			request.setAttribute("erreurLog",erreurLog);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspConnexion.jsp").forward(request, response);
		}

	}

}

