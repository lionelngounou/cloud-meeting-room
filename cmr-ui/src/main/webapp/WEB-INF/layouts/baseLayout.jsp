<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="commonURL.jsp" />

<!DOCTYPE html>
<html lang="en">
    <head>
		<jsp:include page="commonMeta.jsp" />
		<jsp:include page="commonCss.jsp" />
		
		<title><sitemesh:write property="title" /></title>
		<sitemesh:write property="head" />
    </head>
    
    <body role="document" >
        <div id="bodyWrapper">
            <div id="bodyWrapperIn">
                <header class="navbar navbar-inverse navbar-fixed-top no-rad" role="navigation" >
                    <c:import url="header.jsp" />
                </header>
                <section class="container">
                    <sitemesh:write property="body" />
                </section>  
            </div>
        </div>
        <footer class="footer">
            <div class="container">
                <jsp:include page="footer.jsp" />
            </div>
        </footer>

        <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
        
        <jsp:include page="commonJs.jsp" />
    </body>
    
</html>
