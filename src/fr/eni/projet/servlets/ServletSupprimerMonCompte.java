package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Utilisateur;


/**
 * Servlet en charge de gérer la suppression de son compte par un utilisateur connecté
 * @author ablanchet2021
 */

@WebServlet("/ServletSupprimerMonCompte")
public class ServletSupprimerMonCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;


	// doGet vers Jsp si connecté ========================================================================

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Si l'utilisateur est connecté, on le dirige vers la jspSupprimerMonCompte
		// Sinon, il n'a rien à faire là, on le dirige en page d'accueil
		// Rappel : Si la session ne contient pas d'attribut utilisateur, c'est que l'utilisateur n'est pas connecté

		HttpSession session = request.getSession();
		
		if (session.getAttribute("utilisateur").equals(null)) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/jspPageDAccueil.jsp");
			rd.forward(request, response);
		}
		
		// Si l'utilisateur est bien connecté, on prépare quelques paramètres que l'on va utiliser en JSP
		
		if (!session.getAttribute("utilisateur").equals(null)) {
			Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
			String pseudo = u.getPseudo();
			String prenom = u.getPrenom();
			String nom = u.getNom();
			String MotDePasse = u.getMotDePasse();
			int credit = u.getCredit();
			
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("prenom", prenom);
			request.setAttribute("nom", nom);
			request.setAttribute("MotDePasse", MotDePasse);
			request.setAttribute("credit", credit);
			
			//On envoie tout cela à la jspSupprimerMonCompte
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/jspSupprimerMonCompte.jsp");
			rd.forward(request, response);
		}
		
	}

	// doPost utilise fonction delete si confirmation ok =====================================================================

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// D'abord on récupère l'utilisateur qui est en attribut de session
		HttpSession session = request.getSession();
		Utilisateur u = new Utilisateur();
		u = (Utilisateur) session.getAttribute("utilisateur");
		String motDePasseInitial = u.getMotDePasse();
		
		//Ensuite on récupère le formulaire de la jspSupprimerMonCompte
		String motDePasse = request.getParameter("motDePasse");
		String confirmationMotDePasse = request.getParameter("confirmationMotDePasse");
		
		//On fait la verif de validite mot de passe via la methode dans UtilisateurManager
		System.out.println("Le mot de passe motDePasseInitial est " + motDePasseInitial);
		System.out.println("le mot de passe motDePasse est " + motDePasse);
		System.out.println("le mot de passe confirmationMotDePasse est " + confirmationMotDePasse);
		
		UtilisateurManager mng = new UtilisateurManager();
		Boolean verifMotDePasseEstOk = mng.controleMotDePasse(motDePasseInitial, motDePasse, confirmationMotDePasse);
		
		//Si ce n'est pas ok, on renvoie sur la page suppressionCompte et on affiche un petit message
		if (!verifMotDePasseEstOk) {
			System.out.println("dans la Servlet Supprimer compte, le mot de passe n'est pas ok");
			String erreur = "Suppression de votre compte impossible, erreur de mot de passe";
			request.setAttribute("erreur", erreur);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jspFiles/jspSupprimerMonCompte.jsp");
			rd.forward(request, response);
		}
		
		//Si c'est ok on utilise le delete sur l'utilisateur et on redirige sur l'accueil avec un message custom
		//On supprime également l'utilisateur de la session
		
		if (verifMotDePasseEstOk) {
			mng.delete(u);
			String compteSupprime = "Votre compte a été supprimé avec succès";
			request.setAttribute("compteSupprime", compteSupprime);
			session.removeAttribute("utilisateur");
			this.getServletContext().getRequestDispatcher("/encheres/ServletPageDAccueil").forward(request, response);
		}
	}

}
