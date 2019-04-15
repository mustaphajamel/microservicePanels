package com.imaginepartners.microservice.panels.controllers;


import com.imaginepartners.microservice.panels.entities.Agence;
import com.imaginepartners.microservice.panels.repository.AgenceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/agences"})
public class AgenceController {

    private final AgenceRepository agenceRepository;

    AgenceController(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }


    @GetMapping("/all")
    ResponseEntity<List<Agence>> all() {
        return new ResponseEntity<>(agenceRepository.findAll(), HttpStatus.OK);

    }
    @GetMapping("/{id}")
    ResponseEntity<Agence> getAgence(@PathVariable final Long id) {
        return new ResponseEntity<>(agenceRepository.findById(id).get(), HttpStatus.OK);
    }


    @PostMapping(path ="/add", consumes = "application/json", produces = "application/json")
    ResponseEntity add(@RequestBody Agence agence){
        agenceRepository.save(agence);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/Delete/{id}")
    ResponseEntity delete(@PathVariable final Long id){
        agenceRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }



}
