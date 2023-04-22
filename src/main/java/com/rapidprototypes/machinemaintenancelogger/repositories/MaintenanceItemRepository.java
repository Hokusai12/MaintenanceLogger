package com.rapidprototypes.machinemaintenancelogger.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rapidprototypes.machinemaintenancelogger.models.MaintenanceItem;

@Repository
public interface MaintenanceItemRepository extends CrudRepository<MaintenanceItem, Long>{
	public List<MaintenanceItem> findAll();
}
