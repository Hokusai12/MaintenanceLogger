<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Maintenance Logger</title>
</head>
<body>
	<c:forEach var="machine" items="${machineList}">
		<p><c:out value="${machine.name}"/></p>
		<ul>
			<c:forEach var="maintenance" items="${machine.maintenanceItems}">
				<li><c:out value="${maintenance.name}"/></li>
			</c:forEach>
		</ul>
	</c:forEach>
</body>
</html>