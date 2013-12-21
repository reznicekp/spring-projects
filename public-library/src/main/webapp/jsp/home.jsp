<%@ include file="taglibs.jsp" %>

<p><spring:message code="home.1"/></p>

<form:form action="/login" method="post" modelAttribute="loginFormBean">
	<spring:hasBindErrors name="loginFormBean">
		<form:errors cssClass="error"/><!-- bez atributu path zobrazi jen globalni errory -->
		<br>
	</spring:hasBindErrors>
	
	<form:label path="email" cssErrorClass="error"><spring:message code="form.email"/></form:label>
	<form:input path="email" cssErrorClass="error"/>
	<form:errors path="email" cssClass="error"/>
	<br>
	<form:label path="password" cssErrorClass="error"><spring:message code="form.password"/></form:label>
	<form:password path="password" cssErrorClass="error"/>
	<form:errors path="password" cssClass="error"/>
	<br>
	<input type="submit" value='<spring:message code="btn.login"/>'/>
</form:form>

<p><spring:message code="home.2"/></p>
