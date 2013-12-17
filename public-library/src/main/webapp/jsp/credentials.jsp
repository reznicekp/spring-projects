<%@ include file="taglibs.jsp" %>

<table id="credentials">
	<tr>
		<td><spring:message code="form.firstname"/></td>
		<td>${user.firstname}</td>
	</tr>
	<tr>
		<td><spring:message code="form.lastname"/></td>
		<td>${user.lastname}</td>
	</tr>
	<tr>
		<td><spring:message code="form.email"/></td>
		<td>${user.email}</td>
	</tr>
	<tr>
		<td><spring:message code="form.birthYear"/></td>
		<td>${user.birthYear}</td>
	</tr>
	<tr>
		<td><spring:message code="form.gpsLatitude"/></td>
		<td>${user.gpsLatitude}</td>
	</tr>
	<tr>
		<td><spring:message code="form.gpsLongitude"/></td>
		<td>${user.gpsLongitude}</td>
	</tr>
</table>

<a href="<%= request.getContextPath() %>/private/credentials-edit"><input type="button" value='<spring:message code="btn.edit"/>'/></a>
