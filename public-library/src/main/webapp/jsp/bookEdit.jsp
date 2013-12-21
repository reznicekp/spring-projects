<%@ include file="taglibs.jsp" %>

<form:form action="/private/book-do-edit" method="post" modelAttribute="bookFormBean" enctype="multipart/form-data">
	<form:label path="name" cssErrorClass="error"><spring:message code="book.name"/></form:label>
	<form:input path="name" cssErrorClass="error" disabled="${readonlyForm}"/>
	<form:errors path="name" cssClass="error"/>
	<br>
	<form:label path="author" cssErrorClass="error"><spring:message code="book.author"/></form:label>
	<form:input path="author" cssErrorClass="error" disabled="${readonlyForm}"/>
	<form:errors path="author" cssClass="error"/>
	<br>
	<form:label path="year"><spring:message code="book.year"/></form:label>
	<form:input path="year" cssErrorClass="error" disabled="${readonlyForm}"/>
	<form:errors path="year" cssClass="error"/>
	<br>
	<c:if test="${bookFormBean.photoExists}">
		<img src="<%= request.getContextPath() %>/private/get-photo/${bookFormBean.id}" height="200px"/>
		<br>
	</c:if>
	<c:if test="${!readonlyForm}">
		<form:label path="photo"><spring:message code="book.photo"/></form:label>
		<form:input path="photo" cssErrorClass="error" type="file" disabled="${readonlyForm}"/>
		<form:errors path="photo" cssClass="error"/>
		<br>
		<form:label path="canBeHired"><spring:message code="book.canBeHired"/></form:label>
		<form:checkbox path="canBeHired" disabled="${bookNowRequested}"/>
		<br>
	</c:if>
	<spring:nestedPath path="condition">
		<form:label path="spilled"><spring:message code="book.condition.spilled"/></form:label>
		<form:radiobutton path="spilled" value="1" label="1" disabled="${readonlyForm}"/>
		<form:radiobutton path="spilled" value="2" label="2" disabled="${readonlyForm}"/>
		<form:radiobutton path="spilled" value="3" label="3" disabled="${readonlyForm}"/>
		<form:radiobutton path="spilled" value="4" label="4" disabled="${readonlyForm}"/>
		<form:radiobutton path="spilled" value="5" label="5" disabled="${readonlyForm}"/>
		<br>
		<form:label path="bentEdges"><spring:message code="book.condition.bentEdges"/></form:label>
		<form:radiobutton path="bentEdges" value="1" label="1" disabled="${readonlyForm}"/>
		<form:radiobutton path="bentEdges" value="2" label="2" disabled="${readonlyForm}"/>
		<form:radiobutton path="bentEdges" value="3" label="3" disabled="${readonlyForm}"/>
		<form:radiobutton path="bentEdges" value="4" label="4" disabled="${readonlyForm}"/>
		<form:radiobutton path="bentEdges" value="5" label="5" disabled="${readonlyForm}"/>
		<br>
		<form:label path="tornLeaves"><spring:message code="book.condition.tornLeaves"/></form:label>
		<form:radiobutton path="tornLeaves" value="1" label="1" disabled="${readonlyForm}"/>
		<form:radiobutton path="tornLeaves" value="2" label="2" disabled="${readonlyForm}"/>
		<form:radiobutton path="tornLeaves" value="3" label="3" disabled="${readonlyForm}"/>
		<form:radiobutton path="tornLeaves" value="4" label="4" disabled="${readonlyForm}"/>
		<form:radiobutton path="tornLeaves" value="5" label="5" disabled="${readonlyForm}"/>
		<br>
		<form:label path="missingLeaves"><spring:message code="book.condition.missingLeaves"/></form:label>
		<form:radiobutton path="missingLeaves" value="1" label="1" disabled="${readonlyForm}"/>
		<form:radiobutton path="missingLeaves" value="2" label="2" disabled="${readonlyForm}"/>
		<form:radiobutton path="missingLeaves" value="3" label="3" disabled="${readonlyForm}"/>
		<form:radiobutton path="missingLeaves" value="4" label="4" disabled="${readonlyForm}"/>
		<form:radiobutton path="missingLeaves" value="5" label="5" disabled="${readonlyForm}"/>
	</spring:nestedPath>
	<br>
	<form:hidden path="id"/>
	<c:if test="${!readonlyForm}">
		<input type="submit" value='<spring:message code="btn.ok"/>'/>
	</c:if>
	<input type="hidden" name="referer" value="${referer}"/>
</form:form>

<br>
<a href="${referer}"><input type="button" value='<spring:message code="btn.back"/>'/></a>
