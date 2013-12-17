<%@ include file="taglibs.jsp" %>

<form:form action="/edit-motorcycle" method="get" modelAttribute="detailFormBean">
	<fieldset>
		<legend><spring:message code="menu.motorcycle"/></legend>
		
		<tiles:insertTemplate template="detailVehicle.jsp"/>
		
		<br>
		<spring:nestedPath path="motorcycle">
			<form:label path="volume"><spring:message code="volume"/></form:label>
			<span id="motorcycle.volume">${detailFormBean.motorcycle.volume}</span>
		</spring:nestedPath>
	</fieldset>
	
	<fieldset>
		<legend><spring:message code="owner"/></legend>
		<tiles:insertTemplate template="detailOwner.jsp"/>
	</fieldset>
	
	<input type="submit" name="editVehicle" value='<spring:message code="btn.edit"/>'/>
	
	<tiles:insertTemplate template="detailInsurance.jsp"/>
</form:form>
