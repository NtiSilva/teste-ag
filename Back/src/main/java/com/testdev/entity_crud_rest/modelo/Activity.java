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
public class Activity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message = "O nome deve ser informado")
    @Size(min = 2, max = 15, message= "O nome deve ter no mínimo 2 caracteres e no máximo 15 caracteres")
    private String name;
	
	private String description;
	
	private Date dt_init;
    private Date dt_end;
    
    private String status;
    private String prioridade;
    
   
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	@ManyToOne
	private Project project_id;


	public Long getProject_id() {
		return project_id != null ? project_id.getProjectId() : null;
    }

    public void setProject_id(Project project_id) {
        this.project_id = project_id;
    }
   

}
