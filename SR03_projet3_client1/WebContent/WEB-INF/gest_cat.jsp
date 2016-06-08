<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="style.css" />
		<title>Gestion des catégories</title>
	</head>
	<body>
		<c:import url="navbar.jsp"></c:import>
		
		<div class="container">
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
			<form class="form-horizontal" method="post">
				<fieldset>
	                <legend>Créer une nouvelle catégorie</legend>
	                <div class="form-group">
						<label for="nom" class="col-sm-2 control-label">Nom de la catégorie </label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="nom" id="nom" />
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