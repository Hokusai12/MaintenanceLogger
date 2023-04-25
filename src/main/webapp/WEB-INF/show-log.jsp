<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<title>Maintenance Logger</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg bg-warning">
			<div class="container-fluid">
				<h1 class="navbar-brand fs-1">Maintenance Logger</h1>
				<div class="navbar-nav">
					<a class="nav-link" href="/">Home</a>
					<a class="nav-link" href="/logs/new">New Log</a>
					<a class="nav-link" href="/machines">Your Machines</a>
				</div>
			</div>
		</nav>
	</header>

	<main class="bg-warning-subtle pt-5" style="min-height: 100vh">
		<div class="container mx-5 d-flex justify-content-around">
		<!-- Left column -->
			<div class="col-5">
				<div class="card border-warning border-3" style="width: 36rem">
					<div class="card-header bg-warning-subtle border-warning border-3">
						<h1><fmt:formatDate pattern="MMMM dd, yyyy" value="${log.createdAt}"/></h1>
					</div>
					<div class="card-body">
						<h3>${log.machine.name}</h3>
						<ul>
							<c:forEach var="maintenance" items="${log.maintenancePerformed}">
								<li>${maintenance.name}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<!-- Right column -->
			<div class="col-5 d-flex gap-2 flex-wrap justify-content-around">
				<c:forEach var="picture" items="${pictureList}">
					<img src="data:image/${picture.fileType};base64, ${picture.imageDataBase64}" style="height:300px;"/>
				</c:forEach>
			</div>
		</div>
	</main>
	
</body>
</html>