package com.testdev.entity_crud_rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testdev.entity_crud_rest.modelo.Client;
import com.testdev.entity_crud_rest.modelo.Project;
import com.testdev.entity_crud_rest.repository.ClientRepository;
import com.testdev.entity_crud_rest.repository.ProjectRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/project")
@Validated
@CrossOrigin(origins = "*")
public class ProjectController {

	
	@Autowired
    private ProjectRepository projectRepository;

    // Adicionar um novo projeto
    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@Valid @RequestBody Project project) {
        Project savedProject = projectRepository.save(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    
    @Autowired
    private CrudRepository<Client, Long> ClientRepository;
    
    
    // Editar um projeto existente
    @PutMapping("/edit/{project_id}")
    public ResponseEntity<String> editProject(@PathVariable Long project_id, @Valid @RequestBody Project projectDetails) {
        // Verifica se o projeto com o ID fornecido existe no banco de dados
        Optional<Project> projectOptional = projectRepository.findById(project_id);
        if (!projectOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Retorna uma resposta 404 se o projeto não for encontrado
        }
        
     // Obtém o cliente associado ao projeto
        Optional<Client> clientOptional = ClientRepository.findById(projectDetails.getClient().getId());
        
        // Verifica se o cliente foi encontrado
        if (!clientOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Cliente não encontrado com o id: " + projectDetails.getClient().getId());
        }
        
        // Copia os detalhes do projeto recebido no corpo da requisição para o projeto existente
        Project project = projectOptional.get();
        project.setProject_name(projectDetails.getProject_name());
        project.setProject_description(projectDetails.getProject_description());
        project.setStatus(projectDetails.getStatus());
        project.setDt_init(projectDetails.getDt_init());
        project.setDt_end(projectDetails.getDt_end());
        
        // Atribui o cliente ao projeto
        project.setClient(clientOptional.get());

        // Salva o projeto atualizado no banco de dados
        Project updatedProject = projectRepository.save(project);
        
        if (updatedProject != null) {
            return ResponseEntity.ok("Projeto editado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao editar o projeto.");
        }
    }

    // Visualizar um projeto pelo ID
    @GetMapping("/{project_id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        return projectOptional.map(project -> ResponseEntity.ok().body(project))
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir um projeto pelo ID
    @DeleteMapping("/delete/{project_id}")
    public ResponseEntity<String> deleteProject(@PathVariable("project_id") Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (!projectOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        projectRepository.deleteById(projectId);
        return ResponseEntity.ok("Project deleted successfully");
    }

    
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok(projects);
    }
}
