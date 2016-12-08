<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Add New User</title>
</head>
<body>

	<form action="/addOrUpdate" method="POST">
	       <div class="form-group">
                 <label for="user_id">ID:</label>
             <input type="text" class="form-control" name="user_id" value="<c:out value="${user.id}" />"
             readonly="readonly" placeholder="User ID" />
            </div>

			<div class="form-group">
				<label for="user_name">Name:</label>
				<input type="text" class="form-control"name="user_name" value="<c:out value="${user.name}" />"
					placeholder="User Name" />
			</div>
			<div class="form-group">
				<label for="user_age">Age:</label>
				 <input type="text" class="form-control" name="user_age" value="<c:out value="${user.age}" />"
					placeholder="User Age" />
			</div>
			<div class="form-group">
				<label for="user_sex">Sex:</label>
				woman<input type="radio" name="user_sex" value="1" checked="checked" />

                 man<input type="radio" name="user_sex" value="2"
                                                  <c:if test="${user.sex=='2'}">checked="checked"</c:if> />
			</div>
			<div class="form-group">
				<label for="user_interests">Interests:</label>
				<input type="text" class="form-control"name="user_interests" value="<c:out value="${user.interests}" />"
				placeholder="User Interests" />
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
	</form>
</body>
</html>