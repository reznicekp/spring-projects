<%@ include file="taglibs.jsp" %>

<form:form action="/upsert-bus" method="post" modelAttribute="createFormBean">
	<fieldset>
		<legend><spring:message code="menu.bus"/></legend>
		
		<tiles:insertTemplate template="createVehicle1.jsp"/>
		
		<spring:nestedPath path="vehicle">
			<form:label path="brand" cssErrorClass="error"><spring:message code="brand"/> *</form:label>
			<form:select path="brand" cssErrorClass="error">
				<form:option value="" label="---"/>
				<form:options items="${brandBus}" itemLabel="value" itemValue="code"/>
			</form:select>
			<form:errors path="brand" cssClass="error"/>
		</spring:nestedPath>
		
		<tiles:insertTemplate template="createVehicle2.jsp"/>
		
		<br>
		<spring:nestedPath path="bus">
			<form:label path="bodywork" cssErrorClass="error"><spring:message code="bodywork"/> *</form:label>
			<form:select path="bodywork" cssErrorClass="error">
		        <form:option value="" label="---"/>
		        <form:options items="${bodyworkBus}" itemLabel="value" itemValue="code"/>
	        </form:select>
			<form:errors path="bodywork" cssClass="error"/>
			<br>
			<form:label path="sittingPlacesCount" cssErrorClass="error"><spring:message code="sittingPlacesCount"/> *</form:label>
			<form:input path="sittingPlacesCount" cssErrorClass="error"/>
			<form:errors path="sittingPlacesCount" cssClass="error"/>
			<br>
			<form:label path="volume" cssErrorClass="error"><spring:message code="volume"/> *</form:label>
			<form:input path="volume" cssErrorClass="error"/>
			<form:errors path="volume" cssClass="error"/>
		</spring:nestedPath>
	</fieldset>
	
	<fieldset>
		<legend><spring:message code="owner"/></legend>
		<tiles:insertTemplate template="createOwner.jsp"/>
		<input type="submit" name="getOwner" value='<spring:message code="btn.getOwner"/>'/>
	</fieldset>
	
	<input type="submit" name="saveVehicle" value='<spring:message code="btn.save"/>'/>
	
	<tiles:insertTemplate template="createInsurance.jsp"/>
</form:form>
