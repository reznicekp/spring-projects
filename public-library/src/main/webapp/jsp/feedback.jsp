<%@ include file="taglibs.jsp" %>

<form:form action="/private/save-feedback" method="post" modelAttribute="feedbackFormBean">
	<form:label path="rentTiming" cssErrorClass="error"><spring:message code="feedback.rentTiming"/></form:label>
	<form:radiobuttons path="rentTiming" items="${items}" cssErrorClass="error"/>
	<form:errors path="rentTiming" cssClass="error"></form:errors>
	<br>
	<form:label path="bookDamage" cssErrorClass="error"><spring:message code="feedback.bookDamage"/></form:label>
	<form:radiobuttons path="bookDamage" items="${items}" cssErrorClass="error"/>
	<form:errors path="bookDamage" cssClass="error"></form:errors>
	<br>
	<form:label path="bookLoss" cssErrorClass="error"><spring:message code="feedback.bookLoss"/></form:label>
	<form:radiobuttons path="bookLoss" items="${items}" cssErrorClass="error"/>
	<form:errors path="bookLoss" cssClass="error"></form:errors>
	<br>
	<form:hidden path="idUser"/>
	<input type="submit" value='<spring:message code="btn.ok"/>'/>
</form:form>

<a href="<%= request.getContextPath() %>/private/my-books"><input type="button" value='<spring:message code="btn.withoutFeedback"/>'/></a>
