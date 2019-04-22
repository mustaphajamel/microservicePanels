package com.imaginepartners.microservice.panels.repository;

import com.imaginepartners.microservice.panels.entities.Panel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanelRepository extends JpaRepository<Panel,Long> {
}
