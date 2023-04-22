package com.rapidprototypes.machinemaintenancelogger.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidprototypes.machinemaintenancelogger.models.Machine;
import com.rapidprototypes.machinemaintenancelogger.models.MaintenanceItem;
import com.rapidprototypes.machinemaintenancelogger.repositories.MachineRepository;

@Service
public class MachineService {
	
	@Autowired
	MachineRepository repo;
	
	public List<Machine> getAll() {
		return repo.findAll();
	}
	
	public Machine findById(Long id) {
		Optional<Machine> machine = repo.findById(id);
		if(machine.isPresent()) {
			return machine.get();
		}
		return null;
	}
	
	public JSONObject getAvailableMaintenanceJSON(Long id) {
		Machine machine = findById(id);
		JSONObject maintenanceJSON = new JSONObject();
		List<Long> maintenanceIdList = new ArrayList<Long>();
		List<String> maintenanceTextList = new ArrayList<String>();
		
		for(MaintenanceItem maintenance : machine.getMaintenanceItems()) {
			maintenanceIdList.add(maintenance.getId());
			maintenanceTextList.add(maintenance.getName());
		}
		
		maintenanceJSON.put("maintenance-ids", maintenanceIdList);
		maintenanceJSON.put("maintenance-texts", maintenanceTextList);
		
		System.out.println("JSON In Service: " + maintenanceJSON.toString());
		
		return maintenanceJSON;
	}
	
	public Machine update(Machine machine) {
		return repo.save(machine);
	}
}
