<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div id="home-content">
<c:url value="detailPage" var="detailPage" />

<c:forEach var="park" items="${allParks}" >
<div class = "content">
	<a class="imageLink" href="${detailPage}?code=${park.code}&tempChange=${tempChange}">
		<div class="imageContainer">
			<img class="parkImage" href src="img/parks/${park.code.toLowerCase()}.jpg"/>
		</div>
	</a>
	<div class="summary">
		<h3>${park.name}</h3>
		<p class="state">State: ${park.state}</p>
		<p class="description">${park.description}</p>
	</div>
</div>
</c:forEach>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />