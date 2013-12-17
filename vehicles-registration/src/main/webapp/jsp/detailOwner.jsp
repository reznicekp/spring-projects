<%@ include file="taglibs.jsp" %>

<spring:nestedPath path="owner">
	<form:label path="firstname"><spring:message code="firstname"/></form:label>
	<span id="owner.firstname">${detailFormBean.owner.firstname}</span>
	<br>
	<form:label path="lastname"><spring:message code="lastname"/></form:label>
	<span id="owner.lastname">${detailFormBean.owner.lastname}</span>
	<br>
	<form:label path="birthCertificateNumber"><spring:message code="birthCertificateNumber"/></form:label>
	<span id="owner.birthCertificateNumber">${detailFormBean.owner.birthCertificateNumber}</span>
	<br>
	<form:label path="address"><spring:message code="address"/></form:label>
	<span id="owner.address">${detailFormBean.owner.address}</span>
	<br>
	<form:label path="phone1"><spring:message code="phone"/> 1</form:label>
	<span id="owner.phone1">${detailFormBean.owner.phone1}</span>
	<br>
	<form:label path="phone2"><spring:message code="phone"/> 2</form:label>
	<span id="owner.phone2">${detailFormBean.owner.phone2}</span>
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
	
	<c:forEach items="${detailFormBean.owner.vehiclesOfOwner}" var="vehicleOfOwner">
		<tr>
			<td>${vehicleOfOwner.order}</td>
			<td class="center"><a href="<%= request.getContextPath() %>/detail-${fn:toLowerCase(vehicleOfOwner.vehicleType)}/${vehicleOfOwner.id}">${vehicleOfOwner.plateNumber}</a></td>
			<td>${vehicleOfOwner.brandAndModel}</td>
			<td class="center">${vehicleOfOwner.makingYear}</td>
			<td class="center">${vehicleOfOwner.motEnd}</td>
		</tr>
	</c:forEach>
</table>

<c:if test="${empty detailFormBean.owner.vehiclesOfOwner}">
	<spring:message code="noDataFound"/>
</c:if>
