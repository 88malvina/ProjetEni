<!-- 
Antoine
Jsp en réception de la servlet Afficher profil
Recherche d'utilisateur
Affiche infos utilisateur
nécessitant import jstl -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Je suis la jspAfficherProfil</title>

<!-- Bootstrap core CSS -->

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<style>
	<%@include file="/css/style.css"%>
</style>

</head>
<body>
	<!-- HEADER -->

	<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
		<jsp:param value="pageActuelle" name="Afficher Profil" />
	</jsp:include>


	<h2>Saisissez le pseudo de l'utilisateur à afficher</h2>

	<form action="<%=request.getContextPath()%>/ServletAfficherProfil"
		method="post">

		<!-- Ci dessous le formulaire qui permet de récupérer le pseudo cherché  -->

		<div class=formLabel>
			<label for="pseudo">Saisissez le pseudonyme utilisateur à
				chercher :</label>
		</div>
		<div class=formInput>
			<input name="pseudo" placeholder="saisir pseudo"
				autofocus="autofocus">
		</div>

		<input type="submit" value="Valider" />

	</form>

	<!-- Si le pseudo n'est pas vide, on affiche toutes les infos -->

	<c:if test="${!empty pseudo}">

		<h2>Voici les informations de l'utilisateur</h2>

	<div class="container">
	<form class="form">
		<fieldset  >
				<legend><h3> profil de ${pseudo } </h3></legend>
				
				Nom :${nomCherche }<br>
				Prenom : ${prenomCherche }<br>
				email : ${emailCherche }<br>
				tel. :${telephoneCherche }<br>
				Rue : ${rueCherche }<br>
				Code postal : ${cpCherche }<br>
				Ville : ${villeCherche }<br>
				
			</fieldset></form>
	</div>

	</c:if>

<!---

		<h4>
			Pseudo :
			<c:out value="${pseudo }" />
		</h4>
		<h4>
			Nom :
			<c:out value="${nomCherche }" />
		</h4>
		<h4>
			Prenom :
			<c:out value="${prenomCherche }" />
		</h4>
		<h4>
			email :
			<c:out value="${emailCherche }" />
		</h4>
		<h4>
			tel. :
			<c:out value="${telephoneCherche }" />
		</h4>
		<h4>
			Rue :
			<c:out value="${rueCherche }" />
		</h4>
		<h4>
			Code postal :
			<c:out value="${cpCherche }" />
		</h4>
		<h4>
			Ville :
			<c:out value="${villeCherche }" />
		</h4>

	
	
	-->


	<!-- Message par défaut si le pseudo est empty -->

	<c:if test="${empty pseudo}">
		<h4>Renseignez un pseudo existant sur le site</h4>
	</c:if>

	<a class="nav-link" href="/ProjetEni/encheres/ServletPageDAccueil">
		| Retour accueil |</a>

	<!--  footer -->

	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
		<jsp:param value="footer" name="footer" />
	</jsp:include>

</body>
</html>