<%@ include file="taglibs.jsp" %>

<form:form id="searchForm" action="/private/borrowed-books/search" method="get" modelAttribute="searchFormBean">
	<form:label path="searchedSubject" cssErrorClass="error"><spring:message code="form.whatToSearch"/></form:label>
	<form:input path="searchedSubject" cssErrorClass="error"/>
	<form:errors path="searchedSubject" cssClass="error"/>
	
	<input type="submit" value='<spring:message code="btn.search"/>' onclick="resetPaging()"/>
	<form:hidden id="page" path="page"/>
</form:form>

<table class="list">
	<tr>
		<th>#</th>
		<th><spring:message code="book.name"/></th>
		<th><spring:message code="book.author"/></th>
		<th><spring:message code="book.year"/></th>
		<th><spring:message code="book.owner"/></th>
		<th><spring:message code="book.homeOrHired"/></th>
	</tr>
	<c:set var="i" value="${1}"/>
	<c:forEach items="${bookList}" var="userBookState" >
		<tr>
			<td><c:out value="${i}."/></td>
			<td>
				<c:url value="/private/book-edit/${userBookState.book.id}" var="urlBookDetail"/>
				<a href="${urlBookDetail}"><c:out value="${userBookState.book.name}"/></a>
			</td>
			<td><c:out value="${userBookState.book.author}"/></td>
			<td class="center"><c:out value="${userBookState.book.year}"/></td>
			<td><a href="mailto:${userBookState.book.owner.email}"><c:out value="${userBookState.book.owner.firstname} ${userBookState.book.owner.lastname}"/></a></td>
			<td>
				<c:choose>
					<c:when test="${userBookState.requested != null}">
						<fmt:formatDate value="${userBookState.requested}" pattern="<%= Constants.DATE_FORMAT_STRING %>" var="requestedWhen"/>
						<spring:message code="book.requestedWhen" arguments="${requestedWhen},${userBookState.id}"/>
					</c:when>
					<c:when test="${userBookState.refused != null}">
						<fmt:formatDate value="${userBookState.refused}" pattern="<%= Constants.DATE_FORMAT_STRING %>" var="refusedWhen"/>
						<spring:message code="book.refusedWhen" arguments="${refusedWhen},${userBookState.id}"/>
					</c:when>
					<c:when test="${userBookState.hired != null}">
						<fmt:formatDate value="${userBookState.hired}" pattern="<%= Constants.DATE_FORMAT_STRING %>" var="hiredWhen"/>
						<spring:message code="book.hiredWhen" arguments="${hiredWhen},${userBookState.id}"/>
					</c:when>
				</c:choose>
			</td>
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
