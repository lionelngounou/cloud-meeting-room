<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="ctxURL" value="${pageContext.request.contextPath}" scope="application" />
<spring:url var="indexURL" value="/" scope="application" />
<spring:url var="homeURL" value="/home" scope="application" />
<spring:url var="bookingURL" value="/bookings" scope="application" />

<script type="text/javascript">
	/*Globals*/	
	function getAppContextPath(){
		return "${ctxURL}";
	}
	
	function getUrls(){
		return {
			bookingListAPI : "${ctxURL}/bookings/api",
			roomListAPI : "${ctxURL}/rooms/api",
			timeSlotListAPI : "${ctxURL}/rooms/api/timeSlots",
			bookingCreateAPI : "${ctxURL}/bookings/api"
		};
	}
</script>