<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
  <%@include file="../css/bootstrap.min.css"%>
  <%@include file="../css/font-awesome.min.css"%>
  <%@include file="../css/custom.css"%>
</style>
<script>
  
<%@include file="../js/jquery-3.1.0.min.js" %>
  
<%@include file="../js/bootstrap.min.js" %>
  
</script>
<link
  href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"
  rel="stylesheet">
<title>My Profile | MyAirlines</title>
</head>
<body>
  <jsp:include page="header.jsp" />
  <!--Navigation bar-->
  <div class="container" id="bookingContainer">
    <div class="row">
      <h1 class="text-center">User Profile</h1>
      <hr>
      <c:if test="${message!=''}">
				<h3 class="text-center">${message}</h3>
			</c:if>
        <div id="UserProfile" class="col-sm-4">
        	
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h2 class="text-center">Your Personal Details</h2>
            </div>
            <div class="panel-body padding-20x">
              <form:form class="form-horizontal" action="updateUser.html" method="post" modelAttribute="userObj">
                  <div class="form-group">
                    <form:label path="userId">UserId:</form:label>
                    <form:input type="text" class="form-control" path="userId" required="required" readonly="true"/>
                  </div>
                  <div class="form-group">
                    <form:label path="username">Username:</form:label>
                    <form:input type="text" class="form-control" path="username" required="required" readonly="true"/>
                  </div>
                  <div class="form-group">
                    <form:label path="password">Password:</form:label>
                    <form:input type="password" class="form-control" path="password" required="required"/>
                    <form:errors path="password"/><br>
                  </div>
                  <div class="form-group">
                    <form:label path="email">Email:</form:label>
                    <form:input type="email" class="form-control" path="email" required="required" readonly="true"/>
                  </div>

                  <div class="form-group">
                    <form:label path="mobileNo">Mobile No:</form:label>
                    <form:input type="text" class="form-control" path="mobileNo" required="required"/>
                    <form:errors path="mobileNo"/><br>
                  </div>
                  
                  <div class="row">
                    <div class="text-center">
                      <input type="submit" value="Update" class="btn btn-danger btn-lg"/>
                    </div>  
                  </div>
                </form:form>
            </div>
          </div><!--End Panel-->
        </div>
        <div class="col-sm-6 col-sm-offset-2">
          <div class="panel panel-primary" id="paymentForm">
            <div class="panel-heading">
              <h3 class="text-center panel-title">Your Bookings</h3>
            </div>
            <div class="panel-body" id="bookingsBody">
              <c:if test="${bookings.isEmpty() eq false}">
	              	<table class="table">
	                <tr>
	                  <th>Booking Id</th>
	                  <th>Booked Flight</th>
	                  <th class="text-center">Action</th>
	                </tr>
	                <c:forEach items="${bookings}" var="booking">
	                	<tr>
	                  		<td>${booking.bookingId}</td>
	                  		<td>${booking.flightNo}</td>
	                  		<!-- <td><a href="showEditBooking.html" class="btn btn-danger">Edit</a></td> -->
	                  		<td><a href="cancelBooking.html?bookingId=${booking.bookingId}" class="btn btn-danger">Cancel</a></td>
	                  		<td><a href="viewBooking.html?bookingId=${booking.bookingId}" class="btn btn-danger">View Details</a></td>
	                	</tr>
	                </c:forEach>
	              </table>
              </c:if>
              <c:if test="${bookings.isEmpty() eq true}">
              	<p class="text-center">Sorry No Bookings done by You</p>
              	<div class="text-center"><a href="index.html" class="btn btn-danger btn-lg">Click here to Book</a></div>
              </c:if>
            </div>
          </div>
        </div>
        <!-- End FlightList -->
    </div><!--End Row-->
  </div><!--End Booking Container-->
</body>
</html>