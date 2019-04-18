<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div id="detail-content" >

	<div class="detailImageContainer">
		<img class="detailParkImage" href src="img/parks/${park.code.toLowerCase()}.jpg"/>
		<p id="quote">"${park.quote}" - ${park.source}</p>
	</div>
	<c:set var="parkFee" value="${park.fee}"/>
	<div class="detailSummary">
	<div id="firstLine"></div>
		<div id="name">${park.name} (${park.code})</div>
		<div id="founded">${park.state} (founded in ${park.founded})</div>
		<p></p>
		<div id="climate">Climate: ${park.climate}</div>
		<div id="fee">Fee: $${park.fee}</div> 
		<p class="description">${park.description}</p>
	
	<h3>Fun Facts!</h3>
		<div id="elevation">Elevation: ${park.elevation} feet</div>
		<div id="campsites">Number of Campsites: ${park.campsites}</div>
		<div id="miles">Miles of Trails: ${park.miles}</div>
		<div id="animals">Number of Animal Species: ${park.animals}</div>
		<div id="acreage">${park.name} protects ${park.acreage} acres!</div>
	</div>
</div>
<br>
		<c:url var="formAction" value="detailPage"/>
		<form  action="${formAction}" >
			<input type="hidden" name="code" value="${park.code}"/>
			<input type="submit" name="tempChange" value ="Fahrenheit"/>
			<input type="submit" name="tempChange" value ="Celcius"/>
		</form>


<div id="allWeather">
	<div id="todaysWeather"> 
		<div id="todayWord">Today</div>
		<img src ="img/weather/${today.forecast}.png"/>
		<c:choose>
			<c:when test="${param.tempChange == 'Celcius' }">
				<div id="temp"><div id="todayHigh">High ${Math.round((today.high - 32) * (5/9))}C</div> <div id="todayLow">Low ${Math.round((today.low - 32) * (5/9))}C</div></div>	
			</c:when>
			<c:otherwise>
				<div id="temp"><div id="todayHigh">High ${today.high}F</div> <div id="todayLow">Low ${today.low}F</div></div>
			</c:otherwise>
		</c:choose>
		<hr>
		<div id="psa">
	<c:choose>
		
		<c:when test="${today.forecast == 'snow'}">
			Pack snowshoes!
		</c:when>
		
		<c:when test="${today.forecast == 'rain'}">
			Pack rain gear and wear waterproof shoes!
		</c:when>
		
		<c:when test="${today.forecast == 'thunderstorms'}">
			Seek shelter and avoid hiking on exposed ridges!
		</c:when>
		
		<c:when test="${today.forecast == 'sun'}">
			Pack sunblock!
		</c:when>
		<c:otherwise></c:otherwise>
		
	</c:choose>
	<c:choose>
		
		<c:when test="${today.high > 75}">
			Bring an extra gallon of water!
		</c:when>
		
		<c:when test="${today.high - today.low > 20}">
			Wear breathable layers!
		</c:when>
		
		<c:when test="${today.low < 20}">
			Please be warned: exposure to frigid temperatures is bad.
		</c:when>
		
		<c:otherwise></c:otherwise>
		
	</c:choose>
	</div>
	</div>
<c:choose>
	<c:when test="${param.tempChange == 'Celcius' }">
		
		
		<div id="otherWeather"> 
			<img src ="img/weather/${day2.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${Math.round((day2.high - 32) * (5/9))}C</div> <hr> <div id="low">Low ${Math.round((day2.low - 32) * (5/9))}C</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day3.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${Math.round((day3.high - 32) * (5/9))}C</div> <hr> <div id="low">Low ${Math.round((day3.low - 32) * (5/9))}C</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day4.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${Math.round((day4.high - 32) * (5/9))}C</div> <hr> <div id="low">Low ${Math.round((day4.low - 32) * (5/9))}C</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day5.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${Math.round((day5.high - 32) * (5/9))}C</div> <hr> <div id="low">Low ${Math.round((day5.low - 32) * (5/9))}C</div></div>
		</div>
	</c:when>
	<c:otherwise>
		
		<div id="otherWeather"> 
			<img src ="img/weather/${day2.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${day2.high}F</div> <hr> <div id="low">Low ${day2.low}F</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day3.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${day3.high}F</div> <hr> <div id="low">Low ${day3.low}F</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day4.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${day4.high}F</div> <hr> <div id="low">Low ${day4.low}F</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day5.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${day5.high}F</div> <hr> <div id="low">Low ${day5.low}F</div></div>
		</div>
	</c:otherwise>
</c:choose>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />