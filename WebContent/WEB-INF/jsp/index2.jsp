<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<h1>Airline Reservation System</h1>


	<form:form action="listOfFlights.html" method="post"
		modelAttribute="booking">

		<table>
			<tr>
				<td><input type="button" onclick="location.href='login.html'"
					value="Login/SignUp" /></td>
			</tr>

			<tr>
				<td>From</td>
				<td><form:select path="srcCity">
						<form:option value="select">Select</form:option>
						<form:option value="PNQ">Pune(PNQ)</form:option>
						<form:option value="BOM">Mumbai(BOM)</form:option>
						<form:option value="MAA">Chennai(MAA)</form:option>
						<form:option value="CCU">Kolkata(CCU)</form:option>
						<form:option value="DEL">Delhi(DEL)</form:option>
						<form:option value="BLR">Bangalore(BLR)</form:option>
					</form:select></td>
			</tr>

			<tr>
				<td>To</td>
				<td><form:select path="destCity">
						<form:option value="select">Select</form:option>
						<form:option value="PNQ">Pune(PNQ)</form:option>
						<form:option value="BOM">Mumbai(BOM)</form:option>
						<form:option value="MAA">Chennai(MAA)</form:option>
						<form:option value="CCU">Kolkata(CCU)</form:option>
						<form:option value="DEL">Delhi(DEL)</form:option>
						<form:option value="BLR">Bangalore(BLR)</form:option>
					</form:select></td>
			</tr>

			<tr>
				<td>Depart</td>
				<td><form:input path="travelDate" type="date" /></td>
			</tr>

			<tr>
				<td>No. of Passengers</td>
				<td><form:input path="noOfPassengers" type="text" /></td>
			</tr>

			<tr>
				<td>Class Type</td>
				<td><form:input path="classType" type="text" /></td>
			</tr>


			<tr></tr>
			<tr>
				<td><input type="submit" value="Search" /></td>
			</tr>

		</table>




	</form:form>
</body>
</html>
