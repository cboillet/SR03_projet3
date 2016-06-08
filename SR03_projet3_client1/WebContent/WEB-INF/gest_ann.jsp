<!docTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="style.css" />
		<title>Gestion des annonces</title>
	</head>
	<body>
		<c:import url="navbar.jsp"></c:import>
		
		<div class="container">
			<c:import url="menu.jsp"><c:param name="mode" value="ann"/></c:import>
			
			<x:parse var="doc_ann" doc="${list_ann}" />
			<x:parse var="doc_cat" doc="${list_cat}" />
			<table border="1">
				<tr><th rowspan="2">ID</th><th rowspan="2">nom</th><th rowspan="2">catégorie</th><th rowspan="2">téléphone</th><th colspan="4">adresse</th><th rowspan="2">action</th></tr>
				<tr><th>numéro</th><th>rue</th><th>ville</th><th>code postal</th></tr>
				<x:forEach var="ann" select="$doc_ann/liste/annonce">
					<x:set var="adresse" select="$ann/adresse" />
					<tr>
						<td><x:out select="$ann/id"/></td>
						<td><x:out select="$ann/nom"/></td>
						<td><x:out select="$doc_cat/liste/categorie[id=$ann/cat]/nom"/></td>
						<td><x:out select="$ann/telephone"/></td>
						<td><x:out select="$adresse/numero"/></td>
						<td><x:out select="$adresse/rue"/></td>
						<td><x:out select="$adresse/ville"/></td>
						<td><x:out select="$adresse/codepostal"/></td>
						<td><a href="modifier_annonce?id=<x:out select="$ann/id"/>">modifier</a><br><a href="supprimer_annonce?id=<x:out select="$ann/id"/>">supprimer</a></td>
					</tr>
				</x:forEach>
			</table>
			<br><br>
			<form class="form-horizontal" method="post">
				<fieldset>
	                <legend>Créer une nouvelle annonce</legend>
	                <div class="form-group">
						<label for="nom" class="col-sm-2 control-label">Nom de l'annonce </label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="nom" id="nom" value="<x:out select="$doc_ann/annonce/nom"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="categorie" class="col-sm-2 control-label">Catégorie </label>
						<div class="col-sm-10">
							<select class="form-control" id="categorie" name="categorie">
								<x:forEach var="cat" select="$doc_cat/liste/categorie">
									<option value="<x:out select="$cat/id"/>" <x:if select="$doc_ann/annonce[cat=$cat/id]">selected</x:if>><x:out select="$cat/nom"/></option>
								</x:forEach>
							</select>
						</div>
					</div>						
					<div class="form-group">
						<label for="tel" class="col-sm-2 control-label">Téléphone </label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="tel" id="tel" value="<x:out select="$doc_ann/annonce/telephone"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="numero" class="col-sm-2 control-label">Numéro </label>
						<div class="col-sm-10">
							<input class="form-control" type="number" name="numero" id="numero" value="<x:out select="$doc_ann/annonce/adresse/numero"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="rue" class="col-sm-2 control-label">Rue </label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="rue" id="rue" value="<x:out select="$doc_ann/annonce/adresse/rue"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="ville" class="col-sm-2 control-label">Ville </label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="ville" id="ville" value="<x:out select="$doc_ann/annonce/adresse/ville"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="cp" class="col-sm-2 control-label">Code postal </label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="cp" id="cp" value="<x:out select="$doc_ann/annonce/adresse/codepostal"/>" />
						</div>
					</div>
					<div class="form-group">
	                	<div class="col-sm-offset-2 col-sm-10">
							<button class="btn btn-default" type="submit">Créer</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</body>
</html>