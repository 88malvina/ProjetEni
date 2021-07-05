<!-- 
Antoine
Jsp en réception de la servlet SupprimerMonCompte
Demande une confirmation mot de passe avant suppression
Puis réalise un delete
nécessitant import jstl
 -->

<!--  
			MEMO des attributs envoyés par la servlet 
			
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("prenom", prenom);
			request.setAttribute("nom", nom);
			request.setAttribute("MotDePasse", MotDePasse);
			request.setAttribute("credit", credit);
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Je suis la jspSupprimerMonCompte</title>
</head>

<body>

	<!-- petit message d'erreur en cas d'échec vérif mot de passe -->
	<p>
		<c:out value="${erreur }" />
	</p>

	<h1>Supprimer mon compte utilisateur "${pseudo }" :'(</h1>
	<h2>Nous sommes tristes de vous voir partir ! Pour rappel il vous
		reste "${credit }" crédit</h2>
	<h2>"${prenom }" "${nom }" pour supprimer votre compte, veuillez
		saisir votre mot de passe</h2>

	<form action="<%=request.getContextPath()%>/ServletSupprimerMonCompte"
		method="post">

		<!-- TODO : mise en page CSS -->

		<div class=formLabel>
			<label for="motDePasse">Mot de passe :</label>
		</div>
		<div class=formInput>
			<input name="motDePasse" placeholder="Mon mot de passe" required
				type="password">
		</div>

		<div class=formLabel>
			<label for="confirmationMotDePasse"> Confirmer mot de passe :</label>
		</div>
		<div class=formInput>
			<input name="confirmationMotDePasse"
				placeholder="Confirmer mot de passe" required type="password">
		</div>

		<input type="submit" value="Valider" />

	</form>

</body>
</html>