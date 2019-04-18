<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>
		
	</title>
	<c:url var="stylesheetHref" value="/css/site.css" />
	<link rel="stylesheet" href="${stylesheetHref}">
</head>
<body>
	<header>
		<c:url var="headerImg" value="/img/logo.png" />
		<img id="logo" src="${headerImg}" alt="Logo">
	</header>
	<nav>
		<ul id="headerLinks">
			<c:url var="homePage" value="homePage" />		
			<li><a href="${homePage}">Home</a></li>
			<c:url var="survey" value="survey" />
			<li><a href="${survey}">Survey</a></li>
			<c:url var="favoriteParks" value="favoriteParks" />
			<li><a href="${favoriteParks}">Favorite Parks</a> </li>
		</ul>
	</nav>
	<div id="container" class="wrapper">