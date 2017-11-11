<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="validLogin.html" method="post" modelAttribute="user">
		<table>
			<tr>

				<td>Username:</td>
				<td><form:input path="username" type="text"/> <form:errors
						path="username" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="password" type="text"/> <form:errors
						path="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Login" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>