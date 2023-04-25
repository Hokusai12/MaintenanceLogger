package com.rapidprototypes.machinemaintenancelogger.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
		Log log = logService.findById(id);
		model.addAttribute("log", log);
		model.addAttribute("pictureList", log.getPictures());
		return "show-log.jsp";
	}
	
	@PostMapping("/logs/new")
	public String saveLog(@RequestParam("image") List<MultipartFile> multipartFiles, @RequestParam Map<String, String> allParams) {
		Log newLog = new Log();
		Machine machine = machineService.findById(Long.parseLong(allParams.get("machine")));
		List<MaintenanceItem> maintenancePerformed = new ArrayList<MaintenanceItem>();
		List<Picture> logPictureList = new ArrayList<Picture>();
		
		for(int i = 0; i < machine.getMaintenanceItems().size(); i++) {
			if(allParams.get("maintenance-".concat(String.valueOf(i))) == null) continue;
			
			MaintenanceItem maintenance = maintenanceService.findById(Long.parseLong(allParams.get("maintenance-".concat(String.valueOf(i)))));
			maintenancePerformed.add(maintenance);
		}
		
			
		newLog.setMachine(machine);
		newLog.setMaintenancePerformed(maintenancePerformed);
		
		
		for(int i = 0; i < multipartFiles.size(); i++) {
			if(multipartFiles.get(i).isEmpty()) continue;
			
			Picture picture = new Picture();
			
			byte imageData[] = null;
			try {
				imageData = multipartFiles.get(i).getBytes();
			} catch(IOException e) {
				System.err.println("Couldn't read image data to byte array. Error: " + e.getMessage());
			}
			
			String pictureFileName = multipartFiles.get(i).getOriginalFilename();
			String pictureFileType = pictureFileName.substring(pictureFileName.indexOf(".") + 1);
			String imageDataString = Base64.getEncoder().encodeToString(imageData);
			
			picture.setFileType(pictureFileType);
			picture.setFileName(pictureFileName);
			picture.setImageDataBase64(imageDataString);
			logPictureList.add(picture);
			
			try {
				FileUtil.saveResource(multipartFiles.get(i));
			} catch(IOException e) {
				System.out.println("IOException: " + e.getMessage());
			}
		}	
		
		newLog.setPictures(logPictureList);
		Log savedLog = logService.saveLog(newLog);
		
		for(Picture picture : logPictureList) {
			picture.setLog(savedLog);
			pictureService.savePicture(picture);
		}
		
		
		try {
			FileUtil.saveHTML(logService.getLogHTML(savedLog));
		} catch(IOException e) {
			System.err.println("Couldn't save HTML file. Error: " + e.getMessage());
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
