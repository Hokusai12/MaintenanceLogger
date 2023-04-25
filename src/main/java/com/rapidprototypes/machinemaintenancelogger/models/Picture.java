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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pictures")
public class Picture {
	
	////	ATTRIBUTES
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(columnDefinition="longtext")
	private String imageDataBase64;
	
	@NotNull
	private String fileType;
	
	@NotNull
	private String fileName;
	
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

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getImageDataBase64() {
		return imageDataBase64;
	}

	public void setImageDataBase64(String imageDataBase64) {
		this.imageDataBase64 = imageDataBase64;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
