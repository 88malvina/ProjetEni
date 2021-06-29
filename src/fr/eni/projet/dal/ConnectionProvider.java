/**
 * 
 */
package fr.eni.projet.dal;

import java.sql.Connection;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe en charge de disponibiliser une connection dans le pool
 * @author pconchou2021
 *
 */


public class ConnectionProvider {

	private static DataSource dataSource;

	static {
		Context context;

		try {
			context = new InitialContext();
			ConnectionProvider.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/projet_pool_db");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible de se connecter à la base de données");
		}
	}

	public static Connection getConnection() throws SQLException {
		return ConnectionProvider.dataSource.getConnection();
	}

}
