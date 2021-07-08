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
import fr.eni.projet.businessException.BusinessException;

/**
 * Servlet en charge de permettre aux utilisateurs connectés de modifer leur profil
 * @author ablanchet2021
 */

@WebServlet("/ServletModifierMonProfil")
public class ServletModifierMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// ============================ DoGet vers jspModifierMonProfil seulement si l'utilisateur est connecté ============================
	
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

			// ======== On dirige vers la JSP
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspModifierMonProfil.jsp").forward(request, response);
			
		}
		
	}

	// ============================ DoPost envoi vers formulaire qui permet l'update des données utilisateur  ============================
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère l'utilisateur en attribut de session (pour le moment les maj ne sont pas faites)
		
		HttpSession session = request.getSession();
		Utilisateur old = (Utilisateur) session.getAttribute("utilisateur");
		
		// On récupére l'ensemble des champs du formulaire que l'on affecte à un nouvel utilisateur qui sera la mise à jour
		
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
		
		//On n'oublie pas de garder les infos qui n'ont pas été redemandées dans le formulaire
		u.setCredit(old.getCredit());
		u.setAdministrateur(old.isAdministrateur());
		u.setNoUtilisateur(old.getNoUtilisateur());
		
		//On récupére aussi la confirmation de mot de passe
		
		String confirmation_mot_de_passe = request.getParameter("confirmation_mot_de_passe");
		
		// On utilise la méthode controleModifProfil de l'utilisateur manager pour vérifier que c'est bon
		
		UtilisateurManager mng = new UtilisateurManager();
		String message_erreur = null;
		
		try {
			message_erreur = mng.ControleModifProfil(u, old, confirmation_mot_de_passe);
		} catch (BusinessException e) {
			e.getMessage();
			e.printStackTrace();
		}

		if (message_erreur.equals("Verificaton réussite.")) {
			//Si c'est ok on update et on change l'utilisateur en attribut de session
			System.out.println("Dans la servlet modifier le profil, la vérification a été réussie");
			mng.update(u);
			System.out.println("la méthode update vient d'être appliquée pour l'utilisateur pseudo :");
			System.out.println(u.getPseudo());
			
			System.out.println("logiquement en bdd on devrait avoir l'utilisateur suivant :");
			System.out.println(u.getPseudo());
			System.out.println(u.getNom());
			System.out.println("num utilisateur : " + u.getNoUtilisateur());
			
			//On dégage l'ancien attribut de session et on met le nouveau
			session.removeAttribute("utilisateur");
			session.setAttribute("utilisateur", u);
			
			//On créé un petit message que l'on voudra afficher en page d'accueil mode connecte
			
			String modifOk = "votre profil a bien été modifié";
			request.setAttribute("modifOk", modifOk);
			
			//On laisse sur la même page mais avec le message
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspModifierMonProfil.jsp").forward(request, response);

		} else {
			//Si ce n'est pas ok on produit le message d'erreur en attribut de requête
			String message = message_erreur;
			request.setAttribute("message", message);
			//On fait suivre le tout à la jsp qui l'affichera en cas d'erreur de modif profil
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspModifierMonProfil.jsp").forward(request, response);
		}
		
		
		
		doGet(request, response);
	}

}
