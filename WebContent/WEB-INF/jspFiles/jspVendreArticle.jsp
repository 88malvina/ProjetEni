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

</head>

<body>

	<!-- HEADER -->
	
		<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
			<jsp:param value="pageActuelle" name="inscription"/>
		</jsp:include>

	<!-- formulaire vente -->
	
		<div class="container">   	
			<div class="row">
				<form name="formulaireInscription" method="post" action="ServletVendreArticle">
					<fieldset>
						<legend>Vendre un article</legend>
						
						<div class="row">
							<div class="col-sm-12 col-md-6">
								<label for="nomArticle">Nom :</label>
								<input type="text" name="nomArticle" id="nomArticle" placeholder="Un petit nom sympa"
								size="40" maxlength="30" autofocus="autofocus" required="required" />
							</div>
						
							<div class="col-sm-12 col-md-6">
								<label for="no_categorie">Categorie :</label>
								<select name="no_categorie" size="1" required="required">
									<option value="1">Informatique</option>
									<option value="2">Ameublement</option>
									<option value="3">Vetement</option>
									<option value="4">Sport et Loisirs</option>
								</select>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-12">
								<label for="description">Description :</label>
								<textarea name="description" id="description" rows="6" cols="60" maxlength="30" required="required">Parlez un peu sur votre article...</textarea>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-12 col-md-6">
								<label for="date_debut_encheres">Date de début de l'enchère :</label>
								<input type="datetime-local" id="date_debut_encheres"
								       name="date_debut_encheres" value="${ LocalDate.now() }"
								       min="${ LocalDate.now() }" required="required">
							</div>
						
							<div class="col-sm-12 col-md-6">
								<label for="date_fin_encheres">Date de fin de l'enchère :</label>
								<input type="datetime-local" id="date_fin_encheres"
								       name="date_fin_encheres" value="${ LocalDate.now() }"
								       min="${ date_debut_encheres }" required="required">
							</div>
						</div>
						
						
						<div class="row">
						<div class="col-sm-12 col-md-6">
						<label for="prenom">Prénom :</label>
						<input type="text" name="prenom" id="prenom" placeholder="Votre prénom"
						size="40" maxlength="30" required="required" />
						</div>
						
						<div class="col-sm-12 col-md-6">
						<label for="code_postal">Code postal :</label>
						<input type="text" name="code_postal" id="code_postal" placeholder="Votre Code postal"
						size="40" maxlength="10" required="required" />
						</div>
						</div>
						
						
						<div class="row">
						<div class="col-sm-12 col-md-6">
						<label for="telephone">Téléphone :</label>
						<input type="text" name="telephone" id="telephone" placeholder="(Optionel)"
						size="40" maxlength="15" />
						</div>
						
						<div class="col-sm-12 col-md-6">
						<label for="mot_de_passe">Mot de passe :</label>
						<input type="password" name="mot_de_passe" id="mot_de_passe" placeholder="Votre mot de passe"
						size="40" maxlength="30" required="required" />
						</div>
						</div>
						
						
						<div class="row">
						<div class="col-sm-12 col-md-6">
						<label for="email">Email :</label>
						<input type="email" name="email" id="email" placeholder="Votre email"
						size="40" maxlength="20" required="required" />
						</div>
						
						
						<div class="col-sm-12 col-md-6">
						<label for="confirmation_mot_de_passe">Confirmation :</label>
						<input type="password" name="confirmation_mot_de_passe" id="confirmation_mot_de_passe" placeholder="Repetez votre mot de passe"
						size="40" maxlength="30" required="required" />
						</div>
						</div>
						
						<div class="row">
							<div class="col-sm-12 col-md-6">
								<p>
									<c:out value="${ message_erreur }" />
								</p>
							</div>
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