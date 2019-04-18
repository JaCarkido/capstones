<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div id="surveyContent">
<h2>Favorite Parks Survey</h2>
<div>Please tell us which park is your favorite! Your opinion is valuable in knowing what parks see frequent use (and which are regularly ignored).</div>
<p></p>
	<c:url value="/survey" var="survey" />
	<form:form action="${survey}" method="POST" modelAttribute="Survey">
		
		<div>
        	<label for="park"><div id=radioLabel>Of All the National Parks... Which Do You Prefer?</div></label>
        	<form:select path="park">
        		<option value ="">Select Park</option>
        	<c:forEach var="park" items="${parks}">
        		<option value="${park.code}">${park.name}</option>
        	</c:forEach>
        	</form:select>
        	<form:errors path="park"  cssClass="error"/>	
        </div>
        <p></p>
		<div>
        	<label for="email"><div id=radioLabel>Your Electronic Mail Address, Please.</div></label>
        	<form:input  path="email"  class="form-control"/>
        	<form:errors path="email"  cssClass="error"/>	
        </div>
        <p></p>
        <div>
            <label for="state"><div id=radioLabel>What State Do You Call Home?</div></label>
			<form:select path="state">
			<option value="">Select State</option>
	<option value="AL">Alabama</option>
	<option value="AK">Alaska</option>
	<option value="AZ">Arizona</option>
	<option value="AR">Arkansas</option>
	<option value="CA">California</option>
	<option value="CO">Colorado</option>
	<option value="CT">Connecticut</option>
	<option value="DE">Delaware</option>
	<option value="DC">District Of Columbia</option>
	<option value="FL">Florida</option>
	<option value="GA">Georgia</option>
	<option value="HI">Hawaii</option>
	<option value="ID">Idaho</option>
	<option value="IL">Illinois</option>
	<option value="IN">Indiana</option>
	<option value="IA">Iowa</option>
	<option value="KS">Kansas</option>
	<option value="KY">Kentucky</option>
	<option value="LA">Louisiana</option>
	<option value="ME">Maine</option>
	<option value="MD">Maryland</option>
	<option value="MA">Massachusetts</option>
	<option value="MI">Michigan</option>
	<option value="MN">Minnesota</option>
	<option value="MS">Mississippi</option>
	<option value="MO">Missouri</option>
	<option value="MT">Montana</option>
	<option value="NE">Nebraska</option>
	<option value="NV">Nevada</option>
	<option value="NH">New Hampshire</option>
	<option value="NJ">New Jersey</option>
	<option value="NM">New Mexico</option>
	<option value="NY">New York</option>
	<option value="NC">North Carolina</option>
	<option value="ND">North Dakota</option>
	<option value="OH">Ohio</option>
	<option value="OK">Oklahoma</option>
	<option value="OR">Oregon</option>
	<option value="PA">Pennsylvania</option>
	<option value="RI">Rhode Island</option>
	<option value="SC">South Carolina</option>
	<option value="SD">South Dakota</option>
	<option value="TN">Tennessee</option>
	<option value="TX">Texas</option>
	<option value="UT">Utah</option>
	<option value="VT">Vermont</option>
	<option value="VA">Virginia</option>
	<option value="WA">Washington</option>
	<option value="WV">West Virginia</option>
	<option value="WI">Wisconsin</option>
	<option value="WY">Wyoming</option>
			</form:select>          
        	<form:errors path="state" cssClass="error"/>
        </div>
        <p></p>
        <div>
        	<formlabel path="level"><div id=radioLabel>Provide the Nearest Estimate of Your Activity Level</div></formlabel>
      <div id="radioList">
        	<form:radiobutton path="level" value="None"/>I Am Lazy Human<br>
        	<form:radiobutton path="level" value="Inactive"/>Inactive<br>
        	<form:radiobutton path="level" value="Moderate" checked="true"/>Sometimes I Do Things<br>
        	<form:radiobutton path="level" value="Active"/>Active<br>
        	<form:radiobutton path="level" value="ExtremelyActive"/>The Most Active Human Alive
        	<form:errors path="level"  cssClass="error"/>
      </div>	
        </div>
        <p></p>
        <div>
            <input type="submit" value="SUBMIT"/>
        </div>


	</form:form>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />