/**
 * 
 */
package fr.eni.projet.dal;

/**
 * Classe en charge de gérer les exceptions
 * @author PConchou
 */

public class DALException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7619148820145992570L;

	public DALException(String message, Throwable cause) {
		super(message, cause);
	}

	public DALException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "DAL Exception : " + super.getMessage();
	}

}
