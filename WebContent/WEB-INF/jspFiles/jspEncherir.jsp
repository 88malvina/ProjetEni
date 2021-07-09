<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Enchèrir<c:out value=" ${ article.nomArticle }"></c:out></title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="Application ENI-Encheres - Enchèrir article">
	<meta name="author" content="Priscila Conchou">
	
	<!-- Bootstrap core CSS -->
	
	<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<style>
		<%@include file="/css/style.css"%>
	</style>
</head>

<body>
	<!-- HEADER -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
		<jsp:param value="pageActuelle" name="afficherArticle"/>
	</jsp:include>

<div class="container" id="container_form" > 
	<div id="form_inscription" class="form_encherir">
		<div class="legend titre">
			<h2><c:out value=" Enchèrir un article"/></h2>
		</div>
		<div class="form_space">
			<h2><c:out value="${ msg_erreur_encherir }" /></h2>
			<p>
			<c:out value="Vous avez desormais ${ utilisateur.getCredit() } crédits." />
			</p>
		</div>

	</div>
</div>
 				
 
 	<!--  footer -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
	</jsp:include>

</body>
</html>