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
 * Servlet implementation class ServletVersJSPInscription
 */
@WebServlet("/ServletVersJSPInscription")
public class ServletVersJSPInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;
		rd = request.getRequestDispatcher("/WEB-INF/jspFiles/JSPInscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if ((request.getParameter("mot_de_passe")).equals(request.getParameter("confirmation_mot_de_passe"))) {
			Utilisateur u = new Utilisateur();
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
			
			UtilisateurManager.insert(u);
			
			//TODO PRISCILA diriger vers la page d'accueil en mode logedId
			RequestDispatcher rd=null;
			rd = request.getRequestDispatcher("/WEB-INF/jspFiles/jspPageDAccueil.jsp");
			rd.forward(request, response);
		}
		//TODO PRISCILA add else pour msg "mots de passe diferents"
	}

}
