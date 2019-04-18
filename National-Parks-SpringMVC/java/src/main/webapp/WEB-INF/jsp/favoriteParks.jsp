<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div id="favorites-content">
<c:url value="detailPage" var="detailPage" />

<c:forEach var="faves" items="${favParks}" >
<div class = "content">
	<a class="imageLink" href="${detailPage}?code=${faves.code}&tempChange=${tempChange}">
		<div class="imageContainer">
			<img class="parkFaveImage" href src="img/parks/${faves.code.toLowerCase()}.jpg"/>
		</div>
	</a>
	<div class="summary">
		<h3>${faves.name}</h3>
		<p class="description">${faves.count} vote(s)!</p>
	</div>
</div>
</c:forEach>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />