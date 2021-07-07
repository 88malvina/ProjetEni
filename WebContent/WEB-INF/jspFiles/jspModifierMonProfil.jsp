<!-- 
Antoine
Jsp en réception de la servlet Modifier profil
Affiche formulaire prérempli et permet l'update
nécessitant import jstl -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Je suis la jspModifierMonProfil</title>

<!-- Bootstrap core CSS -->

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<style>
	<%@include file="/css/style.css"%>
</style>

</head>

<body>

	<!-- HEADER -->
	
	<jsp:include page="/WEB-INF/jspFiles/jspHeader.jsp">
		<jsp:param value="pageActuelle" name="Modifier mon Profil"/>
	</jsp:include>
	
	<!-- Si une modif profil a été tentée et a échoué, on affiche un message d'erreur -->
	
	<c:if test="${!empty message}"> 
		<h4> <c:out value="${message }" /> </h4>
	</c:if>
	
	<!-- Si une modif vient d'être faite et a réussi, on affiche message succès -->
	
	<c:if test="${!empty modifOk}"> 
		<h4> <c:out value="${modifOk }" /> </h4>
	</c:if>

	<!-- formulaire ----------------------------------------------------------------- -->
	
		<div class="container">   	
			<div class="row">
				<form name="Modifier mon profil" method="post" action="ServletModifierMonProfil">
					<fieldset>
						<legend>Modifier mon profil</legend>
						
			<!-- Memo des attributs envoyés ici depuis la servlet
			
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("prenom", prenom);
			request.setAttribute("nom", nom);
			request.setAttribute("email", email);
			request.setAttribute("telephone", telephone);
			request.setAttribute("rue", rue);
			request.setAttribute("cp", cp);
			request.setAttribute("ville", ville);
			request.setAttribute("motDePasse", motDePasse); -->
						
						<div>
						<label for="pseudo">Pseudo :</label>
						<input type="text" name="pseudo" id="pseudo" value = "${pseudo }"
						size="40" maxlength="30" autofocus="autofocus" required="required" />
						</div>
						
						<div>
						<label for="prenom">Prenom :</label>
						<input type="text" name="prenom" id="prenom" value = "${prenom }"
						size="40" maxlength="30" required="required" />
						</div>
						
						<div>
						<label for="nom">Nom :</label>
						<input type="text" name="nom" id="nom" value = "${nom }"
						size="40" maxlength="30" required="required" />
						</div>
						
						<div>
						<label for="email">Email :</label>
						<input type="email" name="email" id="email" value = "${email }"
						size="40" maxlength="20" required="required" />
						</div>					

						<div>
						<label for="telephone">Téléphone :</label>
						<input type="text" name="telephone" id="telephone" value = "${telephone }"
						size="40" maxlength="15" />
						</div>						
						
						<div>
						<label for="rue">Rue :</label>
						<input type="text" name="rue" id="rue" value = "${rue }"
						size="40" maxlength="30" required="required"/>
						</div>
						
						<div>
						<label for="code_postal">Code postal :</label>
						<input type="text" name="code_postal" id="code_postal" value = "${cp }"
						size="40" maxlength="10" required="required" />
						</div>
						
						<div>
						<label for="ville">Ville :</label>
						<input type="text" name="ville" id="ville" value = "${ville }"
						size="40" maxlength="30" required="required"/>
						</div>
						
						<div>
						<label for="mot_de_passe">Mot de passe :</label>
						<input type="password" name="mot_de_passe" id="mot_de_passe" value = "${motDePasse }"
						size="40" maxlength="30" required="required" />
						</div>
						
						<div>
						<label for="confirmation_mot_de_passe">Confirmation :</label>
						<input type="password" name="confirmation_mot_de_passe" id="confirmation_mot_de_passe" placeholder="Repetez votre mot de passe"
						size="40" maxlength="30" required="required" />
						</div>
						
						
						<!-------------------------------- BUTTONS -------------------------->
						
						<div class="row">
							<div class="col-sm-4">
								<input type="submit" value="Valider" class="btn btn-outline-dark btn-sm">
							</div>
							
							<div class="col-sm-4">
								<a href="/ProjetEni/encheres/ServletPageDAccueil"><input type="button" value="Annuler" class="btn btn-outline-dark btn-sm"></a>
							</div>
							
							<div class="col-sm-4">
								<a href="/ProjetEni/ServletSupprimerMonCompte"><input type="button" value="Supprimer mon compte" class="btn btn-outline-dark btn-sm"></a>
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