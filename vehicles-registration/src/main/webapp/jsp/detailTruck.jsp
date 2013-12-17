<%@ include file="taglibs.jsp" %>

<form:form action="/edit-truck" method="get" modelAttribute="detailFormBean">
	<fieldset>
		<legend><spring:message code="menu.truck"/></legend>
		
		<tiles:insertTemplate template="detailVehicle.jsp"/>
		
		<br>
		<spring:nestedPath path="truck">
			<form:label path="volume"><spring:message code="volume"/></form:label>
			<span id="truck.volume">${detailFormBean.truck.volume}</span>
			<br>
			<form:label path="bodywork"><spring:message code="bodywork"/></form:label>
			<span id="truck.bodywork">${detailFormBean.truck.bodywork}</span>
		</spring:nestedPath>
	</fieldset>
	
	<fieldset>
		<legend><spring:message code="owner"/></legend>
		<tiles:insertTemplate template="detailOwner.jsp"/>
	</fieldset>
	
	<input type="submit" name="editVehicle" value='<spring:message code="btn.edit"/>'/>
	
	<tiles:insertTemplate template="detailInsurance.jsp"/>
</form:form>
