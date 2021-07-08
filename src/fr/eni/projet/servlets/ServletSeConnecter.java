package fr.eni.projet.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.businessException.BusinessException;

/**
 * Antoine 
 * Servlet servant à diriger vers la JSP Connexion
 * Servlet implementation class ServletSeConnecter
 */  
@WebServlet("/ServletSeConnecter")
public class ServletSeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère tous les cookies stockés via un tableau de cookies
		Cookie[] cookies = request.getCookies();
		//Si le cookie porte le nom de pseudo, on récupère la valeur et on la passe en attribut de requete
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("pseudo")) {
					request.setAttribute("pseudo",cookie.getValue());
				}
			}
		}
		request.setAttribute("pageActuelle", "seConnecter");
		
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
		Boolean estConnecte = null;
		
		try {
			estConnecte = identifiants.verifConnexion(pseudo, password);
		} catch (BusinessException e) {
			e.getMessage();
			e.printStackTrace();
		}

		//en cas de connexion ok, on passe l'utilisateur en attribut de session
		if (estConnecte) {
			
			UtilisateurManager mng = new UtilisateurManager();
			Utilisateur u = new Utilisateur();
			
			try {
				u = mng.selectByPseudo(pseudo);
			} catch (BusinessException e) {
				e.getMessage();
				e.printStackTrace();
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", u);
			
			//On enverra aussi un petit message a afficher en jsp accueil
			String messageLog = "Bonjour " + pseudo + "!";
			request.setAttribute("messageLog", messageLog);
			
			// ----------------DEBUT gestion des cookies DEBUT--------------------------------
			
			//Si on a coché "se souvenir de moi", on va créer un cookie qui memorise pseudo
			//D'abord on vérifie si on a laissé coché "se souvenir de moi"
			
			String checked = request.getParameter("seSouvenirDeMoi");
			System.out.println(checked);
			
			//Si c'est coché ça renvoie "on", sinon ça ne renvoie rien du tout. On passe en boolean c'est plus clair je trouve
			Boolean seSouvenirDeMoi = false;
			if (checked.equals("on")) {
				System.out.println("condition de check case cohchée est faite");
				seSouvenirDeMoi = true;
			} else {
				seSouvenirDeMoi = false;
			}
			
			// Donc si c'est true, on créé le cookie que l'on récupèrera ensuite en doGET
			// Méthode de création de cookie de base pour info : response.addCookie(new Cookie("pseudo", pseudo));
			
			if (seSouvenirDeMoi) {
				Cookie cookie = new Cookie ("pseudo", pseudo);
				//On dit que le cookie va durer 24 heures
				cookie.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie);
				}
			
			// ----------------FIN    gestion des cookies   FIN  --------------------------------
			
			//On lui enverra les listes articles vendus
			

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

