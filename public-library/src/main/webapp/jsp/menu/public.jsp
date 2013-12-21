<%@ include file="../taglibs.jsp" %>

<ul>
	<li><a href="<%= request.getContextPath() %>/home"><spring:message code="menu.home"/></a></li>
	<li><a href="<%= request.getContextPath() %>/search"><spring:message code="menu.search"/></a></li>
	<li><a href="<%= request.getContextPath() %>/help"><spring:message code="menu.help"/></a></li>
	<li><a href="<%= request.getContextPath() %>/bugs"><spring:message code="menu.bugs"/></a></li>
</ul>
