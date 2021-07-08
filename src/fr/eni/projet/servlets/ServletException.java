package fr.eni.projet.servlets;

public class ServletException extends Exception {


	private static final long serialVersionUID = -7619148820145992570L;

	public ServletException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServletException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "ServletException Exception : " + super.getMessage();
	}
}
