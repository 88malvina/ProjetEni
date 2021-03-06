package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.businessException.BusinessException;

/**
 * Servlet implementation class ServletVersJSPInscription
 */

@WebServlet("/ServletVersJSPInscription")
public class ServletVersJSPInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pageActuelle", "inscription");
		this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/JSPInscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Utilisateur u = new Utilisateur();
		String confirmation_mot_de_passe = request.getParameter("confirmation_mot_de_passe");
		HttpSession session = request.getSession();
		UtilisateurManager mng = new UtilisateurManager();

		u.setPseudo(request.getParameter("pseudo"));
		u.setNom(request.getParameter("nom"));
		u.setPrenom(request.getParameter("prenom"));
		u.setEmail(request.getParameter("email"));
		u.setRue(request.getParameter("rue"));
		u.setCodePostal(request.getParameter("code_postal"));
		u.setVille(request.getParameter("ville"));
		u.setMotDePasse(request.getParameter("mot_de_passe"));
		u.setCredit(0);
		u.setAdministrateur(false);
		if(!request.getParameter("telephone").equals("")) {
			u.setTelephone(request.getParameter("telephone"));
		}
		
		
		
		
		String message_erreur = null;
		
		try {
			message_erreur = mng.controleInscription(u, confirmation_mot_de_passe);
		} catch (BusinessException e) {
			e.getMessage();
			e.printStackTrace();
		}

		if (message_erreur.equals("Verificaton r??ussite.")) {

			mng.insert(u);
			session.setAttribute("utilisateur", u);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspPageDAccueilModeConnect??.jsp").forward(request, response);

		} else {
			// Si on n'a pas r??ussi ?? se connecter, on revient sur le formulaire mais d??j?? rempli avec nos infos
			// Pour garder en m??moire la saisie utilisateur, on renvoie tout cela ?? la jsp
			
			if (message_erreur.equals("")) {
				message_erreur = "Erreur inconnue";
			}
			
			String pseudo = (request.getParameter("pseudo"));
			System.out.println("pseudo est " + pseudo);
			String nom = (request.getParameter("nom"));
			System.out.println(nom);
			String prenom = (request.getParameter("prenom"));
			System.out.println(prenom);
			String email = (request.getParameter("email"));
			System.out.println(email);
			String telephone = (request.getParameter("telephone"));
			System.out.println(telephone);
			String rue = (request.getParameter("rue"));
			System.out.println(rue);
			String code_postal = (request.getParameter("code_postal"));
			System.out.println(code_postal);
			String ville =(request.getParameter("ville"));
			System.out.println(ville);
			System.out.println("fin des retours saisie inscirption pour debug");
			
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("email", email);
			request.setAttribute("telephone", telephone);
			request.setAttribute("rue", rue);
			request.setAttribute("code_postal", code_postal);
			request.setAttribute("ville", ville);
			
			session.setAttribute("message_erreur", message_erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/JSPInscription.jsp").forward(request, response);
		}

	}

}
