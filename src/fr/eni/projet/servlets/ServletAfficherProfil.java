package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		System.out.println("Nous somme dans le doGet ServletAfficherProfil");
		//On dirige vers la JSP afficher profil
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/jspAfficherProfil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Nous somme dans le doPost ServletAfficherProfil");
		
		//On r�cup�re le param�tre de recherche
		String pseudo = request.getParameter("pseudo");
		
		//On v�rifie que l'utilisateur existe gr�ce au FormManager
		UtilisateurManager verif = new UtilisateurManager();
		Boolean utilisateurExiste = verif.verifUtilisateurExiste(pseudo);
		System.out.println("le boolean utilisateur existe indique : " + utilisateurExiste);
		
		//Si l'utilisateur existe ou non, on pr�pare un texte � afficher en jsp
		String messageJsp = null;
		if (!utilisateurExiste) {
			messageJsp = "Cet utilisateur n'existe pas";
		} else {
			messageJsp = "Voici les informations concernant" + pseudo;
		}
		
		request.setAttribute("messageJsp", messageJsp);
		
		// Si l'utilisateur existe, on va le récupérer et afficher ses infos
		// Infos a afficher = Les pseudo, nom, pr�nom, e-mail, tel, rue, code postal, ville
		
		if (utilisateurExiste) {
			
			UtilisateurManager mng = new UtilisateurManager();
			System.out.println("l'utilisateur existe et on cherche � r�cup�rer ses infos avant de les renvoyer");
			Utilisateur u = new Utilisateur();
			u = mng.selectByPseudo(pseudo);
			String nomCherche = u.getNom();
			System.out.println("le nom de l'utilisateur cherché est : " + nomCherche);
			
			String prenomCherche = u.getPrenom();
			String emailCherche = u.getEmail();
			String telephoneCherche = u.getTelephone();
			String rueCherche = u.getRue();
			String cpCherche = u.getCodePostal();
			String villeCherche = u.getVille();
			
			request.setAttribute("pseudo", pseudo);
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
