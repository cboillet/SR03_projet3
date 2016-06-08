<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="style.css" />
		<title>Recherche par nom</title>
	</head>
	<body>
		<c:import url="navbar.jsp"></c:import>
		
		<div class="container">
			<c:import url="menu.jsp"><c:param name="mode" value="name"/></c:import>
			
			<form class="form-horizontal" method="post">
				<fieldset>
	                <legend>Rechercher une annonce par nom</legend>
	                <div class="form-group">
						<label for="nom" class="col-sm-2 control-label">Nom de l'annonce </label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="nom" id="nom" />
						</div>
					</div>
					<div class="form-group">
	                	<div class="col-sm-offset-2 col-sm-10">
							<button class="btn btn-default" type="submit">Rechercher</button>
						</div>
					</div>
				</fieldset>
			</form><br><br>
			
			<c:if test="${ !empty list_ann }">
			    <x:parse var="doc_ann" doc="${list_ann}" />
			    <x:parse var="doc_cat" doc="${list_cat}" />
			    
			    <fieldset>
		        	<legend>Résultat de la recherche</legend>
		        	<div class="table_container">
					    <table border="1">
							<tr><th rowspan="2">ID</th><th rowspan="2">nom</th><th rowspan="2">catégorie</th><th rowspan="2">téléphone</th><th colspan="4">adresse</th></tr>
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
								</tr>
							</x:forEach>
						</table>
					</div>
				</fieldset>
			</c:if>
		</div>
	</body>
</html>