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
	<form method="post" action="/ProjetEni/encheres/ServletPageDAccueil"> 
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
	</form>
	<br>
	<section>
	<label for="radio-achats">
		<input type="radio" id="radio-achats" name="radio-achats" checked>Achats
			<br>
			<input type="checkbox">enchères ouvertes<br>
			<input type="checkbox">mes enchères en cours<br>
			<input type="checkbox">mes enchères remportées<br>
			
	</label>
	<label for="radio-mes_ventes">
		<input type="radio" id="radio-mes_ventes" name="radio-mes_ventes">Mes ventes
			<br>
			<input type="checkbox">mes ventes en cours<br>
			<input type="checkbox">ventes non débutées<br>
			<input type="checkbox">ventes terminées<br>
			
	</label>
	</section>
	<br>
	<button type="submit" name="rechercher">Rechercher</button>

	<div class="liste_enchères">
	<br>
		<c:forEach items="${encheres}" var="enchere">
		<div class="enchère">
		<div class="photo"><img src="https://pics.freeicons.io/uploads/icons/png/394198151553508653-512.png" width="50px" height="50px" alt="enchère"></div>
		<div class="description">
			<a href="">${enchere.nomArticle}</a><br>
			Prix : ${enchere.prixVente} points<br>
			Fin de l'enchère : ${enchere.dateFinEncheres}<br>
			Vendeur : ${enchere.no_utilisateur}
		</div>
		</div></c:forEach>
	</div>
	</div>
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
		</jsp:include>
</body>
</html>