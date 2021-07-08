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
	<form class="form">
		<fieldset  >
				<legend><h3> Mon profil </h3></legend>
				
				 Pseudo : ${pseudo }<br>
				Nom :${nom }<br>
				Prenom : ${prenom }<br>
				email : ${email }<br>
				tel. :${telephone }<br>
				 Rue : ${rue }<br>
				Code postal : ${cp }<br>
				Ville : ${ville }<br>
				 Nombre crédit : ${credit }<br>
				
				 <a href="/ProjetEni/ServletModifierMonProfil"> Modifier </a> 
			</fieldset></form>
	</div>
	
	
		<!--  footer -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
	</jsp:include>
	
</body>
</html>