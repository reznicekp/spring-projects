<%@ include file="taglibs.jsp" %>

<form:form action="/search" method="post" modelAttribute="searchFormBean">
	<fieldset>
		<legend><spring:message code="vehicle"/></legend>
		
		<spring:nestedPath path="searchVehicleFormBean">
			<form:label path="plateNumber" cssErrorClass="error"><spring:message code="plateNumber"/></form:label>
			<form:input path="plateNumber" cssErrorClass="error"/>
			<form:errors path="plateNumber" cssClass="error"/>
			<br>
			<form:label path="brand" cssErrorClass="error"><spring:message code="brand"/></form:label>
			<form:input path="brand" cssErrorClass="error"/>
			<form:errors path="brand" cssClass="error"/>
			<br>
			<form:label path="model" cssErrorClass="error"><spring:message code="model"/></form:label>
			<form:input path="model" cssErrorClass="error"/>
			<form:errors path="model" cssClass="error"/>
			<br>
			<form:label path="makingYearFrom" cssErrorClass="error"><spring:message code="makingYearFrom"/></form:label>
			<form:input path="makingYearFrom" cssErrorClass="error"/>
			<form:errors path="makingYearFrom" cssClass="error"/>
			<br>
			<form:label path="makingYearTo" cssErrorClass="error"><spring:message code="makingYearTo"/></form:label>
			<form:input path="makingYearTo" cssErrorClass="error"/>
			<form:errors path="makingYearTo" cssClass="error"/>
			<br>
			<form:label path="motEndFrom" cssErrorClass="error"><spring:message code="motEndFrom"/></form:label>
			<form:input path="motEndFrom" cssErrorClass="error"/>
			<form:errors path="motEndFrom" cssClass="error"/>
			<br>
			<form:label path="motEndTo" cssErrorClass="error"><spring:message code="motEndTo"/></form:label>
			<form:input path="motEndTo" cssErrorClass="error"/>
			<form:errors path="motEndTo" cssClass="error"/>
			<br>
			<form:label path="insuranceCompany" cssErrorClass="error"><spring:message code="insuranceCompany"/></form:label>
			<form:input path="insuranceCompany" cssErrorClass="error" onchange="setCheckbox()" onkeyup="setCheckbox()"/>
			<form:errors path="insuranceCompany" cssClass="error"/>
			<br>
			<form:label path="onlyActiveInsurance"><spring:message code="onlyActiveInsurance"/></form:label>
			<form:checkbox path="onlyActiveInsurance" value="" disabled="true"/>
		</spring:nestedPath>
	</fieldset>
	
	<fieldset>
		<legend><spring:message code="owner"/></legend>
		
		<spring:nestedPath path="searchOwnerFormBean">
			<form:label path="firstname" cssErrorClass="error"><spring:message code="firstname"/></form:label>
			<form:input path="firstname" cssErrorClass="error"/>
			<form:errors path="firstname" cssClass="error"/>
			<br>
			<form:label path="lastname" cssErrorClass="error"><spring:message code="lastname"/></form:label>
			<form:input path="lastname" cssErrorClass="error"/>
			<form:errors path="lastname" cssClass="error"/>
			<br>
			<form:label path="birthCertificateNumber" cssErrorClass="error"><spring:message code="birthCertificateNumber"/></form:label>
			<form:input path="birthCertificateNumber" cssErrorClass="error"/>
			<form:errors path="birthCertificateNumber" cssClass="error"/>
		</spring:nestedPath>
	</fieldset>
	
	<input type="submit" value='<spring:message code="btn.search"/>'/>
</form:form>

<table class="list">
	<tr>
		<th>#</th>
		<th><spring:message code="plateNumber"/></th>
		<th><spring:message code="brandAndModel"/></th>
		<th><spring:message code="makingYear"/></th>
		<th><spring:message code="motEnd"/></th>
		<th><spring:message code="owner"/></th>
	</tr>
	
	<c:forEach items="${searchFormBean.searchResultFormBeanList}" var="searchResult">
		<tr>
			<td>${searchResult.order}</td>
			<td class="center"><a href="<%= request.getContextPath() %>/detail-${fn:toLowerCase(searchResult.vehicleType)}/${searchResult.id}">${searchResult.plateNumber}</a></td>
			<td>${searchResult.brandAndModel}</td>
			<td class="center">${searchResult.makingYear}</td>
			<td class="center">${searchResult.motEnd}</td>
			<td>${searchResult.owner}</td>
		</tr>
	</c:forEach>
</table>

<c:if test="${empty searchFormBean.searchResultFormBeanList}">
	<spring:message code="noDataFound"/>
</c:if>
