<!-- 
Antoine
Jsp en réception de la servlet Afficher profil
Recherche d'utilisateur
Affiche infos utilisateur
nécessitant import jstl (fichier lib et aussi ligne ci dessous) -

 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Je suis la jspAfficherProfil</title>
</head>
<body>

<h1>Bonjour je suis la jsp afficher profil</h1>

<h2> Saisissez le pseudo de l'utilisateur à afficher</h2>

	<form action="<%=request.getContextPath()%>/ServletAfficherProfil" method="post"></form>
						
				<!-- TODO : L'autofocus ne marche pas -->
				<!-- TODO : mise en page CSS -->
				
				<!-- Ci dessous le formulaire qui permet de récupérer le pseudo cherché  -->
				
				
				
				<div class=formLabel>
				<label for="pseudo">Saisissez le pseudonyme utilisateur à chercher :</label>
				</div>
				<div class=formInput>
				<input name="pseudo" placeholder="saisir pseudo" autofocus>
				</div>
				
				<div class=formLabel>
				<input type="submit" value="Valider"/>
				<a href="<%=request.getContextPath()%>"><input type="button" value="Annuler"/></a>
				</div>
				
				<!-- Si le pseudo n'est pas vide, on affiche toutes les infos -->
				
				<c:if test="${!empty pseudo}"> 
				
				<h2> Voici les informations de l'utilisateur </h2>
				<h3> <c:out value="${nomCherche }" /> </h3>
				
					
				</h3>
				  

				
			request.setAttribute("nomCherche", nomCherche);
			request.setAttribute("prenomCherche", prenomCherche);
			request.setAttribute("emailCherche", emailCherche);
			request.setAttribute("telephoneCherche", telephoneCherche);
			request.setAttribute("rueCherche", rueCherche);
			request.setAttribute("cpCherche", cpCherche);
			request.setAttribute("villeCherche", villeCherche);
			
				
				
				</c:if>
				

</body>
</html>