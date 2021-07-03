<!------------NAVIGATION----------->
		
<nav>
	<div class="row">
	
		<!----------------- logo et nom du site -->
	
		<div class="col-sm-4" class="logo">
	 		<img src="images/logo.png" alt="Logo du site" title="logo du site" />
	 	</div>
		<div class="col-sm-4">
			<h1>ENI-Enchères</h1>
		</div>
		
		<!----------------- cas où l'utilisateur est connecté / dans la page d'accueil / 
		dans la page d'inscription -->
		
		<!-- mettre lien vers afficher profil utilisateur -->
		
		<c:choose>
			<c:when test="${ !empty utilisateur }">
				<div class="col-sm-4">
	 		        <a class="nav-link" href="#">Profil de ${ utilisateur.getPseudo() }</a>
			        <a class="nav-link" href="/ProjetEni/ServletSeDeconnecter">Se déconnecter</a>
 				</div>
			</c:when>
			<c:when test="${ empty utilisateur }">
				<c:choose>
					<c:when test="${ request.pageActuelle == inscription }">
						<div class="col-sm-4">
					        <a class="nav-link" href="/ProjetEni/encheres/ServletPageDAccueil"><c:out value="Accueil" /></a>
					        <a class="nav-link" href="/ProjetEni/ServletSeConnecter"><c:out value="Se connecter" /></a>
						</div>
					</c:when>
					<c:when test="${ request.pageActuelle == accueil }">
						<div class="col-sm-4">
							<div class="se_connecter">
								<a href="/ProjetEni/ServletVersJSPInscription">S'inscrire</a>-<a href="/ProjetEni/ServletSeConnecter">Se connecter</a>
							</div>
						</div>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>
	
	</div>
</nav>