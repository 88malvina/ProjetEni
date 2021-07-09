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
	
	<div id="container_form">
	<form id="form_inscription" class="form">
		<fieldset id="monprofil" >
				<legend class="legend">Mon profil</legend>
				<div class="form_space">
				Pseudo : ${pseudo }
				</div>
				<div class="form_space">
				Nom : ${nom }
				</div>
				<div class="form_space">
				Prenom : ${prenom }
				</div>
				<div class="form_space">
				Email : ${email }
				</div>
				<div class="form_space">
				Téléphone :${telephone }
				</div>
				<div class="form_space">
				Rue : ${rue }
				</div>
				<div class="form_space">
				Code postal : ${cp }
				</div>
				<div class="form_space">
				Ville : ${ville }
				</div>
				<div class="form_space">
				Nombre crédit : ${credit }
				</div>
				<div class="form_space">
				 <a href="/ProjetEni/ServletModifierMonProfil" class="btn btn-outline-dark btn-sm"> Modifier </a> 
				</div>
			</fieldset></form>
	</div>
	
	
		<!--  footer -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
	</jsp:include>
	
</body>
</html>