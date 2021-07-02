
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>A Web Page</title>
</head>
<style>
	<%@include file="/WEB-INF/css/style.css"%>
</style>
<body>
	<div class="container">
	
	<!-- HEADER -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
		<jsp:param value="pageActuelle" name="accueil"/>
	</jsp:include>
		
	<!-- 		<div class="logo"> -->
	<!-- 			<h3>ENI-Encheres</h3> -->
	<!-- 		</div> -->
	<!-- 	<div class="se_connecter"><a href="/ProjetEni/ServletVersJSPInscription">S'inscrire</a>-<a href="/ProjetEni/ServletSeConnecter">Se connecter</a></div> -->
	</div>
	
	
	<div class="titre"><h4>Liste des enchères</h4></div>
	Filtres:<br>
	<form method="post" action=""> 
	<label>
	<button type="submit"><i class="fa fa-search"></i></button><input name="saisieUtilisateur" type="text" placeholder="Le nom de l'article contient" size=30>
	<br><br>
	Catégorie :
	
	<select name="select">
	<option value="" disabled selected>Toutes</option>
	<option value="ameublement">Ameublement</option>
	<option value="vetement">Vêtement</option>
	<option value="sport">Sport et Loisirs</option>
	</select>
	
	<button type="submit" name="rechercher">Rechercher</button>
	</label>
	</form>
	<div class="liste_enchères">
	<br>
		
		<c:forEach items="${encheres}" var="enchere">
		<div class="enchère">
		<div class="photo"><img src="https://pics.freeicons.io/uploads/icons/png/394198151553508653-512.png" width="50px" height="50px" alt="enchère"></div>
		<div class="description">
			<a href="">${enchere.article_vendu}</a><br>
			Prix : ${enchere.montant_enchere} points<br>
			Fin de l'enchère : ${enchere.date_enchere}<br>
			Vendeur : ${enchere.utilisateur}
		</div>
		</div></c:forEach>
	</div>
	<footer>
		<h3>Eni 2021</h3>
	</footer>
	
</body>
</html>