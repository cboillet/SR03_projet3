<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="style.css" />
		<title>Modifier une catégorie</title>
	</head>
	<body>
		<c:import url="navbar.jsp"></c:import>
		
		<div class="container">
			<c:import url="menu.jsp"><c:param name="mode" value="cat"/></c:import>
			
			<x:parse var="doc" doc="${categorie}" />
			<br><br>
			<form class="form-horizontal" method="post">
				<fieldset>
	                <legend>Modifier une catégorie</legend>
	                <div class="form-group">
						<label for="nom" class="col-sm-2 control-label">Nouveau nom </label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="nom" id="nom" value="<x:out select="$doc/categorie/nom"/>"/>
						</div>
					</div>
					<div class="form-group">
	                	<div class="col-sm-offset-2 col-sm-10">
							<button class="btn btn-default" type="submit">Mettre à jour</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</body>
</html>