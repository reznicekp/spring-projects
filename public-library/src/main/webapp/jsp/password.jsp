<%@ include file="taglibs.jsp" %>

<c:if test="${passwordFormBean.validationMessage == true}">
	<span class="success"><spring:message code="success.password"/></span>
</c:if>

<form:form action="/private/password" method="post" modelAttribute="passwordFormBean" id="password">
	<spring:hasBindErrors name="passwordFormBean">
		<form:errors cssClass="error"/>
		<br>
	</spring:hasBindErrors>
	
	<form:label path="oldPassword" cssErrorClass="error"><spring:message code="form.oldPassword"/></form:label>
	<form:password path="oldPassword" cssErrorClass="error"/>
	<form:errors path="oldPassword" cssClass="error"/>
	<br>
	<form:label path="newPassword" cssErrorClass="error"><spring:message code="form.newPassword"/></form:label>
	<form:password path="newPassword" cssErrorClass="error"/>
	<form:errors path="newPassword" cssClass="error"/>
	<br>
	<form:label path="confirmPassword" cssErrorClass="error"><spring:message code="form.confirmPassword"/></form:label>
	<form:password path="confirmPassword" cssErrorClass="error"/>
	<form:errors path="confirmPassword" cssClass="error"/>
	<br>
	<input type="submit" value='<spring:message code="btn.ok"/>'/>
</form:form>
