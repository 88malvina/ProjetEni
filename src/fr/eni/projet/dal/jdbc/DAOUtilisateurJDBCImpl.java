package fr.eni.projet.dal.jdbc;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.DAOUtilisateur;

public class DAOUtilisateurJDBCImpl implements DAOUtilisateur {

	private String insert = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	
	private String delete = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	
	private String update = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur=?;";
	
	private String selectAll = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS ORDER BY prenom,nom;";
	
	private String selectById = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur=?;";
	
	private String selectByPseudo = "select * from UTILISATEURS WHERE pseudo=? ";
	
	
	@Override
	public void insert(Utilisateur u) {

		try (
				
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();
				
				PreparedStatement psmt = cnx.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setString(1, u.getPseudo());
			psmt.setString(2, u.getNom());
			psmt.setString(3, u.getPrenom());
			psmt.setString(4, u.getEmail());
			psmt.setString(5, u.getTelephone());
			psmt.setString(6, u.getRue());
			psmt.setString(7, u.getCodePostal());
			psmt.setString(8, u.getVille());
			psmt.setString(9, u.getMotDePasse());
			psmt.setInt(10, u.getCredit());
			psmt.setBoolean(11, u.isAdministrateur());
			
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();
			if (rs.next()) {
				u.setNoUtilisateur(rs.getInt(1));
			}
			
			cnx.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Utilisateur u) {
		try (
				
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();
				
				PreparedStatement psmt = cnx.prepareStatement(delete, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, u.getNoUtilisateur());
			
			psmt.executeUpdate();
			
			cnx.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Utilisateur u) {
		try (
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();
				
				PreparedStatement psmt = cnx.prepareStatement(update, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setString(1, u.getPseudo());
			psmt.setString(2, u.getNom());
			psmt.setString(3, u.getPrenom());
			psmt.setString(4, u.getEmail());
			psmt.setString(5, u.getTelephone());
			psmt.setString(6, u.getRue());
			psmt.setString(7, u.getCodePostal());
			psmt.setString(8, u.getVille());
			psmt.setString(9, u.getMotDePasse());
			psmt.setInt(10, u.getCredit());
			psmt.setBoolean(11, u.isAdministrateur());
			psmt.setInt(12, u.getNoUtilisateur());
			
			psmt.executeUpdate();
			
			cnx.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Utilisateur> selectAll() {
		
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		try (
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();
				
				
				Statement smt = cnx.createStatement()) 
		{
			ResultSet rs = smt.executeQuery(selectAll);
			Utilisateur u = null;
			while(rs.next())
			{
				u = new Utilisateur();
				u.setNoUtilisateur(rs.getInt("no_utilisateur"));
				u.setPseudo(rs.getString("pseudo"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setEmail(rs.getString("email"));
				u.setTelephone(rs.getString("telephone"));
				u.setRue(rs.getString("rue"));
				u.setCodePostal(rs.getString("code_postal"));
				u.setVille(rs.getString("ville"));
				u.setMotDePasse(rs.getString("mot_de_passe"));
				u.setCredit(rs.getInt("credit"));
				u.setAdministrateur(rs.getBoolean("administrateur"));
				
				utilisateurs.add(u);	
			}
			
			cnx.close();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return utilisateurs;
	}

	@Override
	public Utilisateur selectById(int id) {
		Utilisateur u = null;
		
		try (
				
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();
				
				PreparedStatement psmt = cnx.prepareStatement(selectById, PreparedStatement.RETURN_GENERATED_KEYS);) {

			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next())
			{
				u = new Utilisateur();
				u.setNoUtilisateur(rs.getInt("no_utilisateur"));
				u.setPseudo(rs.getString("pseudo"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setEmail(rs.getString("email"));
				u.setTelephone(rs.getString("telephone"));
				u.setRue(rs.getString("rue"));
				u.setCodePostal(rs.getString("code_postal"));
				u.setVille(rs.getString("ville"));
				u.setMotDePasse(rs.getString("mot_de_passe"));
				u.setCredit(rs.getInt("credit"));
				u.setAdministrateur(rs.getBoolean("administrateur"));
				
				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	//Ajout Antoine selectByPseudo pour valider la connexion ====================
	
		public Utilisateur selectByPseudo(String pseudo) {
			Utilisateur u = null;
			
			try (
				
				//Remplacement par pool de connexion via ConnectionProvider
				//Connection cnx = JdbcTools.getConnection();
				Connection cnx = ConnectionProvider.getConnection();
					
				PreparedStatement psmt = cnx.prepareStatement(selectByPseudo);) {
			
				psmt.setString(1, pseudo);
				
				ResultSet rs = psmt.executeQuery();
				
				if(rs.next()) {
					u = new Utilisateur();
					u.setPseudo(pseudo);
					u.setMotDePasse(rs.getString("mot_de_passe"));
					u.setNom(rs.getString("nom"));
					u.setPrenom(rs.getString("prenom"));
					u.setEmail(rs.getString("email"));
					u.setTelephone(rs.getString("telephone"));
					u.setRue(rs.getString("rue"));
					u.setCodePostal(rs.getString("code_postal"));
					u.setVille(rs.getString("ville"));
					u.setMotDePasse(rs.getString("mot_de_passe"));
					u.setCredit(rs.getInt("credit"));
					u.setAdministrateur(rs.getBoolean("administrateur"));
					
					
					
					cnx.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
				
			return u;
		}

}
