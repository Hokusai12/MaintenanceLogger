package com.rapidprototypes.machinemaintenancelogger.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rapidprototypes.machinemaintenancelogger.models.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, Long>{
	public List<Log> findAll();
}
