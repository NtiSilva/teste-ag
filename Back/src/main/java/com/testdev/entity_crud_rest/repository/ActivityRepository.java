package com.testdev.entity_crud_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testdev.entity_crud_rest.modelo.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}

