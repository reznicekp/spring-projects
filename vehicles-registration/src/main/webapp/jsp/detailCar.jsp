<%@ include file="taglibs.jsp" %>

<form:form action="/edit-car" method="get" modelAttribute="detailFormBean">
	<fieldset>
		<legend><spring:message code="menu.car"/></legend>
		
		<tiles:insertTemplate template="detailVehicle.jsp"/>
		
		<br>
		<spring:nestedPath path="car">
			<form:label path="sittingPlacesCount"><spring:message code="sittingPlacesCount"/></form:label>
			<span id="car.sittingPlacesCount">${detailFormBean.car.sittingPlacesCount}</span>
			<br>
			<form:label path="volume"><spring:message code="volume"/></form:label>
			<span id="car.volume">${detailFormBean.car.volume}</span>
			<br>
			<form:label path="fuel"><spring:message code="fuel"/></form:label>
			<span id="car.fuel">${detailFormBean.car.fuel}</span>
		</spring:nestedPath>
	</fieldset>
	
	<fieldset>
		<legend><spring:message code="owner"/></legend>
		<tiles:insertTemplate template="detailOwner.jsp"/>
	</fieldset>
	
	<input type="submit" name="editVehicle" value='<spring:message code="btn.edit"/>'/>
	
	<tiles:insertTemplate template="detailInsurance.jsp"/>
</form:form>
