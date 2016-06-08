<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Rechercher une annonce par nom</title>
	</head>
	<body>
		<c:import url="navbar.jsp"></c:import>
		<c:import url="menu.jsp"><c:param name="mode" value="ville"/></c:import>
		
		<form method="post">
			<label for="ville">Ville de l'annonce </label><input type="text" name="ville" id="ville" /><br>
			<input type="submit" value="rechercher" />
		</form><br><br>
		
		<c:if test="${ !empty list_ann }">
		    <x:parse var="doc_ann" doc="${list_ann}" />
		    <x:parse var="doc_cat" doc="${list_cat}" />
		    
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
		</c:if>
	</body>
</html>