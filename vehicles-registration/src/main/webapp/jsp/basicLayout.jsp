<%@ include file="taglibs.jsp" %>
<%@ page contentType="text/html" pageEncoding="utf-8" %>

<c:set var="pageSize" value="<%= Constants.PAGE_SIZE %>" scope="request"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/default.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/validation.css">
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/default.js"></script>
        
        <tiles:importAttribute name="title"/>
        <title><spring:message code="${title}"/> | <spring:message code="appName"/></title>
    </head>
    
    <body>
    	<div id="header">
    		<div id="titles">
				<h1><spring:message code="appName"/></h1>
				<h2><spring:message code="${title}"/></h2>
			</div>
    	</div>
    	
    	<br class="clear">
    	
    	<div id="menu">
    		<ul>
    			<li><a href="<%= request.getContextPath() %>/search"><spring:message code="menu.search"/></a></li>
    			<li>
    				<spring:message code="menu.create"/>
    				<ul>
    					<li><a href="<%= request.getContextPath() %>/create-car"><spring:message code="menu.car"/></a></li>
		    			<li><a href="<%= request.getContextPath() %>/create-motorcycle"><spring:message code="menu.motorcycle"/></a></li>
		    			<li><a href="<%= request.getContextPath() %>/create-truck"><spring:message code="menu.truck"/></a></li>
		    			<li><a href="<%= request.getContextPath() %>/create-bus"><spring:message code="menu.bus"/></a></li>
    				</ul>
    			</li>
    			<li><a href="<%= request.getContextPath() %>/bugs"><spring:message code="menu.bugs"/></a></li>
    		</ul>
    	</div>
    	
    	<div id="content">
    		<tiles:insertAttribute name="content"/>
    	</div>
    	
    	<div id="footer">
    		<p>Copyright 2013 &#169; Pavel Reznicek. All rights reserved.</p>
    	</div>
    </body>
</html>
