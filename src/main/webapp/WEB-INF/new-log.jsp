<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

<title>Machine Maintenance</title>
</head>
<body onLoad="onMachineChange();updateImageInput()">
	<form action="/logs/new" method="POST" enctype="multipart/form-data">
		<input type="hidden" id="file-path-input"/>
	
		<select id="machine-select" name="machine" onChange="onMachineChange()">
			<c:forEach var="machine" items="${machineList}">
				<option value="${machine.id}"><c:out value="${machine.name}"/></option>
			</c:forEach>
		</select>
		<div id="maintenance-available" style="display: flex; flex-direction: column;"></div>
		
		<input type="file" id="image-input" name="image" accept="image/jpeg, image/png, image/jpg" multiple onChange="updateImageInput()"/>
		
		<div id="image-div" style="display: flex;"></div>
		
		<input type="submit" value="Save Log"/>
	</form>
	<script type="text/javascript" src="js/app.js"></script>
</body>
</html>