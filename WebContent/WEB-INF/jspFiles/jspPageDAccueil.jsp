
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>A Web Page</title>
</head>
<style>
	<%@include file="/css/style.css"%>
</style>
<body>

	<!-- Message de confirmation si suppression de compte vient d'être effectué -->
	<c:if test="${!empty compteSupprime}">
    <p><c:out value="${compteSupprime }" /> </p>
	</c:if>
	<!-- ------------------------ -->


	<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
		<jsp:param value="pageActuelle" name="accueil"/>
	</jsp:include>
	<div id="container_form">
	<div id="form_inscription">
	<div id="content">
	<div class="recherche">
		<div class="legend titre">Liste des enchères</div>
	Filtres:<br>
	<form method="post" action=""> 
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
	
	<button type="submit" name="rechercher">Rechercher</button>
	</label>
	</form>
	</div>
	
	<div class="liste_encheres">
	<br>
		
		<c:forEach items="${encheres}" var="enchere">
		<div class="form">
		<div class="photo"><img src="https://pics.freeicons.io/uploads/icons/png/394198151553508653-512.png" width="50px" height="50px" alt="enchère"></div>
		<div class="description">
			<a href="/ProjetEni/ServletAfficherArticle?action=${ enchere.getNoArticle() }">${enchere.nomArticle}</a><br>
			Prix : ${enchere.prixVente} points<br>
			Fin de l'enchère : ${enchere.dateFinEncheres}<br>
			Vendeur : ${enchere.pseudo}
		</div>
		</div></c:forEach>
	</div>
	</div>
	</div>
	
	</div>
	
	<!--  footer -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
	</jsp:include>
	
</body>
</html>