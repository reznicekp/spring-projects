<%@ include file="taglibs.jsp" %>

<spring:nestedPath path="vehicle">
	<form:hidden path="id"/>
	
	<form:label path="plateNumber"><spring:message code="plateNumber"/></form:label>
	<span id="vehicle.plateNumber">${detailFormBean.vehicle.plateNumber}</span>
	<br>
	<form:label path="brand"><spring:message code="brand"/></form:label>
	<span id="vehicle.brand">${detailFormBean.vehicle.brand}</span>
	<br>
	<form:label path="model"><spring:message code="model"/></form:label>
	<span id="vehicle.model">${detailFormBean.vehicle.model}</span>
	<br>
	<form:label path="makingYear"><spring:message code="makingYear"/></form:label>
	<span id="vehicle.makingYear">${detailFormBean.vehicle.makingYear}</span>
	<br>
	<form:label path="motEnd"><spring:message code="motEnd"/></form:label>
	<span id="vehicle.motEnd">${detailFormBean.vehicle.motEnd}</span>
	<br>
	<form:label path="weight"><spring:message code="weight"/></form:label>
	<span id="vehicle.weight">${detailFormBean.vehicle.weight}</span>
</spring:nestedPath>
