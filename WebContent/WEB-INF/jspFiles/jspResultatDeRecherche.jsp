<%@page import="fr.eni.projet.bo.Enchere"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Resultat de recherche</title>
</head>
<style>
	<%@include file="/css/style.css"%>
</style>
<body>
		<div class="container">
		<div class="logo">
			<h3>ENI-Encheres</h3>
		</div>
	<div class="se_connecter"><a href="/ProjetEni/ServletSeConnecter">S'inscrire-Se connecter</a></div>
	</div>
	<div class="titre"><h4>Liste des enchères</h4></div>
	<div class="liste_enchères">
	<br>
			<%if(request.getAttribute("categorie")!=null) {%>
			<c:forEach items="${categorie}" var="list">
			<div class="enchère">
		<div class="photo"><img src="https://pics.freeicons.io/uploads/icons/png/394198151553508653-512.png" width="50px" height="50px" alt="enchère"></div>
		<div class="description">
			<a href="">${list.article_vendu}</a><br>
			Prix : ${list.montant_enchere} points<br>
			Fin de l'enchère : ${list.date_enchere}<br>
			Vendeur : ${list.utilisateur}
		</div>
		</div>
			</c:forEach>
			<%} %>
			
			<%if(request.getAttribute("list_rechereche_avecNom_et_categorie")!=null) {%>
			<c:forEach items="${list_rechereche_avecNom_et_categorie}" var="list">
			<div class="enchère">
		<div class="photo"><img src="https://pics.freeicons.io/uploads/icons/png/394198151553508653-512.png" width="50px" height="50px" alt="enchère"></div>
		<div class="description">
			<a href="">${list.article_vendu}</a><br>
			Prix : ${list.montant_enchere} points<br>
			Fin de l'enchère : ${list.date_enchere}<br>
			Vendeur : ${list.utilisateur}
		</div>
		</div>
			</c:forEach>
			<%} %>
			
		<c:forEach items="${list_rechereche}" var="list">
		<div class="enchère">
		<div class="photo"><img src="https://pics.freeicons.io/uploads/icons/png/394198151553508653-512.png" width="50px" height="50px" alt="enchère"></div>
		<div class="description">
			<a href="">${list.article_vendu}</a><br>
			Prix : ${list.montant_enchere} points<br>
			Fin de l'enchère : ${list.date_enchere}<br>
			Vendeur : ${list.utilisateur}
		</div>
		</div>
		
		</c:forEach>
		
		<%if(request.getAttribute("aucune_trouvé")!=null) {%>
		<%=request.getAttribute("aucune_trouvé") %>
		<%} %>
		
		
	</div>
	<footer>
		<h3>Eni 2021</h3>
	</footer>
</body>
</html>