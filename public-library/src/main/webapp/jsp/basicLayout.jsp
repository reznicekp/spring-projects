<%@ include file="taglibs.jsp" %>

<%@ page contentType="text/html" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/default.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/validation.css">
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/default.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.10.2.min.js"></script>
        
        <script> 
			var PAGING = <%= Constants.PAGING %>;
		</script>
        
        <tiles:importAttribute name="title"/>
        <title><spring:message code="${title}"/> | <spring:message code="appName"/></title>
    </head>
    
    <body>
    	<div id="header">
    		<div id="titles">
				<h1><spring:message code="appName"/></h1>
				<h2><spring:message code="${title}"/></h2>
			</div>
			
			<div id="links">
				<c:url value="/changeLocale?lang=en" var="urlLocaleEn"/>
				<c:url value="/changeLocale?lang=cz" var="urlLocaleCz"/>
				<a href="${urlLocaleEn}">en</a> | <a href="${urlLocaleCz}">cz</a>
				
				<br>
				
				<% if (loggedUser != null) { %>
					<br>
					<c:out value="${loggedUser.name}"/>
					<br>
					<c:url value="/logout" var="urlLogout"/>
					<a href="${urlLogout}"><spring:message code="btn.logout"/></a>
				<% } %>
			</div>
    	</div>
    	
    	<br class="clear">
    	
    	<div id="menu">
    		<tiles:insertAttribute name="menu"/>
    	</div>
    	
    	<div id="content">
    		<tiles:insertAttribute name="content"/>
    	</div>
    	
    	<div id="footer">
    		<p>Copyright 2013 &#169; Pavel Reznicek. All rights reserved.</p>
    	</div>
    </body>
</html>
