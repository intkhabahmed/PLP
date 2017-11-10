<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello this is index page</h1>

	Source :<input type = "text" name="src" >
	Destination :<input type = "text" name="dest" >
	<a href="login.html">Login</a>
	<a href="index.html">Get All List of Flights</a>
	<a href="booking.html">Get All Flight Bookings</a>

	<c:if test="${flights ne null}">
		<table border="1">
			<tr>
				<td>Flight No</td>
				<td>Flight Name</td>
				<td>Arrival Date</td>
				<td>Arrival Date</td>
			</tr>
			<c:forEach items="${flights}" var="flight">
				<tr>
				<td>${flight.flightNo}</td>
				<td>${flight.flightName}</td>
				<td><fmt:formatDate value="${flight.arrDate}" pattern="dd-MMM-yy"></fmt:formatDate></td>
				<td>${flight.arrTime}</td>
			</tr>
			
			</c:forEach>
		</table>
	</c:if>
	<hr>
	<c:if test="${bookings ne null}">
		<table border="1">
			<tr>
				<td>BookingId</td>
				<td>Customer Email</td>
				<td>No of Passengers</td>
				<td>Class Type</td>
			</tr>
			<c:forEach items="${bookings}" var="booking">
				<tr>
				<td>${booking.bookingId}</td>
				<td>${booking.custEmail}</td>
				<td>${booking.noOfPassengers}</td>
				<td>${booking.classType}</td>
			</tr>
			
			</c:forEach>
		</table>
	</c:if>
</body>
</html>