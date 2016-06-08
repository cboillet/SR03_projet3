<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="../style.css" />
		<title>Modifier une catégorie</title>
	</head>
	<body>
		<c:import url="navbar.jsp"></c:import>
		<c:import url="menu.jsp"><c:param name="mode" value="cat"/></c:import>
		
		<x:parse var="doc" doc="${categorie}" />
		<br><br>
		<form method="post">
			<label for="nom">Nouveau nom </label><input type="text" name="nom" id="nom" value="<x:out select="$doc/categorie/nom"/>"/>
			<input type="submit" value="mettre à jour" />
		</form>
	</body>
</html>