<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello this is index page</h1>
	<a href="index.html">Get All List of Flights</a>
	<c:if test="${flights ne null}">
		<table>
			<c:forEach items="${flights}" var="flight">
				<tr>
				<td>${flight.flightNo}</td>
				<td>${flight.flightName}</td>
				<td>${flight.arrDate}</td>
				<td>${flight.arrTime}</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>