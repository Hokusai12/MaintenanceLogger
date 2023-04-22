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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="logs")
public class Log {
	
	////	ATTRIBUTES
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String note;
	
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
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="maintenance_logs",
			joinColumns = @JoinColumn(name="log_id"),
			inverseJoinColumns = @JoinColumn(name="maintenance_id")
	)
	private List<MaintenanceItem> maintenancePerformed;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="machine_id")
	private Machine machine;
	
	@OneToMany(mappedBy="log", fetch=FetchType.LAZY)
	private List<Picture> pictures;
	
	////	CONSTRUCTORS
	
	public Log() {}
	
	////	GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MaintenanceItem> getMaintenancePerformed() {
		return maintenancePerformed;
	}

	public void setMaintenancePerformed(List<MaintenanceItem> maintenancePerformed) {
		this.maintenancePerformed = maintenancePerformed;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
}
