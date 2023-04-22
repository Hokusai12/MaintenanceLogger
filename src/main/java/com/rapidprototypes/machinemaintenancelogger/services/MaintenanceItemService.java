package com.rapidprototypes.machinemaintenancelogger.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidprototypes.machinemaintenancelogger.models.MaintenanceItem;
import com.rapidprototypes.machinemaintenancelogger.repositories.MaintenanceItemRepository;

@Service
public class MaintenanceItemService {
	
	@Autowired
	MaintenanceItemRepository repo;
	
	public List<MaintenanceItem> getAll() {
		return repo.findAll();
	}
	
	public MaintenanceItem findById(Long id) {
		Optional<MaintenanceItem> maintenance = repo.findById(id);
		if(maintenance.isPresent()) {
			return maintenance.get();
		}
		return null;
	}
}
