<%@ include file="taglibs.jsp" %>

<form:form action="/sign-in" method="post" modelAttribute="registrationFormBean">
	<form:label path="firstname"><spring:message code="form.firstname"/></form:label>
	<form:input path="firstname"/>
	<br>
	<form:label path="lastname" cssErrorClass="error"><spring:message code="form.lastname"/></form:label>
	<form:input path="lastname" cssErrorClass="error"/>
	<form:errors path="lastname" cssClass="error"/>
	<br>
	<form:label path="email" cssErrorClass="error"><spring:message code="form.email"/></form:label>
	<form:input path="email" cssErrorClass="error"/>
	<form:errors path="email" cssClass="error"/>
	<br>
	<form:label path="birthYear" cssErrorClass="error"><spring:message code="form.birthYear"/></form:label>
	<form:input path="birthYear" cssErrorClass="error"/>
	<form:errors path="birthYear" cssClass="error"/>
	<br>
	<form:label path="gpsLatitude" cssErrorClass="error"><spring:message code="form.gpsLatitude"/></form:label>
	<form:input path="gpsLatitude" cssErrorClass="error"/>
	<form:errors path="gpsLatitude" cssClass="error"/>
	<br>
	<form:label path="gpsLongitude" cssErrorClass="error"><spring:message code="form.gpsLongitude"/></form:label>
	<form:input path="gpsLongitude" cssErrorClass="error"/>
	<form:errors path="gpsLongitude" cssClass="error"/>
	<br>
	<input type="submit" value='<spring:message code="btn.signIn"/>'/>
</form:form>
