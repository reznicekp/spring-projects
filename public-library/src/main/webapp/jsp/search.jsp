<%@ include file="taglibs.jsp" %>

<form:form id="searchForm" action="/do-search" method="get" modelAttribute="searchFormBean">
	<form:label path="searchedSubject" cssErrorClass="error"><spring:message code="form.whatToSearch"/></form:label>
	<form:input path="searchedSubject" cssErrorClass="error"/>
	<form:errors path="searchedSubject" cssClass="error" />
	
	<input type="submit" value='<spring:message code="btn.search"/>' onclick="resetPaging()"/>
	<form:hidden id="page" path="page"/>
</form:form>

<table class="list">
	<tr>
		<th>#</th>
		<th><spring:message code="book.name"/></th>
		<th><spring:message code="book.author"/></th>
		<th><spring:message code="book.year"/></th>
		<% if (loggedUser != null) { %>
			<th>&nbsp;</th>
		<% } %>
	</tr>
	<c:set var="i" value="${1}"/>
	<c:forEach items="${bookList}" var="book">
		<tr>
			<td><c:out value="${i}."/></td>
			<td><c:out value="${book.name}"/></td>
			<td><c:out value="${book.author}"/></td>
			<td class="center"><c:out value="${book.year}"/></td>
			<% if (loggedUser != null) { %>
				<c:url value="/private/book?name=${book.name}&author=${book.author}&year=${book.year}&page=1" var="urlBook"/>
				<td class="center"><a href="${urlBook}"><spring:message code="search.request"/></a></td>
			<% } %>
		</tr>
		<c:set var="i" value="${i + 1}"/>
	</c:forEach>
</table>

<c:if test="${pagingPrevVisible}">
	<input type="button" value='<spring:message code="btn.prev"/>' onclick="decPaging(); jQuery('#searchForm').submit()"/>
</c:if>
<c:if test="${pagingNextVisible}">
	<input type="button" value='<spring:message code="btn.next"/>' onclick="incPaging(); jQuery('#searchForm').submit()"/>
</c:if>
