package fr.eni.projet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * @author ablanchet2021
 * Servlet implementation class ServletTestPoolConnexion
 */
@WebServlet("/ProjetEni")
public class ServletTestPoolConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			Context context = new InitialContext();
			DataSource datasource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
			//demander une connexion
			Connection cnx = datasource.getConnection();
			out.print("la connexion est " + (cnx.isClosed()?"ferme":"ouverte"));
			//liberer la connexion lorsque l'on en a plus besoin
			cnx.close();
			
			
		} catch (NamingException | SQLException e) {
			// Gestion de l'exception
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("une erreur est survenue lors de la connexion à la base: " + e.getMessage());
			
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
