<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
	
	<img id="img-thing"/>
	<script>
		var imageInput = document.getElementById("image-input");
		
		function updateImageInput(event) {
			var imageDiv = document.getElementById("image-div");
			imageDiv.innerHTML = "";
			for(var i = 0; i < imageInput.files.length; i++) {
				
				let file = imageInput.files[i];
				let img = document.createElement("img");
				
				console.log(file);
				
			    img.file = file;
			    imageDiv.appendChild(img);
			    img.style= "width: auto; height: 200px; margin-right: 10px;";
			    
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
				maintenanceDiv.innerHTML += "<input name='maintenance-" + i + "' type='checkbox' value='" + maintenanceList["maintenance-ids"][i] + "'/><label>" + maintenanceList["maintenance-texts"][i] + "</label>";
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
</body>
</html>