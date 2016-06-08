<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="../style.css" />
		<title>Modifier une annonce</title>
	</head>
	<body>
		<x:parse var="doc_ann" doc="${annonce}" />
		<x:parse var="doc_cat" doc="${list_cat}" />
		<br><br>
		<form method="post">
			<label for="nom">Nom de l'annonce </label><input type="text" name="nom" id="nom" value="<x:out select="$doc_ann/annonce/nom"/>" /><br>
			<label for="categorie">Catégorie </label>
			<select id="categorie" name="categorie">
				<x:forEach var="cat" select="$doc_cat/liste/categorie">
					<option value="<x:out select="$cat/id"/>" <x:if select="$doc_ann/annonce[cat=$cat/id]">selected</x:if>><x:out select="$cat/nom"/></option>
				</x:forEach>
			</select><br>
			<label for="tel">Téléphone </label><input type="text" name="tel" id="tel" value="<x:out select="$doc_ann/annonce/telephone"/>" /><br>
			<label for="numero">Numéro </label><input type="text" name="numero" id="numero" value="<x:out select="$doc_ann/annonce/adresse/numero"/>" /><br>
			<label for="rue">Rue </label><input type="text" name="rue" id="rue" value="<x:out select="$doc_ann/annonce/adresse/rue"/>" /><br>
			<label for="ville">Ville </label><input type="text" name="ville" id="ville" value="<x:out select="$doc_ann/annonce/adresse/ville"/>" /><br>
			<label for="cp">Code postal </label><input type="text" name="cp" id="cp" value="<x:out select="$doc_ann/annonce/adresse/codepostal"/>" /><br>
			<input type="submit" value="mettre à jour" />
		</form>
	</body>
</html>