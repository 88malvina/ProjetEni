<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>Mode Connecté</title>
</head>
<style>
	<%@include file="/css/style.css"%>
	
</style>
<body>

<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
		<jsp:param value="pageActuelle" name="inscription"/>
	</jsp:include>
	<div class="container">
	<!-- Message de confirmation si suppression de compte vient d'être effectué -->
	<c:if test="${!empty compteSupprime}">
    <p><c:out value="${compteSupprime }" /> </p>
	</c:if>
	<!-- ------------------------ -->
	
	<!-- Message de confirmation si l'on vient juste de se connecter -->
	<c:if test="${!empty messageLog}">
    <p><c:out value="${messageLog }" /> </p>
	</c:if>
	<!-- ------------------------ -->
	
	<div class="titre"><h4>Liste des enchères</h4></div>
	Filtres:<br>
	
	<form action="/ProjetEni/ServletModeConnecte" method="post"> 
	<label>
	<button type="submit"><i class="fa fa-search"></i></button><input name="saisieUtilisateur" type="text" placeholder="Le nom de l'article contient" size=30>
	<br><br>
	Catégorie :
	
		<select name="select">
	<option value="" disabled selected>Toutes</option>
	<option value="informatique">Informatique</option>
	<option value="vetement">Vêtement</option>
	<option value="ameublement">Ameublement</option>
	<option value="sport">Sport et Loisirs</option>
	</select>
	</label>
	
	<br>
	<section>
	<label for="radio-achats">
		<input type="radio" value="radio-achats" name="radio_button" checked>Achats
			<br>
			<input type="checkbox" name="encheres" value="encheres_ouvertes"/>enchères ouvertes<br>
			<input type="checkbox" name="encheres" value="encheres_en_cours"/>mes enchères en cours<br>
			<input type="checkbox" name="encheres" value="remportees"/>mes enchères remportées<br>
			
	</label>
	<label for="radio-mes_ventes">
		<input type="radio" value="radio-mes_ventes" name="radio_button">Mes ventes
			<br>
			<input type="checkbox" name="ventes" value="ventes_en_cours"/>mes ventes en cours<br>
			<input type="checkbox" name="ventes" value="ventes_non_debutees"/>ventes non débutées<br>
			<input type="checkbox" name="ventes" value="ventes_terminees"/>ventes terminées<br>
			
	</label>
	</section>
	<br>
	<button type="submit" name="rechercher">Rechercher</button>
	</form>
	<div class="liste_enchères">
	<br>
		<div id="list">		
		</div>
		
	</div>
	</div>

	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
		</jsp:include>
</body>
</html>