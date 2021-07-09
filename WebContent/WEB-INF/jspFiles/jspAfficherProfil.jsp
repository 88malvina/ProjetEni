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
<title>Afficher profil</title>

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

	<div id="container_form">
	<div id="form_inscription" >
	

	<form action="<%=request.getContextPath()%>/ServletAfficherProfil"
		method="post" class="form">

		<!-- Ci dessous le formulaire qui permet de récupérer le pseudo cherché  -->

		<div class=form_space>
			<label for="pseudo" class="legend">Saisissez le pseudonyme utilisateur à
				chercher :</label>
		</div>
		<div class=form_space>
			<input name="pseudo" placeholder="Saisir pseudo"
				autofocus="autofocus">
		</div>
			<input type="submit" value="Valider" class="btn btn-outline-dark btn-sm"/>
	
	<!--  Si message personalisé, on l'affiche -->
	
	<c:if test="${!empty messageJsp}">
    <p><c:out value="${messageJsp }" /> </p>
	</c:if>
	<!-- ------------------------ -->

<!-- Si le pseudo n'est pas vide, on affiche toutes les infos -->

	<c:if test="${!empty pseudo}">
	
		<fieldset  >
				<legend>Profil de ${pseudo }</legend>
				
				Nom : ${nomCherche }<br>
				Prenom : ${prenomCherche }<br>
				Email : ${emailCherche }<br>
				Tel. : ${telephoneCherche }<br>
				Rue : ${rueCherche }<br>
				Code postal : ${cpCherche }<br>
				Ville : ${villeCherche }<br>
				
			</fieldset>
		</c:if>
	</form>

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




	
	
	</div>
	</div>

	
	<!--  footer -->

	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
		<jsp:param value="footer" name="footer" />
	</jsp:include>

</body>
</html>