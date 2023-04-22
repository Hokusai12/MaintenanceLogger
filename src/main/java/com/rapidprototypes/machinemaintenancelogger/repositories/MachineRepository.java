package com.rapidprototypes.machinemaintenancelogger.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rapidprototypes.machinemaintenancelogger.models.Machine;

@Repository
public interface MachineRepository extends CrudRepository<Machine, Long>{
	public List<Machine> findAll();
}
