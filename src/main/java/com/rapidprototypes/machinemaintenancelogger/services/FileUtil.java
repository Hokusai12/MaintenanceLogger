package com.rapidprototypes.machinemaintenancelogger.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static void saveResource(MultipartFile mpFile) throws IOException {
		
		Calendar cal = Calendar.getInstance();
		
		String folderName = String.format("%d%d%d-%d%d", cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE), cal.get(Calendar.YEAR), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
		
		Path uploadPath = Paths.get("../Logs/" + folderName);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = mpFile.getInputStream()){
			Path filePath = uploadPath.resolve(mpFile.getOriginalFilename());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch(Exception e) {
			throw new IOException("Couldn't read file: " + mpFile.getOriginalFilename(), e);
		}
	}
	
	public static void saveHTML(String htmlString) throws IOException { 
		
		Calendar cal = Calendar.getInstance();
		
		String folderName = String.format("%d%d%d-%d%d", cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE), cal.get(Calendar.YEAR), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
		
		Path uploadPath = Paths.get("../Logs/" + folderName);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		
		
		try (FileOutputStream out = new FileOutputStream("log-" + folderName + ".html")){
		} catch(Exception e) {
			throw new IOException("Couldn't read log text because: " + e.getMessage(), e);
		}
		
	}
}
