package com.rapidprototypes.machinemaintenancelogger.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidprototypes.machinemaintenancelogger.models.Log;
import com.rapidprototypes.machinemaintenancelogger.models.MaintenanceItem;
import com.rapidprototypes.machinemaintenancelogger.repositories.LogRepository;

@Service
public class LogService {
	
	@Autowired
	LogRepository repo;
	
	public List<Log> getAll() {
		return repo.findAll();
	}
	
	public Log findById(Long id) {
		Optional<Log> log = repo.findById(id);
		
		if(log.isPresent()) {
			return log.get();
		}
		
		return null;
	}
	
	public Log saveLog(Log newLog) {		
		
		Calendar cal = Calendar.getInstance();
		String currentDate = String.format("%d%d%d-%d%d", cal.get(Calendar.MONTH), cal.get(Calendar.DATE), cal.get(Calendar.YEAR), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
		
		String logHTML = String.format("<!DOCTYPE html><html><head><meta charset='utf-8'/><title>%s</title></head><body><h1>%s</h1><h3>%s</h3><ul>", currentDate, currentDate, newLog.getMachine().getName());
		for(MaintenanceItem maintenance : newLog.getMaintenancePerformed()) {
			logHTML = logHTML.concat(String.format("<li>%s</li>", maintenance.getName()));
		}
		
		logHTML = logHTML.concat("</ul></body></html>");
		
		try {
			FileUtil.saveHTML(logHTML);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return repo.save(newLog);
	}
}
