
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
	<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
		<jsp:param value="pageActuelle" name="accueil"/>
	</jsp:include>
	<div class="container">
	<div class="titre"><h4>Liste des ench�res</h4></div>
	Filtres:<br>
	<form method="post" action=""> 
	<label>
	<button type="submit"><i class="fa fa-search"></i></button><input name="saisieUtilisateur" type="text" placeholder="Le nom de l'article contient" size=30>
	<br><br>
	Cat�gorie :
	
		<select name="select">
	<option value="" disabled selected>Toutes</option>
	<option value="informatique">Informatique</option>
	<option value="vetement">V�tement</option>
	<option value="ameublement">Ameublement</option>
	<option value="sport">Sport et Loisirs</option>
	</select>
	
	<button type="submit" name="rechercher">Rechercher</button>
	</label>
	</form>
	<div class="liste_ench�res">
	<br>
		
		<c:forEach items="${encheres}" var="enchere">
		<div class="ench�re">
		<div class="photo"><img src="https://pics.freeicons.io/uploads/icons/png/394198151553508653-512.png" width="50px" height="50px" alt="ench�re"></div>
		<div class="description">
			<a href="">${enchere.nomArticle}</a><br>
			Prix : ${enchere.prixVente} points<br>
			Fin de l'ench�re : ${enchere.dateFinEncheres}<br>
			Vendeur : ${enchere.no_utilisateur}
		</div>
		</div></c:forEach>
	</div>
	</div>
	<!--  footer -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
	</jsp:include>
	
</body>
</html>