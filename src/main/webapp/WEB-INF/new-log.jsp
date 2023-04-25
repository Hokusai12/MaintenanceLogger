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
<script>
function updateImageInput(event) {
	var imageInput = document.getElementById("image-input");
	var imageDiv = document.getElementById("image-div");
	imageDiv.innerHTML = "";
	for(var i = 0; i < imageInput.files.length; i++) {
		
		let file = imageInput.files[i];
		let img = document.createElement("img");
		
		console.log(file);
		
	    img.file = file;
	    imageDiv.appendChild(img);
	    img.style= "width: auto; height: 150px; margin-right: 10px;";
	    
    	const reader = new FileReader();			    
	    reader.onload = (e) => {
	      img.src = e.target.result;
	    };
	    reader.readAsDataURL(file);
	}
}

async function onMachineChange() {
	var maintenanceDiv = document.querySelector("#maintenance-available");
	const machine = document.querySelector("#machine-select").value;
	const maintenanceList = await getMachineAvailableMaintenance(machine);
	
	
	maintenanceDiv.innerHTML = "";
	
	for(var i = 0; i < maintenanceList["maintenance-ids"].length; i++) {
		maintenanceDiv.innerHTML += "<div class=\"mb-3 d-flex justify-content-between\"><input class=\"form-checkbox\" name='maintenance-" + i + "' type='checkbox' value='" + await maintenanceList["maintenance-ids"][i] + "'/><label class=\"form-label\">" + await maintenanceList["maintenance-texts"][i] + "</label></div>";
	}
}

async function getMachineAvailableMaintenance(machineId) {
	let fetch_url = "http://localhost:8080/machine/" + machineId.toString() + "/available-maintenance";
	const response = await fetch(fetch_url);
	if(response.ok) {
		const jsonData = await response.json();
		console.log(jsonData);
		return jsonData;
	} else {
		alert("HTTP Request Failed! Error Code: " + response.status.toString());
	}
}
</script>
</head>
<body onLoad="onMachineChange();updateImageInput()">	
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
	<main class="bg-warning-subtle p-5" style="min-height: 100vh">
		<div class="card border-0">
			<div class="card-header bg-warning mb-3">
				<h1>New Log</h1>
			</div>
			<div class="card-body d-flex flex-column">
				<form action="/logs/new" method="POST" enctype="multipart/form-data" name="logForm" class="form d-flex gap-2">
					<input type="hidden" id="file-path-input"/>
					<!-- Left -->
					<div class="mb-3 d-flex flex-column align-items-start col-6">			
						<select class="form-select bg-warning-subtle" id="machine-select" name="machine" onChange="onMachineChange()">
							<c:forEach var="machine" items="${machineList}">
								<option value="${machine.id}"><c:out value="${machine.name}"/></option>
							</c:forEach>
						</select>
		
						<div id="maintenance-available" class="d-flex flex-column col-6 mt-3"></div>
					</div>
					<!-- Right -->
					<div class="col-6 d-flex flex-column">			
						<input type="file" id="image-input" name="image" class="form-control bg-warning-subtle" accept="image/jpeg, image/png, image/jpg" multiple onChange="updateImageInput()"/>
						
						<div id="image-div" class="d-flex flex-wrap"></div>
					</div>
				</form>
				<input class="btn bg-warning col-2 align-self-start me-5" form="logForm" type="submit" value="Save Log"/>
			</div>
		</div>
	</main>
</body>
</html>