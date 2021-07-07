
<!DOCTYPE html>
<html>

<head>

<title>Inscrivez-vous !</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Application ENI-Encheres - Page d'Inscription">
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

	<!-- formulaire -->
	
		<div class="container">   	
			<div class="row">
				<form name="formulaireInscription" method="post" action="ServletVersJSPInscription">
					<fieldset>
						<legend>Créer mon compte</legend>
					
			<!----------------------------------------------------------------------  
			memo des attributs envoyés par la servlet 	
			
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("email", email);
			request.setAttribute("telephone", telephone);
			request.setAttribute("rue", rue);
			request.setAttribute("code_postal", code_postal);
			request.setAttribute("ville", ville);
		  ----------------------------------------------------------------------->
						
						
						<div>
						<label for="pseudo">Pseudo :</label>
						<input type="text" name="pseudo" id="pseudo" placeholder="Un petit nom sympa"
						size="40" maxlength="30" autofocus="autofocus" value = "${pseudo }" required="required" />
						</div>
						
						<div>
						<label for="rue">Rue :</label>
						<input type="text" name="rue" id="rue" placeholder="Votre rue"
						size="40" maxlength="30" value = "${rue }"required="required"/>
						</div>
						
						<div>
						<label for="nom">Nom :</label>
						<input type="text" name="nom" id="nom" placeholder="Votre nom"
						size="40" maxlength="30" value = "${nom }" required="required" />
						</div>
						
						<div>
						<label for="ville">Ville :</label>
						<input type="text" name="ville" id="ville" placeholder="Votre ville"
						size="40" maxlength="30" value = "${ville }"required="required"/>
						</div>
						
						<div>
						<label for="prenom">Prénom :</label>
						<input type="text" name="prenom" id="prenom" placeholder="Votre prénom"
						size="40" maxlength="30" value = "${prenom }" required="required" />
						</div>
						
						<div>
						<label for="code_postal">Code postal :</label>
						<input type="text" name="code_postal" id="code_postal" placeholder="Votre Code postal"
						size="40" maxlength="10" value = "${code_postal }" required="required" />
						</div>
						
						<div>
						<label for="telephone">Téléphone :</label>
						<input type="text" name="telephone" id="telephone" placeholder="(Optionel)"
						size="40" maxlength="15" value = "${telephone }"/>
						</div>
						
						<div>
						<label for="email">Email :</label>
						<input type="email" name="email" id="email" placeholder="Votre email"
						size="40" maxlength="20" value = "${email }"required="required" />
						</div>
						
						<div>
						<label for="mot_de_passe">Mot de passe :</label>
						<input type="password" name="mot_de_passe" id="mot_de_passe" placeholder="Votre mot de passe"
						size="40" maxlength="30" required="required" />
						</div>

						<div>
						<label for="confirmation_mot_de_passe">Confirmation :</label>
						<input type="password" name="confirmation_mot_de_passe" id="confirmation_mot_de_passe" placeholder="Repetez votre mot de passe"
						size="40" maxlength="30" required="required" />
						</div>
						
						<div>
								<p>
									<c:out value="${ message_erreur }" />
								
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