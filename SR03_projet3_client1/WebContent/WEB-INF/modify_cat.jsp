<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Supprimer une catégorie</title>
	</head>
	<body>
		<x:parse var="doc" doc="${categorie}" />
		<br><br>
		<form method="post">
			<label for="nom">Nouveau nom </label><input type="text" name="nom" id="nom" value="<x:out select="$doc/categorie/nom"/>"/>
			<input type="submit" value="mettre à jour" />
		</form>
	</body>
</html>