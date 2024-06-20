package com.testdev.entity_crud_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testdev.entity_crud_rest.modelo.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
