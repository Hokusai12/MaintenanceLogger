package com.rapidprototypes.machinemaintenancelogger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidprototypes.machinemaintenancelogger.models.Picture;
import com.rapidprototypes.machinemaintenancelogger.repositories.PictureRepository;

@Service
public class PictureService {
	@Autowired
	PictureRepository repo;
	
	public Picture savePicture(Picture newPicture) {
		return repo.save(newPicture);
	}
}
