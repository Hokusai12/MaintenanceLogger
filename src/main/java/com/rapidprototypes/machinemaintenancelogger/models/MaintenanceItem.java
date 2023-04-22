package com.rapidprototypes.machinemaintenancelogger.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="maintenance_items")
public class MaintenanceItem {
	
	////	ATTRIBUTES
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	////	RELATIONSHIPS
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="machines_maintenance",
			joinColumns = @JoinColumn(name="maintenance_id"),
			inverseJoinColumns = @JoinColumn(name="machine_id")
	)
	private List<Machine> machines;
	
	@ManyToMany(fetch=FetchType.LAZY) 
	@JoinTable(
			name="maintenance_logs",
			joinColumns = @JoinColumn(name="maintenance_id"),
			inverseJoinColumns = @JoinColumn(name="log_id")
	)
	private List<Log> logs;
	
	////	CONSTRUCTORS
	
	public MaintenanceItem() {}
	
	////	GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}
}
