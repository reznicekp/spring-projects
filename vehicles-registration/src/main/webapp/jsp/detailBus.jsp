<%@ include file="taglibs.jsp" %>

<form:form action="/edit-bus" method="get" modelAttribute="detailFormBean">
	<fieldset>
		<legend><spring:message code="menu.bus"/></legend>
		
		<tiles:insertTemplate template="detailVehicle.jsp"/>
		
		<br>
		<spring:nestedPath path="bus">
			<form:label path="bodywork"><spring:message code="bodywork"/></form:label>
			<span id="bus.bodywork">${detailFormBean.bus.bodywork}</span>
			<br>
			<form:label path="sittingPlacesCount"><spring:message code="sittingPlacesCount"/></form:label>
			<span id="bus.sittingPlacesCount">${detailFormBean.bus.sittingPlacesCount}</span>
			<br>
			<form:label path="volume"><spring:message code="volume"/></form:label>
			<span id="bus.volume">${detailFormBean.bus.volume}</span>
		</spring:nestedPath>
	</fieldset>
	
	<fieldset>
		<legend><spring:message code="owner"/></legend>
		<tiles:insertTemplate template="detailOwner.jsp"/>
	</fieldset>
	
	<input type="submit" name="editVehicle" value='<spring:message code="btn.edit"/>'/>
	
	<tiles:insertTemplate template="detailInsurance.jsp"/>
</form:form>
