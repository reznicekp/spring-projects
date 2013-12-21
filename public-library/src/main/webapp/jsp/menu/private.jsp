<%@ include file="../taglibs.jsp" %>

<ul>
	<li><a href="<%= request.getContextPath() %>/search"><spring:message code="menu.search"/></a></li>
	<li><a href="<%= request.getContextPath() %>/private/my-books"><spring:message code="menu.myBooks"/></a></li>
	<li><a href="<%= request.getContextPath() %>/private/new-book"><spring:message code="menu.newBook"/></a></li>
	<li><a href="<%= request.getContextPath() %>/private/borrowed-books"><spring:message code="menu.borrowedBooks"/></a></li>
	<li><a href="<%= request.getContextPath() %>/private/credentials"><spring:message code="menu.credentials"/></a></li>
	<li><a href="<%= request.getContextPath() %>/private/password"><spring:message code="menu.password"/></a></li>
	<li><a href="<%= request.getContextPath() %>/help"><spring:message code="menu.help"/></a></li>
</ul>
