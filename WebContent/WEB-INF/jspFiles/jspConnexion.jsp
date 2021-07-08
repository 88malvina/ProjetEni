<!-- 
Antoine
Jsp en réception de la servlet Se Connecter
Contenant un formulaire simple ff
nécessitant import jstl -->
    
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

	<div class="container" id="container_form">
	
		<!-- Message si erreur lors tentative de log -->
		
		<c:if test="${!empty erreurLog}">
	    <p><c:out value="${erreurLog }" /> </p>
		</c:if>
		
		<!-- ------------------------ -->
	
	
		<form action="<%=request.getContextPath()%>/ServletSeConnecter" method="post" id="form_inscription">
						
			<!-- TODO : L'autofocus ne marche pas -->
			<!-- TODO : mise en page CSS -->
			
			<fieldset>
				<legend class="legend">Se connecter</legend>
				
				
				<div class=form_space>
				<label for="pseudo">Pseudo :</label>
				
				<input name="pseudo" placeholder="Mon pseudo" value="${pseudo}"required autofocus>
				</div>
				
				<div class=form_space>
				<label for="password">Mot de passe :</label>
				
				<input name="password" placeholder="Mon mot de passe" required type="password">
				</div>
				
				<div class=form_space>
				
				<input name="seSouvenirDeMoi" type="checkbox" checked>
				<label for="souvenirDeMoi">Se souvenir de moi</label>
				
				</div>
				
				<div class="row">

					<div>
					<input type="submit" value="Valider" class="btn btn-outline-dark btn-sm" >
					</div>
					
				</div>
				<div class="a">
					<a href ="<%=request.getContextPath()%>/ServletVersJSPInscription" class="a"> Créer un compte</a>
				</div>
			</fieldset>
		</form>	
		
	</div>

	

	<!--  footer -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
	</jsp:include>

</body>
</html>