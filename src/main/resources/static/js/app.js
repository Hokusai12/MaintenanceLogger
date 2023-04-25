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