package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet toute simple pour gestion direction vers Afficher MON profil
 * copie quasi identique servlet Modifier mon profil
 * @author ablanchet2021
 */

@WebServlet("/ServletAfficherMonProfil")
public class ServletAfficherMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ============================ DoGet vers jspAfficherMonprofil seulement si l'utilisateur est connecté ============================

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère la session
		HttpSession session = request.getSession();

		//Si la session ne contient pas d'attribut utilisateur, c'est que l'utilisateur n'est pas connecté
		//On le redirige alors vers la jsp de connexion
		if (session.getAttribute("utilisateur").equals(null)) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/connexion.jsp");
			rd.forward(request, response);
		}

		//Si la session contient bien un attribut utilisateur, on le dirige vers la JSP Modifier mon profil avec
		//toutes ses infos déjà préremplies dans le formulaire

		if (!session.getAttribute("utilisateur").equals(null)) {
			Utilisateur u = new Utilisateur();
			u = (Utilisateur) session.getAttribute("utilisateur");

			// ======== On prépare les attributs pour envoyer à la jsp

			String pseudo = u.getPseudo();
			String nom = u.getNom();
			String prenom= u.getPrenom();
			String email = u.getEmail();
			String telephone = u.getTelephone();
			String rue = u.getRue();
			String cp= u.getCodePostal();
			String ville = u.getVille();
			String motDePasse = u.getMotDePasse();
			int credit = u.getCredit();

			// ======== On charge tous les attributs dans les attributs de requête

			request.setAttribute("pseudo", pseudo);
			request.setAttribute("prenom", prenom);
			request.setAttribute("nom", nom);
			request.setAttribute("email", email);
			request.setAttribute("telephone", telephone);
			request.setAttribute("rue", rue);
			request.setAttribute("cp", cp);
			request.setAttribute("ville", ville);
			request.setAttribute("motDePasse", motDePasse);
			request.setAttribute("credit", credit);

			// ======== On dirige


			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/jspAfficherMonProfil.jsp");
			rd.forward(request, response);
		}
	}

	// ================== DO POST =====================================================
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
