<!-- 
Antoine
Jsp en réception de la servlet Afficher profil
Recherche d'utilisateur
Affiche infos utilisateur
nécessitant import jstl (fichier lib et aussi ligne ci dessous) -

 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Je suis la jspAfficherProfil</title>
</head>
<body>

<h1>Bonjour je suis la jsp afficher profil</h1>

<h2> Saisissez le pseudo de l'utilisateur à afficher</h2>

	<form action="<%=request.getContextPath()%>/ServletAfficherProfil" method="post">
						
				<!-- TODO : L'autofocus ne marche pas -->
				<!-- TODO : mise en page CSS -->
				
				<!-- Ci dessous le formulaire qui permet de récupérer le pseudo cherché  -->
				
				<div class=formLabel>
				<label for="pseudo">Saisissez le pseudonyme utilisateur à chercher :</label>
				</div>
				<div class=formInput>
				<input name="pseudo" placeholder="saisir pseudo" autofocus>
				</div>
				
				<input type="submit" value="Valider"/>
				
				</form>

				<!-- Si le pseudo n'est pas vide, on affiche toutes les infos -->
				
				<c:if test="${!empty pseudo}"> 
				
				<h2> Voici les informations de l'utilisateur </h2>
				<h3> <c:out value="${pseudo }" /> </h3>
				<h3> <c:out value="${nomCherche }" /> </h3>
				<h3> <c:out value="${prenomCherche }" /> </h3>
				<h3> <c:out value="${emailCherche }" /> </h3>
				<h3> <c:out value="${telephoneCherche }" /> </h3>
				<h3> <c:out value="${rueCherche }" /> </h3>
				<h3> <c:out value="${cpCherche }" /> </h3>
				<h3> <c:out value="${villeCherche }" /> </h3>
				<h3> <c:out value="${nomCherche }" /> </h3>
				
				</c:if>
				
				<!-- Message par défaut si le pseudo est empty -->
				
				<c:if test="${empty pseudo}"> 
				<h3> Renseignez un pseudo existant sur le site </h3>
				</c:if>
				
</body>
</html>