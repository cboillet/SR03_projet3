<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="../style.css" />
		<title>Gestion des catégories</title>
	</head>
	<body>
		<c:import url="navbar.jsp"></c:import>
		<c:import url="menu.jsp"><c:param name="mode" value="cat"/></c:import>
		
		<x:parse var="doc" doc="${list_cat}" />
		<table border="1">
			<tr><th>ID</th><th>nom</th><th>action</th></tr>
			<x:forEach var="cat" select="$doc/liste/categorie">
				<tr>
					<td><x:out select="$cat/id"/></td>
					<td><x:out select="$cat/nom"/></td>
					<td><a href="modifier_categorie?id=<x:out select="$cat/id"/>">modifier</a><br><a href="supprimer_categorie?id=<x:out select="$cat/id"/>">supprimer</a></td>
				</tr>
			</x:forEach>
		</table>
		<br><br>
		<form method="post">
			<label for="nom">Nom de la catégorie </label><input type="text" name="nom" id="nom" /><br>
			<input type="submit" value="créer" />
		</form>
	</body>
</html>