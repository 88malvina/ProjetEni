<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Detail : <c:out value=" ${ article.nomArticle }"></c:out></title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="Application ENI-Encheres - Afficher article">
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

<div class="container" id="container_form"> 
	<div>
		<div class="legend">
			<h2><c:out value=" Article : ${ article.nomArticle }"/></h2>
		</div>
		
		<h3 class="form_space">
			<c:out value=" Description : ${ article.description }"/>
		</h3>
		
		<h3 class="form_space">
			<c:out value=" Catégorie : ${ article.getLibelleCategorie() }"/>
		</h3>
		
		<div class="form_space">
			<a class="a" href="/ProjetEni/encheres/ServletPageDAccueil"> | Retour accueil |</a>
		</div>
		
	</div>
</div>
 				
 
 	<!--  footer -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
	</jsp:include>

</body>
</html>