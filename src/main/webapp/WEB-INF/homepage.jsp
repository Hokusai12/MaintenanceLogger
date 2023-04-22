<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Machine Maintenance</title>
</head>
<body>
	<a href="/logs/new">New Log</a>
	<a href="/machines">View Machines</a>
	<c:forEach var="log" items="${logList}">
		<p><a href="/logs/${log.id}"><c:out value="${log.id}"/></a></p>
		<ul>
		<c:forEach var="maintenance" items="${log.maintenancePerformed}">
			<li><c:out value="${maintenance.name}"/></li>
		</c:forEach>
		</ul>
	</c:forEach>
</body>
</html>