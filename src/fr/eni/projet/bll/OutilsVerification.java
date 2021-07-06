package fr.eni.projet.bll;

/**
 * 
 * Classe en charge de fournir de méthodes utiles pour les verifications
 * @author pconchou2021
 * 
 */
public class OutilsVerification {

	/**
	 * Méthode en charge de vérifier si un String n'a pas de caracteres spéciaux (spaces inclus)
	 * 
	 * @param motAVerifier le String à verifier
	 * @return true si le String n'a pas de caracteres spéciaux
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
	 * Méthode en charge de vérifier si un String a que des chiffres
	 * 
	 * @param aVerifier le String à verifier
	 * @return true si le String a que des chiffres
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
	
	
	/**
	 * Méthode en charge de vérifier si un String a que des lettres, spaces et tirets
	 * 
	 * @param motAVerifier le mot à verifier
	 * @return true si le String a que des lettres et spaces
	 * @author pconchou2021
	 */
	public static boolean onlyLetters(String aVerifier) {
		boolean onlyNumbersAndLetters = false;
		char[] chars = aVerifier.toCharArray();
		for(char c : chars) {
			if(c>=65 && c<=90 || c>=97 && c<=122 || c==32 || c==45) {
				onlyNumbersAndLetters=true;
			} else {
				onlyNumbersAndLetters=false;
				break;
			}
		}
		return onlyNumbersAndLetters;
	}
}
