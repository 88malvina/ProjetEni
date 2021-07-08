<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon profil</title>
<!-- Bootstrap core CSS -->

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<style>
	<%@include file="/css/style.css"%>
</style>

</head>
<body>
	<!-- HEADER -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
		<jsp:param value="pageActuelle" name="Mon Profil"/>
	</jsp:include>
	
	<!-- On NE FAIT QU AFFICHER ici -->
	
	<div class="container">
	
				<h3> Mon profil </h3>
				<h4> Pseudo : ${pseudo }</h4>
				<h4> Nom :${nom }</h4>
				<h4> Prenom : ${prenom }</h4>
				<h4> email : ${email }</h4>
				<h4> tel. :${telephone }</h4>
				<h4> Rue : ${rue } </h4>
				<h4> Code postal : ${cp }</h4>
				<h4> Ville : ${ville }</h4>
				<h3> Nombre crédit : ${credit } </h3>
				
				<h3> <a href="/ProjetEni/ServletModifierMonProfil"> Modifier </a> </h3>
				
	</div>
	
	
		<!--  footer -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
	</jsp:include>
	
</body>
</html>