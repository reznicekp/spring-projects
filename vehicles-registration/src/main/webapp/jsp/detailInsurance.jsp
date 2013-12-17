<%@ include file="taglibs.jsp" %>

<table class="list">
	<tr>
		<th>#</th>
		<th><spring:message code="companyName"/></th>
		<th><spring:message code="activeFrom"/></th>
		<th><spring:message code="activeTo"/></th>
	</tr>
	
	<c:forEach items="${detailFormBean.insuranceList}" var="insurance">
		<tr>
			<fmt:parseDate value="${insurance.activeFrom}" pattern="dd-MM-yyyy" var="activeFrom"/>
			<fmt:parseDate value="${insurance.activeTo}" pattern="dd-MM-yyyy" var="activeTo"/>
			
			<td>${insurance.id}</td>
			<td>${insurance.companyName}</td>
			<td class="center"><fmt:formatDate value="${activeFrom}" pattern="d.M.yyyy"/></td>
			<td class="center"><fmt:formatDate value="${activeTo}" pattern="d.M.yyyy"/></td>
		</tr>
	</c:forEach>
</table>

<c:if test="${empty detailFormBean.insuranceList}">
	<spring:message code="noDataFound"/>
</c:if>
