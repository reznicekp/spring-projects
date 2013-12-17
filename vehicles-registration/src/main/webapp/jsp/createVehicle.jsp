<%@ include file="taglibs.jsp" %>

<spring:nestedPath path="vehicle">
	<form:hidden path="id"/>
	
	<form:label path="plateNumber" cssErrorClass="error"><spring:message code="plateNumber"/> *</form:label>
	<form:input path="plateNumber" cssErrorClass="error"/>
	<form:errors path="plateNumber" cssClass="error"/>
	<br>
	<form:label path="brand" cssErrorClass="error"><spring:message code="brand"/> *</form:label>
	<form:select path="brand" cssErrorClass="error">
		<form:option value="" label="---"/>
		<form:options items="${brand}" itemLabel="value" itemValue="code"/>
	</form:select>
	<form:errors path="brand" cssClass="error"/>
	<br>
	<form:label path="otherBrandName" cssErrorClass="error"><spring:message code="otherBrand"/></form:label>
	<form:input path="otherBrandName" cssErrorClass="error"/>
	<form:errors path="otherBrandName" cssClass="error"/>
	<br>
	<form:label path="model" cssErrorClass="error"><spring:message code="model"/> *</form:label>
	<form:input path="model" cssErrorClass="error"/>
	<form:errors path="model" cssClass="error"/>
	<br>
	<form:label path="makingYear" cssErrorClass="error"><spring:message code="makingYear"/> *</form:label>
	<form:input path="makingYear" cssErrorClass="error"/>
	<form:errors path="makingYear" cssClass="error"/>
	<br>
	<form:label path="motEnd" cssErrorClass="error"><spring:message code="motEnd"/></form:label>
	<form:input path="motEnd" cssErrorClass="error"/>
	<form:errors path="motEnd" cssClass="error"/>
	<br>
	<form:label path="weight" cssErrorClass="error"><spring:message code="weight"/></form:label>
	<form:input path="weight" cssErrorClass="error"/>
	<form:errors path="weight" cssClass="error"/>
</spring:nestedPath>
