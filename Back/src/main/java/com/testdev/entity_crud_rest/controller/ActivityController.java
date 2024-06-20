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

import com.testdev.entity_crud_rest.modelo.Activity;
import com.testdev.entity_crud_rest.modelo.Client;
import com.testdev.entity_crud_rest.modelo.Project;
import com.testdev.entity_crud_rest.repository.ActivityRepository;
import com.testdev.entity_crud_rest.repository.ProjectRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/activity")
@Validated
@CrossOrigin(origins = "*")
public class ActivityController {
	
	@Autowired
    private ActivityRepository activityRepository;

    // Adicionar uma nova atividade
    @PostMapping("/add")
    public ResponseEntity<Activity> addActivity(@Valid @RequestBody Activity activity) {
        Activity savedActivity = activityRepository.save(activity);
        return new ResponseEntity<>(savedActivity, HttpStatus.CREATED);
    }

    // Editar uma atividade existente
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editActivity(@PathVariable Long id, @Valid @RequestBody Activity activityDetails) {
        // Verifica se a atividade com o ID fornecido existe no banco de dados
        Optional<Activity> activityOptional = activityRepository.findById(id);
        if (!activityOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Retorna uma resposta 404 se a atividade não for encontrada
        }
        
        
        // colocar a verificção de projetos
        

        // Copia os detalhes da atividade recebida no corpo da requisição para a atividade existente
        Activity activity = activityOptional.get();
        activity.setName(activityDetails.getName());
        activity.setDescription(activityDetails.getDescription());
        activity.setDt_init(activityDetails.getDt_init());
        activity.setDt_end(activityDetails.getDt_end());
        activity.setStatus(activityDetails.getStatus());
        activity.setPrioridade(activityDetails.getPrioridade());

        Activity updatedActivity = activityRepository.save(activity);
        return ResponseEntity.ok("Activity updated successfully: " + updatedActivity.toString());
    }

    // Visualizar uma atividade pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);
        return activityOptional.map(activity -> ResponseEntity.ok().body(activity))
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir uma atividade pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable Long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);
        if (!activityOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        activityRepository.deleteById(id);
        return ResponseEntity.ok("Activity deleted successfully");
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        return ResponseEntity.ok(activities);
    }
}
