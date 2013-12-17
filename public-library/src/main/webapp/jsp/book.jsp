<%@ include file="taglibs.jsp" %>

<table>
	<tr>
		<td><spring:message code="book.name"/></td>
		<td>${bookInfo.name}</td>
	</tr>
	<tr>
		<td><spring:message code="book.author"/></td>
		<td>${bookInfo.author}</td>
	</tr>
	<tr>
		<td><spring:message code="book.year"/></td>
		<td>${bookInfo.year}</td>
	</tr>
</table>

<table class="list">
	<tr>
		<th><spring:message code="book.photo"/></th>
		<th><spring:message code="book.owner"/></th>
		<th><spring:message code="book.condition.spilled"/></th>
		<th><spring:message code="book.condition.bentEdges"/></th>
		<th><spring:message code="book.condition.tornLeaves"/></th>
		<th><spring:message code="book.condition.missingLeaves"/></th>
		<th><spring:message code="book.condition.average"/></th>
		<th><spring:message code="book.isNowHired"/></th>
		<th><spring:message code="book.requestCount"/></th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach items="${bookList}" var="book">
		<tr>
			<td>
				<c:choose>
					<c:when test="${book.photoMimeType != null}">
						<img src="<%= request.getContextPath() %>/private/get-photo/${book.id}" height="50px"/>
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>
			</td>
			<td><a href="mailto:${book.owner.email}">${book.owner.firstname} ${book.owner.lastname}</a></td>
			<td class="center">${book.condition.spilled}</td>
			<td class="center">${book.condition.bentEdges}</td>
			<td class="center">${book.condition.tornLeaves}</td>
			<td class="center">${book.condition.missingLeaves}</td>
			<td class="center">${book.condition.averageCondition}</td>
			<td class="center">
				<c:choose>
					<c:when test="${book.isNowHired}">
						<spring:message code="yes" var="yes"/>
						<c:out value="${fn:toLowerCase(yes)}"/>
					</c:when>
					<c:otherwise>
						<spring:message code="no" var="no"/>
						<c:out value="${fn:toLowerCase(no)}"/>
					</c:otherwise>
				</c:choose>
			</td>
			<td class="center">${book.requestCount}</td>
			<td>
				<c:choose>
					<c:when test="${book.isNowRequestedByLoggedUser}">
						<spring:message code="book.isNowRequested"/>
					</c:when>
					<c:otherwise>
						<a href="<%= request.getContextPath() %>/private/book/request/${book.id}"><spring:message code="search.request"/></a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>

<c:if test="${pagingPrevVisible || pagingNextVisible}">
	<%
	int currentPage = Integer.parseInt(request.getQueryString().substring(request.getQueryString().indexOf("page=") + "page=".length()));
	String url = request.getContextPath() + "/private/book?" + request.getQueryString();
	url = url.substring(0, url.indexOf("page="));
	%>
	<c:if test="${pagingPrevVisible}">
		<% int prevPage = currentPage - Constants.PAGING; %>
		<a href="<%= url %>page=<%= prevPage %>"><input type="button" value='<spring:message code="btn.prev"/>'/></a>
	</c:if>
	<c:if test="${pagingNextVisible}">
		<% int nextPage = currentPage + Constants.PAGING; %>
		<a href="<%= url %>page=<%= nextPage %>"><input type="button" value='<spring:message code="btn.next"/>'/></a>
	</c:if>
</c:if>

<br>
<a href="<%= session.getAttribute(Constants.SessionKey.HISTORY_STACK) %>"><input type="button" value='<spring:message code="btn.back"/>'/></a>
