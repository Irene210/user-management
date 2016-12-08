<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="zh-cn">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Show All Users</title>
</head>
<body>
<div align="center"><h2>User Management System</h2></div>
	<div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Name</th>
				<th>Age</th>
				<th>Sex</th>
				<th>Interests</th>
				<th colspan="2">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.id}" /></td>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.age}" /></td>
					<td><c:out value="${user.sex}" /></td>
					<td><c:out value="${user.interests}" /></td>
					<td><a href="<c:url value="/update?id=${user.id}"/>">Update</a></td>
					<td><a href="<c:url value="/delete?id=${user.id}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<p>
		<a href="<c:url value="/addUser"/>">Add User</a>
	</p>
</body>
</html>