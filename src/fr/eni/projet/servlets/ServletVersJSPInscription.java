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
		u.setTelephone(request.getParameter("telephone"));
		u.setRue(request.getParameter("rue"));
		u.setCodePostal(request.getParameter("code_postal"));
		u.setVille(request.getParameter("ville"));
		u.setMotDePasse(request.getParameter("mot_de_passe"));
		u.setCredit(0);
		u.setAdministrateur(false);

		String message_erreur = mng.controleInscription(u, confirmation_mot_de_passe);

		if (message_erreur.equals("Verificaton réussite.")) {

			mng.insert(u);
			session.setAttribute("utilisateur", u);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspPageDAccueil.jsp").forward(request, response);

		} else {
			session.setAttribute("message_erreur", message_erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/JSPInscription.jsp").forward(request, response);
		}

	}

}
