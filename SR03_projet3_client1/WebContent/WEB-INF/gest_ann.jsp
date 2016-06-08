<!docTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="../style.css" />
		<title>Gestion des annonces</title>
	</head>
	<body>
		<c:import url="navbar.jsp"></c:import>
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
		<form method="post">
			<label for="nom">Nom de l'annonce </label><input type="text" name="nom" id="nom" /><br>
			<label for="categorie">Catégorie </label>
			<select id="categorie" name="categorie">
				<x:forEach var="cat" select="$doc_cat/liste/categorie">
					<option value="<x:out select="$cat/id"/>"><x:out select="$cat/nom"/></option>
				</x:forEach>
			</select><br>
			<label for="tel">Téléphone </label><input type="text" name="tel" id="tel" /><br>
			<label for="numero">Numéro </label><input type="text" name="numero" id="numero" /><br>
			<label for="rue">Rue </label><input type="text" name="rue" id="rue" /><br>
			<label for="ville">Ville </label><input type="text" name="ville" id="ville" /><br>
			<label for="cp">Code postal </label><input type="text" name="cp" id="cp" /><br>
			<input type="submit" value="créer" />
		</form>
	</body>
</html>