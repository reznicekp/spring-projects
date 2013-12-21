<%@ include file="taglibs.jsp" %>

<form:form id="searchForm" action="/private/my-books/search" method="get" modelAttribute="searchFormBean">
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
		<th><spring:message code="book.canBeHired"/></th>
		<th><spring:message code="book.homeOrHired"/></th>
		<th><spring:message code="delete"/></th>
	</tr>
	<c:set var="i" value="${1}"/>
	<c:forEach items="${bookList}" var="book" >
		<tr>
			<td><c:out value="${i}."/></td>
			<td>
				<c:url value="/private/book-edit/${book.id}" var="urlBookDetail"/>
				<a href="${urlBookDetail}"><c:out value="${book.name}"/></a>
			</td>
			<td><c:out value="${book.author}"/></td>
			<td class="center"><c:out value="${book.year}"/></td>
			<td class="center">
				<c:choose>
					<c:when test="${book.canBeHired}">
						<spring:message code="yes" var="yes"/>
						<c:out value="${fn:toLowerCase(yes)}"/>
					</c:when>
					<c:otherwise>
						<spring:message code="no" var="no"/>
						<c:out value="${fn:toLowerCase(no)}"/>
					</c:otherwise>
				</c:choose>
			</td>
			<td class="center">
				<c:choose>
					<c:when test="${book.hiredWhen != null}">
						<fmt:formatDate value="${book.hiredWhen}" pattern="<%= Constants.DATE_FORMAT_STRING %>" var="hiredWhen"/>
						<spring:message code="book.hiredBySince" arguments="<a href='mailto:${book.hiredTo.email}'>${book.hiredTo.firstname} ${book.hiredTo.lastname}</a>, ${hiredWhen}"/>
						<a href="<%= request.getContextPath() %>/private/my-books/return/${book.id}"><spring:message code="book.return"/></a>
					</c:when>
					<c:otherwise>
						<spring:message code="book.atHome"/>
					</c:otherwise>
				</c:choose>
			</td>
			<td class="center">
				<c:choose>
					<c:when test="${book.hiredWhen == null && book.requestedStateList == null}">
						<a href="<%= request.getContextPath() %>/private/delete-book/${book.id}" onclick="return confirm('<spring:message code="book.deleteConfirm" arguments="${book.name}"/>');"><img src="<%= request.getContextPath() %>/images/delete_icon.png"/></a>
					</c:when>
					<c:otherwise>
						<img src="<%= request.getContextPath() %>/images/delete_icon_disabled.png" title='<spring:message code="book.deleteDisabledReason"/>'/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<c:if test="${book.requestedStateList != null}">
			<c:forEach items="${book.requestedStateList}" var="state">
				<tr class="noBorder">
					<td>&nbsp;</td>
					<td colspan="5">
						<fmt:formatDate value="${state.requested}" pattern="<%= Constants.DATE_FORMAT_STRING %>" var="requested"/>
						<spring:message code="book.requestedByWhen" arguments="<a href='mailto:${state.user.email}'>${state.user.firstname} ${state.user.lastname}</a>, ${requested}"/>
						<c:choose>
							<c:when test="${book.hiredWhen != null}">
								<span title='<spring:message code="book.terminateHireFirst"/>'><spring:message code="book.hire"/></span>
							</c:when>
							<c:otherwise>
								<a href="<%= request.getContextPath() %>/private/my-books/hire/${state.book.id}/${state.user.id}"><spring:message code="book.hire"/></a>
							</c:otherwise>
						</c:choose>
						/ <a href="<%= request.getContextPath() %>/private/my-books/refuse/${state.book.id}/${state.user.id}"><spring:message code="book.refuse"/></a>
					</td>
				</tr>
				<tr class="noBorder">
					<td>&nbsp;</td>
					<td colspan="5">
						<spring:message code="user.experience"/> ${state.user.firstname} ${state.user.lastname}: 
						${state.user.feedbackRentTimingCount} / ${state.user.feedbackBookDamageCount} / ${state.user.feedbackBookLossCount} &nbsp;&nbsp;&nbsp;
						<span title='<spring:message code="feedback.abbr.rentTiming"/> / <spring:message code="feedback.abbr.bookDamage"/> / <spring:message code="feedback.abbr.bookLoss"/>' style="cursor: pointer; text-decoration: underline;">?</span>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:set var="i" value="${i + 1}"/>
	</c:forEach>
</table>

<c:if test="${pagingPrevVisible}">
	<input type="button" value='<spring:message code="btn.prev"/>' onclick="decPaging(); jQuery('#searchForm').submit()"/>
</c:if>
<c:if test="${pagingNextVisible}">
	<input type="button" value='<spring:message code="btn.next"/>' onclick="incPaging(); jQuery('#searchForm').submit()"/>
</c:if>
