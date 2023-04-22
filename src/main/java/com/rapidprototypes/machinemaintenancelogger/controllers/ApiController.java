package com.rapidprototypes.machinemaintenancelogger.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapidprototypes.machinemaintenancelogger.services.MachineService;

@RestController
public class ApiController {
	
	@Autowired
	MachineService machineService;
	
	@RequestMapping("/machine/{machineId}/available-maintenance")
	public String getAvailableMaintenance(@PathVariable(name="machineId") Long machineId) {
		JSONObject availableMaintenance = machineService.getAvailableMaintenanceJSON(machineId);
		if(availableMaintenance == null) {
			System.out.println("Maintenance is null");
		}
		System.out.println("JSON in Controller: " + availableMaintenance.toString());
		return availableMaintenance.toString();
	}
}
