<!DOCTYPE html>
<html>
<head>
	<title>Vendre un article</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="Application ENI-Encheres - Vendre un article">
	<meta name="author" content="Priscila Conchou">
	
	<!-- Bootstrap core CSS -->
	
	<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<style>
		<%@include file="/css/style.css"%>
	</style>
	
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Trirong">


</head>

<body>

	<!-- HEADER -->
	
		<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
			<jsp:param value="pageActuelle" name="inscription"/>
		</jsp:include>

	<!-- formulaire vente -->
	
		<div class="container" id="container_form">   	
			<div class="row">
				<form name="formulaireInscription" method="post" action="ServletVendreArticle" id="form_inscription">
					<fieldset>
						<legend class="legend">Modifier mon article</legend>
						
							<div class="form_space">
								<label for="nom_article">Nom :</label>
								<input type="text" name="nom_article" id="nom_article" value="${ article.nomArticle }"
								size="40" maxlength="30" autofocus="autofocus" required="required" />
							</div>
							<div class="form_space">
								<label for="no_categorie">Categorie :</label>
								<select name="no_categorie" size="1" required="required" value="${ article.getLibelleCategorie() }">
									<option value="1">Informatique</option>
									<option value="2">Ameublement</option>
									<option value="3">Vetement</option>
									<option value="4">Sport et Loisirs</option>
								</select>
							</div>
						
							<div class="form_space">
								<label for="description">Description :</label>
								<textarea name="description" id="description" rows="3" cols="40" maxlength="300" 
								value="${ article.description }" required="required"></textarea>
							</div>
						
							<div class="form_space">
								<label for="date_debut_encheres">Date début de l'enchère :</label>
								<input type="date" id="date_debut_encheres"
								       name="date_debut_encheres" required="required" value="${ article.dateDebutEncheres }">
							</div>
						
							<div class="form_space">
								<label for="date_fin_encheres">Date fin de l'enchère :</label>
								<input type="date" id="date_fin_encheres"
								       name="date_fin_encheres" required="required" value="${ article.dateFinEncheres }">
							</div>
						
							<div class="form_space">
								<label for="prix_initial">Prix de départ :</label>
								<input type="number" value="${ article.miseAPrix }" name="prix_initial" id="prix_initial"/>
							</div>
						
							<div class="form_space">
								<label for="rue">Rue :</label>
								<input type="text" name="rue" id="rue" placeholder="Votre rue"
								size="40" maxlength="30" value="${ utilisateur.getRue() }" required="required"/>
							</div>
							
							<div class="form_space">
								<label for="code_postal">Code postal :</label>
								<input type="text" name="code_postal" id="code_postal" placeholder="Votre Code postal"
								size="40" maxlength="10" value="${ utilisateur.getCodePostal() }" required="required" />
							</div>
							
							<div class="form_space">
								<label for="ville">Ville :</label>
								<input type="text" name="ville" id="ville" placeholder="Votre ville"
								size="40" maxlength="30" value="${ utilisateur.getVille() }" required="required"/>
							</div>
						
							<div>
								<p>
									<c:out value="${ message_erreur_article }" />
								</p>
							</div>
						
						
						
						<!-------------------------------- BUTTONS -------------------------->
						
						<div class="row">
							<div class="col-sm-4">
								<input type="submit" value="Valider" class="btn btn-outline-dark btn-sm">
							</div>
							
							<div class="col-sm-4">
								<input type="reset" value="Réinitialiser" class="btn btn-outline-dark btn-sm">
							</div>
							
							<div class="col-sm-4">
								<a href="/ProjetEni/encheres/ServletPageDAccueil"><input type="button" value="Annuler" class="btn btn-outline-dark btn-sm"></a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
				
		</div>
		<!---------------------------------- ADD FOOTER -------------------------->
		
		<jsp:include page="/WEB-INF/jspFiles/jspFooter.jsp">
			<jsp:param value="footer" name="footer"/>
		</jsp:include>
</body>
</html>