package fr.eni.projet.bll;

/**
 * 
 * Classe en charge de fournir de méthodes utiles pour les verifications
 * @author pconchou2021
 * 
 */
public class OutilsVerification {

	/**
	 * Méthode en charge de vérifier si un mot n'a pas de caracteres spéciaux (spaces inclus)
	 * 
	 * @param motAVerifier le mot à verifier
	 * @return true si le mot n'a pas de caracteres spéciaux
	 * @author pconchou2021
	 */
	public static boolean noSpecialChars(String motAVerifier) {
		boolean noSpecialChars = false;
		char[] charsDuMot = motAVerifier.toCharArray();
		for(char c : charsDuMot) {
			if(c>=48 && c<=57 || c>=65 && c<=90 || c>=97 && c<=122 ) {
				noSpecialChars=true;
			} else {
				noSpecialChars=false;
				break;
			}
		}
		return noSpecialChars;
	}
	
	/**
	 * 
	 * Méthode en charge de vérifier si un String a que des nombres
	 * 
	 * @param aVerifier le String à verifier
	 * @return true si le String a que des nombres
	 * @author pconchou2021
	 */
	public static boolean onlyNumbers(String aVerifier) {
		boolean onlyNumbers=false;
		char[] charsString = aVerifier.toCharArray();
		for(char c : charsString) {
			if(c>=48 && c<=57) {
				onlyNumbers=true;
			}else {
				onlyNumbers=false;
				break;
			}
		}
		return onlyNumbers;
	}
}
