package com.rapidprototypes.machinemaintenancelogger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pictures")
public class Picture {
	
	////	ATTRIBUTES
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=true, length=64)
	private String filename;
	
	////	RELATIONSHIPS
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="log_id")
	private Log log;
	
	////	CONSTRUCTORS
	
	public Picture() {}
	
	////	GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
}
