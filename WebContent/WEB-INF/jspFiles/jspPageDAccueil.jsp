<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>A Web Page</title>
</head>
<style>
	<%@include file="/WEB-INF/css/style.css"%>
</style>
<body>
	<div class="container">
		<div class="logo">
			<h3>ENI-Encheres</h3>
		</div>
	<div class="se_connecter"><a href="/ServletCréerParAntoine">S'inscrire-Se connecter</a></div>
	</div>
	<div class="titre"><h4>Liste des enchères</h4></div>
	Filtres:<br>
	<label>
	<button type="submit"><i class="fa fa-search"></i></button><input type="text" placeholder="Le nom de l'article contient" size=30">
	<br><br>
	Catégorie :
	<select>
	<option value="" disabled selected>Toutes</option>
	<option value="ameublement">Ameublement</option>
	<option value="vetement">Vêtement</option>
	<option value="sport">Sport & Loisirs</option>
	</select>
	
	<button>Rechercher</button>
	</label>
	<div class="liste_enchères">
	<br>
		<div class="enchère">
		<div class="photo"><img src="https://pics.freeicons.io/uploads/icons/png/394198151553508653-512.png" width="50px" height="50px" alt="enchère"></div>
		<div class="description">
			<a href="">PC Gamer pour travailler</a><br>
			Prix : 210 points<br>
			Fin de l'enchère : <br>
			Vendeur : 
		</div>
		</div>
	</div>
	<footer>
		<h3>Eni 2021-Groupe Java Ninjas</h3>
	</footer>
	
</body>
</html>