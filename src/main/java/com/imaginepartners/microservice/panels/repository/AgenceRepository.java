package com.imaginepartners.microservice.panels.repository;


import com.imaginepartners.microservice.panels.entities.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface AgenceRepository extends JpaRepository<Agence,Long> {

}
