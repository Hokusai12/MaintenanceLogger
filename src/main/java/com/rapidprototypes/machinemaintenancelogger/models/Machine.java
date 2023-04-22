package com.rapidprototypes.machinemaintenancelogger.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="machines")
public class Machine {
	
	////	ATTRIBUTES
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min=3)
	private String name;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@PrePersist
	private void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	private void onUpdate() {
		this.updatedAt = new Date();
	}
	
	////	RELATIONSHIPS
	
	@OneToMany(mappedBy="machine", fetch=FetchType.LAZY)
	private List<Log> logs;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="machines_maintenance",
			joinColumns = @JoinColumn(name="machine_id"),
			inverseJoinColumns = @JoinColumn(name="maintenance_id")
	)
	private List<MaintenanceItem> maintenanceItems;
	
	////	CONSTRUCTORS
	
	public Machine() {}

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<MaintenanceItem> getMaintenanceItems() {
		return maintenanceItems;
	}

	public void setMaintenanceItems(List<MaintenanceItem> maintenanceItems) {
		this.maintenanceItems = maintenanceItems;
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}
}
