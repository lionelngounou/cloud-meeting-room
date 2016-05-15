<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<spring:url value="/js/main.js" var="mainJs" />
<script src="${mainJs}" type="text/javascript"></script>

<spring:url value="/js/booking.js" var="bookingJs" />

<c:if test="${generalModel.section=='bookings'}">
	<script src="${bookingJs}" type="text/javascript"></script>
</c:if>
