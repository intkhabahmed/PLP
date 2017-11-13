<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Signup | MyAirlines</title>
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
	<div class="container" id="signupFormContainer">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-4">
					<c:if test="${message!=''}">
							<p class="text-center">${message}</p>
						</c:if>
						<div class="panel panel-primary padding-5x" id="signupFormBody">
							<div class="panel-heading">
								<h1 class="text-center">Signup Form</h1>
							</div>
							<div class="panel-body">
								
								<hr>
								<form:form class="form-horizontal" action="signup.html" method="post" modelAttribute="userObj">
									<div class="form-group">
										<form:label path="username">Username:</form:label>
										<form:input type="text" class="form-control" path="username" required="required"/>
										<form:errors path="username"/><br>
									</div>
									<div class="form-group">
										<form:label path="password">Password:</form:label>
										<form:input type="password" class="form-control" path="password" required="required"/>
										<form:errors path="password"/><br>
									</div>
									<div class="form-group">
										<form:label path="email">Email:</form:label>
										<form:input type="email" class="form-control" path="email" required="required" />
										<form:errors path="email"/><br>
									</div>

									<div class="form-group">
										<form:label path="mobileNo">Mobile No:</form:label>
										<form:input type="text" class="form-control" path="mobileNo" required="required"/>
										<form:errors path="mobileNo"/><br>
									</div>
									
									<div class="row">
										<div class="text-center">
											<input type="submit" value="Signup" class="btn btn-danger btn-lg"/>
										</div>	
									</div>
								</form:form>
								<h4 align="center">Already have account? <a href="showLogin.html">Login here</a></h4>
							</div><!--end panel body-->
						</div><!--end panel-->
				</div>
			</div><!--Form container end-->
		</div>
		<img alt="no image" src="./WebContent/WEB-INF/image/V4XBG.jpg">
</body>
</html>