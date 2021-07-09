package fr.eni.projet.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class ServletEncherir
 */
@WebServlet("/ServletEncherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEncherir() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enchere enchere = new Enchere();
		HttpSession session = request.getSession();

		//int noArticle = request.getIntHeader("no_article");
		
//		String action = request.getParameter("action");
//		System.out.println(action);
//		int noArticle = Integer.parseInt(action);	
		
		
		int noArticle = Integer.parseInt((String) session.getAttribute("no_article"));

		Utilisateur payant = (Utilisateur) session.getAttribute("utilisateur");
		enchere.setNo_utilisateur(payant.getNoUtilisateur());
		enchere.setPayant(payant);
		
		System.out.println(payant);
	
		ArticleVenduManager mngArt = new ArticleVenduManager();
		ArticleVendu article_a_vendre = mngArt.selectById(noArticle);
		
		int no_vendeur = article_a_vendre.getNo_utilisateur();
		UtilisateurManager mngUti = new UtilisateurManager();
		Utilisateur vendeur = mngUti.selectById(no_vendeur);
		
		System.out.println(vendeur);
		
		System.out.println(article_a_vendre);
		
		enchere.setNo_article(noArticle);
		enchere.setArticleAVendre(article_a_vendre);
		enchere.setDate_enchere(LocalDate.now());
		
		System.out.println(enchere);
		
		String verifEnchere="";
		
		int montant_enchere = 0;
		if(request.getParameter("montant_enchere").equals("")) {
			verifEnchere="Vous devez séléctioner une valeur.";
		} else {
			montant_enchere = Integer.valueOf(request.getParameter("montant_enchere"));
			enchere.setMontant_enchere(montant_enchere);
		}
		

		EnchereManager mngEnc = new EnchereManager();
		verifEnchere = mngEnc.verifierEnchere(enchere, article_a_vendre, payant, vendeur);
		
		
		
		if(verifEnchere.equals("Vérification d'enchère réussite.")) {
			try {
				//Enchere ancienne_gagnante = article_a_vendre.getEnchereGagnante();
				//int no_anc_gagnant = ancienne_gagnante.getNo_utilisateur();
				//Utilisateur ancien_payant = mngUti.selectById(no_anc_gagnant);
				//ancien_payant.setCredit(ancien_payant.getCredit()+montant_enchere);
				//mngUti.update(ancien_payant);
				
				mngEnc.insert(enchere);
				article_a_vendre.setPrixVente(montant_enchere);
				article_a_vendre.setEnchereGagnante(enchere);
				String msg_erreur_encherir = "Félicitations ! Vous venez d'enchérir cet article";
				request.setAttribute("msg_erreur_encherir", msg_erreur_encherir);
				
				payant.setCredit(payant.getCredit()-montant_enchere);
				mngUti.update(payant);
				
				mngArt.update(article_a_vendre);
				request.setAttribute("article_enc",article_a_vendre);
				this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspEncherir.jsp").forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("msg_erreur_encherir", "Problème au moment de l'enchère. Veiullez réessayer");
				e.printStackTrace();
			}
		} else {
			request.setAttribute("msg_erreur_encherir", verifEnchere);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jspFiles/jspEncherir.jsp").forward(request, response);
		}
		
	}

}
