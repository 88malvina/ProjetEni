<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<title>Inscrivez-vous !</title>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Application ENI-Encheres - Page d'Inscription">
<meta name="author" content="Priscila Conchou">

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- CSS personalisé -->
<link href="css/JSPInscription.css" rel="stylesheet">

</head>

<body>
	<!------------NAVIGATION----------->
		
		<nav>
			<div class="row">
			<div class="col-sm-4">
		 		<img src="images/logo.png" alt="Logo du site" title="logo du site" />
		 	</div>
		 	<div class="col-sm-4">
		        <h1>ENI-Enchères</h1>
		   </div>
		   <div class="col-sm-4">
		        <a class="nav-link" href="/ProjetEni/encheres/ServletPageDAccueil">Accueil</a>
		   </div>
	       </div>
	    </nav>

	<!-- formulaire -->
	      	
			<div class="row">
				<form name="formulaireInscription" method="post" action="#">
					<fieldset>
						<legend>Créer mon compte</legend>
						
						<div class="row">
						<div class="col-sm-12 col-md-6">
						<label for="pseudo">Pseudo :</label>
						<input type="text" name="pseudo" id="pseudo" placeholder="Un petit nom sympa"
						size="40" maxlength="30" autofocus="autofocus" required="required" />
						</div>
						
						<div class="col-sm-12 col-md-6">
						<label for="prenom">Prénom :</label>
						<input type="text" name="prenom" id="prenom" placeholder="Votre prénom"
						size="40" maxlength="30" required="required" />
						</div>
						</div>
						
						
						<div class="col-sm-12 col-md-6">
						<label for="telephone">Téléphone :</label>
						<input type="text" name="telephone" id="telephone" placeholder="(Optionel)"
						size="40" maxlength="15" />
						</div>
						
						<div class="col-sm-12 col-md-6">
						<label for="code_postal">Code postal :</label>
						<input type="text" name="code_postal" id="code_postal" placeholder="Votre Code postal"
						size="40" maxlength="10" required="required" />
						</div>
						
						<div class="col-sm-12 col-md-6">
						<label for="mot_de_passe">Mot de passe :</label>
						<input type="text" name="mot_de_passe" id="mot_de_passe" placeholder="Votre mot de passe"
						size="40" maxlength="30" required="required" />
						</div>
						
						
						
						<div class="col-sm-12 col-md-6">
						<label for="email">Email :</label>
						<input type="text" name="email" id="email" placeholder="Votre email"
						size="40" maxlength="20" required="required" />
						</div>
						
						<div class="col-sm-12 col-md-6">
						<label for="rue">Rue :</label>
						<input type="text" name="rue" id="rue" placeholder="Votre rue"
						size="40" maxlength="30" required="required"/>
						</div>
						
						<div class="col-sm-12 col-md-6">
						<label for="ville">Ville :</label>
						<input type="text" name="ville" id="ville" placeholder="Votre ville"
						size="40" maxlength="30" required="required"/>
						</div>
						
						<div class="col-sm-12 col-md-6">
						<label for="confirmation_mot_de_passe">Confirmation :</label>
						<input type="text" name="confirmation_mot_de_passe" id="confirmation_mot_de_passe" placeholder="Repetez votre mot de passe"
						size="40" maxlength="30" required="required" />
						</div>
					</fieldset>
				</form>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<input type="submit" value="Valider" class="btn btn-outline-dark btn-sm">
				</div>
			</div>	

</body>

</html>