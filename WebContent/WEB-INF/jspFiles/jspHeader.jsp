<!------------NAVIGATION----------->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.css">	
<style>
	.menu {
		background-color: #E7A8A0;
		
		}
		.nav-link {
   		 color: white;
}
</style>
<nav>
	
		<!----------------- logo et nom du site -->
		
		<nav class="menu navbar navbar-expand-sm justify-content-center">
		<a class="nav-link navbar-brand" href="/ProjetEni/encheres/ServletPageDAccueil"><img src="/ProjetEni/images/logo.png" alt="Logo du site" title="logo du site" /></a>
   	 
   	<!--  ======================== Nav Quand utilisateur connecte ============================================ -->
   	
   	<c:choose>
			<c:when test="${ !empty utilisateur }">
			
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/ProjetEni/encheres/ServletPageDAccueil">Enchères <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/ProjetEni/ServletVendreArticle">Vendre un article</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/ProjetEni/ServletModifierMonProfil">Modifier mon profil</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="/ProjetEni/ServletAfficherProfil">Afficher un profil</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/ProjetEni/ServletSeDeconnecter">Déconnexion</a>
      </li>
    </ul>
    
    <!--  ======================== Nav Quand utilisateur pas connecte ============================================ -->
    
    </c:when>
    				<c:when test="${ empty utilisateur }">
				<c:choose>
					<c:when test="${ pageActuelle == 'inscription' }">
						  <ul class="navbar-nav">
						  	<li class="nav-item">
        						 <a class="nav-link" href="/ProjetEni/encheres/ServletPageDAccueil"><c:out value="Accueil" /></a>
      						</li> 
      						<li class="nav-item">
      							 <a class="nav-link" href="/ProjetEni/ServletSeConnecter"><c:out value="Se connecter" /></a>
      						</li>
						  </ul>
					
					</c:when>
					
					<c:when test="${ pageActuelle == 'accueil' }">
					<ul class="navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href="/ProjetEni/ServletVersJSPInscription">S'inscrire</a>
						</li>
						<li class="nav-item">
						<a class="nav-link" href="/ProjetEni/ServletSeConnecter">Se connecter</a>
						</li>
					</ul>
					</c:when>
				</c:choose>
				</c:when>
		</c:choose>
</nav>
		<!--<div class="row">
		<div class="col-sm-4">
	 		<img src="images/logo.png" alt="Logo du site" title="logo du site" />
	 		</div>
	 		<c:choose>
			<c:when test="${ !empty utilisateur }">
					<div class="col-sm-4">
			       <a class="nav-link" href=""> | Enchères |</a>
			       <a class="nav-link" href="/ProjetEni/ServletVendreArticle"> | Vendre un article |</a>
			        <a class="nav-link" href="/ProjetEni/ServletAfficherProfil"> | Mon profil | </a>
			        <a class="nav-link" href="/ProjetEni/ServletSeDeconnecter"> | Se déconnecter | </a>
			        </div>
			        
			</c:when>
			
			<c:when test="${ empty utilisateur }">
				<c:choose>
					<c:when test="${ pageActuelle == 'inscription' }">
						<div class="col-sm-4">
					        <a class="nav-link" href="/ProjetEni/encheres/ServletPageDAccueil"><c:out value="Accueil" /></a>
					        <a class="nav-link" href="/ProjetEni/ServletSeConnecter"><c:out value="Se connecter" /></a>
						</div>
					</c:when>
					
					<c:when test="${ pageActuelle == 'accueil' }">
						<div class="col-sm-4">
							<div class="se_connecter">
								<a href="/ProjetEni/ServletVersJSPInscription">S'inscrire</a>-<a href="/ProjetEni/ServletSeConnecter">Se connecter</a>
							</div>
						</div>
					</c:when>
					
				
				</c:choose>
			</c:when>
		</c:choose>
	 	</div>-->
		
		<!--  si l'on veut ajouter un gros titre c'est ci dessous -->
		
		<!--   <div class="col-sm-12">
			<h1>ENI-Enchères</h1>
		</div>
		-->
		
		<!----------------- cas où l'utilisateur est connecté / dans la page d'accueil / 
		dans la page d'inscription -->
		
		<!-- mettre lien vers afficher profil utilisateur -->
		
		
	
	
</nav>