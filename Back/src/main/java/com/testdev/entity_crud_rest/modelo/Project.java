package com.testdev.entity_crud_rest.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;
    
    @NotBlank(message = "O nome deve ser informado")
    @Size(min = 2, max = 15, message= "O nome deve ter no mínimo 2 caracteres e no máximo 15 caracteres")
    private String project_name;
    
    private String project_description;
    private String status;
    
    @ManyToOne
    private Client client;
    
    private Date dt_init;
    private Date dt_end;
    
	public Long getProjectId() {
		return project_id;
	}
	public void setProjectId(Long project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_description() {
		return project_description;
	}
	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getDt_init() {
		return dt_init;
	}
	public void setDt_init(Date dt_init) {
		this.dt_init = dt_init;
	}
	public Date getDt_end() {
		return dt_end;
	}
	public void setDt_end(Date dt_end) {
		this.dt_end = dt_end;
	}

    
}
