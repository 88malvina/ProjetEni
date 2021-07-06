<!-- 
Antoine
Jsp en r�ception de la servlet Se Connecter
Contenant un formulaire simple ff
n�cessitant import jstl -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Se connecter</title>

<!-- Bootstrap core CSS -->

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<style>
	<%@include file="/css/style.css"%>
</style>

</head>


<body>

	<!-- HEADER -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
		<jsp:param value="pageActuelle" name="Connexion"/>
	</jsp:include>

	<!-- Message si erreur lors tentative de log -->
	
	<c:if test="${!empty erreurLog}">
    <p><c:out value="${erreurLog }" /> </p>
	</c:if>
	
	<!-- ------------------------ -->

<h1> Se connecter</h1>

		<form action="<%=request.getContextPath()%>/ServletSeConnecter" method="post">
						
				<!-- TODO : L'autofocus ne marche pas -->
				<!-- TODO : mise en page CSS -->
				
				<div class=formLabel>
				<label for="pseudo">pseudo :</label>
				</div>
				<div class=formInput>
				<input name="pseudo" placeholder="Mon pseudo" required autofocus>
				</div>
				
				<div class=formLabel>
				<label for="password">Mot de passe :</label>
				</div>
				<div class=formInput>
				<input name="password" placeholder="Mon mot de passe" required type="password">
				</div>
				
				<div class=formLabel>
				<label for="souvenirDeMoi">Se souvenir de moi :</label>
				</div>
				<div class=formInput>
				<input name="seSouvenirDeMoi" type="checkbox">
				</div>
				
				<div class=formLabel>
				<input type="button" value = "Oubli mot de passe">
				</div>
				
				<input type="submit" value="Valider"/>
				
				<!-- TODO : lien vers la page d'inscription a faire-->
				
				<div class=formLabel>
				<a href ="<%=request.getContextPath()%>/ServletVersJSPInscription"> Cr�er un compte</a>

				</div>
			
		</form>

	<!--  footer -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
	</jsp:include>

</body>
</html>