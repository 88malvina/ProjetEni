package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.FormManager;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet en charge d'afficher le profil des membres
 * @author ablanchet2021
 */


@WebServlet("/ServletAfficherProfil")
public class ServletAfficherProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On dirige vers la JSP afficher profil
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/jspAfficherProfil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère le paramètre de recherche
		String pseudo = request.getParameter("pseudo");
		
		//On vérifie que l'utilisateur existe grâce au FormManager
		FormManager verif = new FormManager();
		Boolean utilisateurExiste = verif.verifUtilisateurExiste(pseudo);
		
		//Si l'utilisateur ou non, on prépare un texte à afficher en jsp
		String messageJsp = null;
		if (!utilisateurExiste) {
			messageJsp = "Cet utilisateur n'existe pas";
		} else {
			messageJsp = "Voici les informations concernant" + pseudo;
		}
		
		request.setAttribute("messageJsp", messageJsp);
		
		// Si l'utilisateur existe, on va le récupérer et préparer ses infos à envoyer en JSP
		// Rappel énoncé : En tant qu’utilisateur, je peux afficher le profil d’un autre utilisateur. Les pseudo, 
		// nom, prénom, e-mail, numéro de téléphone, rue, code postal, ville sont affichés.
		

		if (utilisateurExiste) {
			Utilisateur u = new Utilisateur();
			u = UtilisateurManager.selectByPseudo(pseudo);
			String nomCherche = u.getNom();
			String prenomCherche = u.getPrenom();
			String emailCherche = u.getEmail();
			String telephoneCherche = u.getTelephone();
			String rueCherche = u.getRue();
			String cpCherche = u.getCodePostal();
			String villeCherche = u.getVille();
			
			request.setAttribute("nomCherche", nomCherche);
			request.setAttribute("prenomCherche", prenomCherche);
			request.setAttribute("emailCherche", emailCherche);
			request.setAttribute("telephoneCherche", telephoneCherche);
			request.setAttribute("rueCherche", rueCherche);
			request.setAttribute("cpCherche", cpCherche);
			request.setAttribute("villeCherche", villeCherche);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspAfficherProfil.jsp").forward(request, response);
	}

}
