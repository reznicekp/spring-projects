<%@ include file="taglibs.jsp" %>

<spring:nestedPath path="vehicle">
	<form:hidden path="id"/>
	
	<form:label path="plateNumber" cssErrorClass="error"><spring:message code="plateNumber"/> *</form:label>
	<form:input path="plateNumber" cssErrorClass="error"/>
	<form:errors path="plateNumber" cssClass="error"/>
	<br>
</spring:nestedPath>
