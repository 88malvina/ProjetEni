<%@page import="java.awt.List"%>
<%@page import="fr.eni.projet.bo.Enchere"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultat de recherche</title>

<!-- Bootstrap core CSS -->

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<style>
	<%@include file="/css/style.css"%>
</style>

</head>

<body>
	<!-- HEADER -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
		<jsp:param value="pageActuelle" name="resultat_de_recherche"/>
	</jsp:include>

		<div id="container_form">
			<div class="titre"><h4>Résultat de recherche</h4></div>
	<div class="liste_enchères">
	<br>
			<%if(request.getAttribute("list_encheres")!=null) {%>
			<c:forEach items="${list_encheres}" var="list">
			<div class="form">
		<div class="photo"><img src="https://pics.freeicons.io/uploads/icons/png/394198151553508653-512.png" width="50px" height="50px" alt="enchère"></div>
		<div class="description">
			<a href="">${list.nomArticle}</a><br>
			Prix : ${list.prixVente} points<br>
			Fin de l'enchère : ${list.dateFinEncheres}<br>
			Vendeur : ${list.pseudo}
		</div>
		</div>
			</c:forEach>
			<%} %>
			
	
		
		<%if(request.getAttribute("mes_articles")!=null) {%>
		<c:forEach items="${mes_articles}" var="enchere">
		<div class="form">
		<div class="photo"><img src="https://pics.freeicons.io/uploads/icons/png/394198151553508653-512.png" width="50px" height="50px" alt="enchère"></div>
		<div class="description">
			<a href="">${enchere.nomArticle}</a><br>
			Prix : ${enchere.prixVente} points<br>
			Fin de l'enchère : ${enchere.dateFinEncheres}<br>
			Vendeur : ${enchere.pseudo}
		</div>
		</div></c:forEach>
		<%} %>
	
	</div>
	</div>
	<!--  footer -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
	</jsp:include>
	
</body>
</html>