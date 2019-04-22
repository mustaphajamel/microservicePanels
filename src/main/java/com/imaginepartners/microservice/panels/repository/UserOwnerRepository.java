package com.imaginepartners.microservice.panels.repository;

import com.imaginepartners.microservice.panels.entities.UserOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOwnerRepository extends JpaRepository<UserOwner,Long> {
    Optional<UserOwner> findByIdUser(Long idUser);
}
