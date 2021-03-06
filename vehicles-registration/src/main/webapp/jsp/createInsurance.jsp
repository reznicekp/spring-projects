<%@ include file="taglibs.jsp" %>

<table class="list">
	<tr>
		<th>#</th>
		<th><spring:message code="companyName"/></th>
		<th><spring:message code="activeFrom"/></th>
		<th><spring:message code="activeTo"/></th>
		<th>&nbsp;</th>
	</tr>
	
	<c:set var="i" value="1"/>
	<c:forEach items="${createFormBean.insuranceList}" var="insurance">
		<tr>
			<fmt:parseDate value="${insurance.activeFrom}" pattern="dd-MM-yyyy" var="activeFrom"/>
			<fmt:parseDate value="${insurance.activeTo}" pattern="dd-MM-yyyy" var="activeTo"/>
			<fmt:formatDate value="${activeFrom}" pattern="d.M.yyyy" var="activeFrom"/>
			<fmt:formatDate value="${activeTo}" pattern="d.M.yyyy" var="activeTo"/>
			<spring:message code="confirm.insuranceDelete" arguments="${activeFrom},${activeTo}" var="confirmation"/>
			
			<td>${i}.</td>
			<td>${insurance.companyName}</td>
			<td class="center">${activeFrom}</td>
			<td class="center">${activeTo}</td>
			<td class="center"><a href="<%= request.getContextPath() %>/delete-insurance/${insurance.id}" onclick="return confirm('${confirmation}')">x</a></td>
		</tr>
		
		<c:set var="i" value="${i + 1}"/>
	</c:forEach>
</table>

<c:if test="${empty createFormBean.insuranceList}">
	<spring:message code="noDataFound"/>
</c:if>

<fieldset>
	<legend><spring:message code="thirdPartyInsurance"/></legend>
	
	<spring:nestedPath path="insurance">
		<form:label path="companyCode" cssErrorClass="error"><spring:message code="companyName"/> *</form:label>
		<form:select path="companyCode" cssErrorClass="error">
	        <form:option value="" label="---"/>
	        <form:options items="${company}" itemLabel="value" itemValue="code"/>
        </form:select>
        <form:errors path="companyCode" cssClass="error"/>
		<br>
		<form:label path="otherCompanyName" cssErrorClass="error"><spring:message code="otherCompanyName"/></form:label>
		<form:input path="otherCompanyName" cssErrorClass="error"/>
		<form:errors path="otherCompanyName" cssClass="error"/>
		<br>
		<form:label path="activeFrom" cssErrorClass="error"><spring:message code="activeFrom"/> *</form:label>
		<form:input path="activeFrom" cssErrorClass="error"/>
		<form:errors path="activeFrom" cssClass="error"/>
		<br>
		<form:label path="activeTo" cssErrorClass="error"><spring:message code="activeTo"/> *</form:label>
		<form:input path="activeTo" cssErrorClass="error"/>
		<form:errors path="activeTo" cssClass="error"/>
	</spring:nestedPath>
</fieldset>

<input type="submit" name="saveInsurance" value='<spring:message code="btn.saveInsurance"/>'/>
