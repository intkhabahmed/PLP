<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Login | MyAirlines</title>
	<style type="text/css">
    <%@include file="../css/bootstrap.min.css" %>
    <%@include file="../css/font-awesome.min.css" %>
    <%@include file="../css/custom.css" %>
</style>
<script>
	<%@include file="../js/jquery-3.1.0.min.js" %>
	<%@include file="../js/bootstrap.min.js" %>
</script>
<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
</head>
<body>
	<jsp:include  page="header.jsp" />
	<!--Navigation bar-->
	<div class="container" id="loginFormContainer">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-4" >
						<c:if test="${message!=''}">
							<p class="text-center">${message}</p>
						</c:if>
						<div class="panel panel-primary padding-5x" id="loginFormBody">
							<div class="panel-heading">
								<h1 class="text-center">Login Form</h1>
							</div>
							<div class="panel-body">

								<hr>
								 <c:if test="${booking.flightNo eq null }">
									<c:set var="action" value="login.html"></c:set>
								</c:if>
								<c:if test="${booking.flightNo ne null }">
									<c:set var="action" value="loginAfterSearch.html"></c:set>
									<c:set var="bookingInfo" value="${booking}" scope="session"> </c:set>
								</c:if>
								<form:form class="form-horizontal" action="${action}" method="post" modelAttribute="user">
									<div class="form-group">
										<form:label path="username">Username:</form:label>
										<form:input type="text" class="form-control" path="username" required="required"/>
									</div>
									<div class="form-group">
										<form:label path="password">Password:</form:label>
										<form:input type="password" class="form-control" path="password" required="required"/>
									</div>
									<div class="row">
										<div class="text-center">
											<input type="submit" value="Login" class="btn btn-danger btn-lg"/>
										</div>	
									</div>
								</form:form>
								<h4 align="center">Forgot Password? <a href="showForgotPassword.html">Click here</a></h4>
								<h4 align="center">Don't have account? <a href="showSignup.html">Signup here</a></h4>
							</div>
						</div>
				</div>
			</div><!--Form container end-->
		</div>
		<jsp:include page="footer.jsp" />
</body>
</html>