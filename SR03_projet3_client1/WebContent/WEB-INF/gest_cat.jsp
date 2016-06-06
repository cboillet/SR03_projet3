<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Supprimer une catégorie</title>
	</head>
	<body>
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
			<label for="nom">Nom de la catégorie </label><input type="text" name="nom" id="nom" />
			<input type="submit" value="créer" />
		</form>
	</body>
</html>