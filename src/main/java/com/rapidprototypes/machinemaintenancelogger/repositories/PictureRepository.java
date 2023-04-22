package com.rapidprototypes.machinemaintenancelogger.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rapidprototypes.machinemaintenancelogger.models.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long>{
	
}
