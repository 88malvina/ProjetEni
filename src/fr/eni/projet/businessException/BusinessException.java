/**
 * 
 */
package fr.eni.projet.businessException;

/**
 * Classe en charge de gérer les exceptions
 * @author PConchou
 */

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7619148820145992570L;

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "DAL Exception : " + super.getMessage();
	}

}
