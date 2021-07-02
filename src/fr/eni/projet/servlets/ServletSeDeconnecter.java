package fr.eni.projet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet gérant la déconnexion utilisateur
 * @author ablanchet2021
 */


@WebServlet("/ServletSeDeconnecter")
public class ServletSeDeconnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Retour � la page connexion
		
		HttpSession session = request.getSession();
		if (session.getAttribute("utilisateur")!=null) {
			session.invalidate();
			this.getServletContext().getRequestDispatcher("/ServletSeConnecter").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
