<%@ include file="taglibs.jsp" %>

<spring:nestedPath path="owner">
	<form:hidden path="id"/>
	
	<form:label path="firstname" cssErrorClass="error"><spring:message code="firstname"/> *</form:label>
	<form:input path="firstname" cssErrorClass="error"/>
	<form:errors path="firstname" cssClass="error"/>
	<br>
	<form:label path="lastname" cssErrorClass="error"><spring:message code="lastname"/> *</form:label>
	<form:input path="lastname" cssErrorClass="error"/>
	<form:errors path="lastname" cssClass="error"/>
	<br>
	<form:label path="birthCertificateNumber" cssErrorClass="error"><spring:message code="birthCertificateNumber"/> *</form:label>
	<form:input path="birthCertificateNumber" cssErrorClass="error"/>
	<form:errors path="birthCertificateNumber" cssClass="error"/>
	<br>
	<form:label path="address" cssErrorClass="error"><spring:message code="address"/> *</form:label>
	<form:input path="address" cssErrorClass="error"/>
	<form:errors path="address" cssClass="error"/>
	<br>
	<form:label path="phone1" cssErrorClass="error"><spring:message code="phone"/> 1 *</form:label>
	<form:input path="phone1" cssErrorClass="error"/>
	<form:errors path="phone1" cssClass="error"/>
	<br>
	<form:label path="phone2" cssErrorClass="error"><spring:message code="phone"/> 2</form:label>
	<form:input path="phone2" cssErrorClass="error"/>
	<form:errors path="phone2" cssClass="error"/>
</spring:nestedPath>

<h3><spring:message code="followingVehicles"/></h3>

<table class="list">
	<tr>
		<th>#</th>
		<th><spring:message code="plateNumber"/></th>
		<th><spring:message code="brandAndModel"/></th>
		<th><spring:message code="makingYear"/></th>
		<th><spring:message code="motEnd"/></th>
	</tr>
	
	<c:set var="i" value="1"/>
	<c:forEach items="${createFormBean.owner.vehiclesOfOwner}" var="vehicleOfOwner">
		<tr>
			<td>${i}.</td>
			<td class="center"><a href="<%= request.getContextPath() %>/detail-${fn:toLowerCase(vehicleOfOwner.vehicleType)}/${vehicleOfOwner.id}">${vehicleOfOwner.plateNumber}</a></td>
			<td>${vehicleOfOwner.brandAndModel}</td>
			<td class="center">${vehicleOfOwner.makingYear}</td>
			<td class="center">${vehicleOfOwner.motEnd}</td>
		</tr>
		
		<c:set var="i" value="${i + 1}"/>
	</c:forEach>
</table>

<c:if test="${empty createFormBean.owner.vehiclesOfOwner}">
	<spring:message code="noDataFound"/>
</c:if>
