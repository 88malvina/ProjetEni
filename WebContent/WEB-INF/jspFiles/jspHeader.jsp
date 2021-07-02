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
		
		
		
		<!----------------- cas où l'utilisateur n'est pas connecté -->
		
<%-- 		<c:if test=" $ { 50=50 } "> --%>
			
<!-- 			<!----------------- si il est dans la page d'inscription --> 
<%-- 			<c:if test=" $ { pageInscription } "> --%>
				<div class="col-sm-4">
					
			        <a class="nav-link" href="/ProjetEni/encheres/ServletPageDAccueil">Accueil</a>
			        <a class="nav-link" href="/ProjetEni/ServletSeConnecter">Se connecter</a>
				</div>
<%-- 			</c:if> --%>
			
			<!----------------- si il est dans la page d'Accueil -->
<%-- 			<c:if test=" $ { pageActuelle = accueil } "> --%>
<!-- 				<div class="col-sm-4"> -->
<!-- 					<div class="se_connecter"> -->
<!-- 						<a href="/ProjetEni/ServletVersJSPInscription">S'inscrire</a>-<a href="/ProjetEni/ServletSeConnecter">Se connecter</a></div> -->
<!-- 				</div> -->
<%-- 			</c:if> --%>
			
<%-- 		</c:if> --%>
		
		<!----------------- cas où l'utilisateur est connecté -->
		
<%-- 		<c:if test=" $ { !empty session.getAttribute(utilisateur) } "> --%>
<!-- 			<div class="col-sm-4"> -->
<!-- 		        <a class="nav-link" href="/ProjetEni/encheres/ServletPageDAccueil">Profil de $ { utilisateur.pseudo }</a> -->
<!-- 		        <a class="nav-link" href="/ProjetEni/ServletSeDeconnecter">Se deconnecter</a> -->
<!-- 			</div> -->
<%-- 		</c:if> --%>
		
	</div>
</nav>