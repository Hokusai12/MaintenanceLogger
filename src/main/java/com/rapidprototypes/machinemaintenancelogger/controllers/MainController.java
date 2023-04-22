package com.rapidprototypes.machinemaintenancelogger.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rapidprototypes.machinemaintenancelogger.models.Log;
import com.rapidprototypes.machinemaintenancelogger.models.Machine;
import com.rapidprototypes.machinemaintenancelogger.models.MaintenanceItem;
import com.rapidprototypes.machinemaintenancelogger.models.Picture;
import com.rapidprototypes.machinemaintenancelogger.services.FileUtil;
import com.rapidprototypes.machinemaintenancelogger.services.LogService;
import com.rapidprototypes.machinemaintenancelogger.services.MachineService;
import com.rapidprototypes.machinemaintenancelogger.services.MaintenanceItemService;
import com.rapidprototypes.machinemaintenancelogger.services.PictureService;

@Controller
public class MainController {
	
	////	SERVICES
	@Autowired
	MaintenanceItemService maintenanceService;
	@Autowired
	MachineService machineService;
	@Autowired
	LogService logService;
	@Autowired
	PictureService pictureService;
	
	////	HOME ROUTES    ////
	
	@GetMapping("/")
	public String homepage(Model model) {
		model.addAttribute("logList", logService.getAll());
		
		return "homepage.jsp";
	}
	
	////	LOG ROUTES    ////
	
	@GetMapping("/logs/new")
	public String newLogForm(Model model) {		
		model.addAttribute("maintenanceList", maintenanceService.getAll());
		model.addAttribute("machineList", machineService.getAll());
		
		return "new-log.jsp";
	}
	
	@GetMapping("/logs/{id}")
	public String showLog(@PathVariable("id") Long id, Model model) {
		model.addAttribute("log", logService.findById(id));
		
		return "show-log.jsp";
	}
	
	@PostMapping("/logs/new")
	public String saveLog(@RequestParam("image") List<MultipartFile> multipartFiles, @RequestParam Map<String, String> allParams) {
		Log newLog = new Log();
		Machine machine = machineService.findById(Long.parseLong(allParams.get("machine")));
		List<MaintenanceItem> maintenancePerformed = new ArrayList<MaintenanceItem>();
		
		for(int i = 0; i < machine.getMaintenanceItems().size(); i++) {
			if(allParams.get("maintenance-".concat(String.valueOf(i))) == null) continue;
			
			MaintenanceItem maintenance = maintenanceService.findById(Long.parseLong(allParams.get("maintenance-".concat(String.valueOf(i)))));
			maintenancePerformed.add(maintenance);
		}
		
			
		newLog.setMachine(machine);
		newLog.setMaintenancePerformed(maintenancePerformed);
		
		logService.saveLog(newLog);
		
		for(int i = 0; i < multipartFiles.size(); i++) {
			if(multipartFiles.get(i).isEmpty()) continue;
			
			Picture picture = new Picture();
			picture.setFilename(multipartFiles.get(i).getOriginalFilename());
			picture.setLog(newLog);
			pictureService.savePicture(picture);
			
			
			try {
				FileUtil.saveResource(multipartFiles.get(i));
			} catch(IOException e) {
				System.out.println("IOException: " + e.getMessage());
			}
		}
		
		return "redirect:/";
	}
	
	////	MACHINE ROUTES
	
	@GetMapping("/machines")
	public String showMachines(Model model) {
		model.addAttribute("machineList", machineService.getAll());
		return "show_machines.jsp";
	}
}
