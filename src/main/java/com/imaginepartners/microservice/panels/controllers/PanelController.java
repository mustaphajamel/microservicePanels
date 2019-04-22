package com.imaginepartners.microservice.panels.controllers;


import com.imaginepartners.microservice.panels.entities.Panel;
import com.imaginepartners.microservice.panels.entities.UserOwner;
import com.imaginepartners.microservice.panels.repository.PanelRepository;
import com.imaginepartners.microservice.panels.repository.UserOwnerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/panels"})
public class PanelController {

    @Value("${users.endpoint}")
    private String usersExchange;

    private final PanelRepository panelRepository;
    private final UserOwnerRepository userOwnerRepository;
    private final RestTemplate restTemplate;

    public PanelController(PanelRepository panelRepository, UserOwnerRepository userOwnerRepository, RestTemplate restTemplate) {
        this.panelRepository = panelRepository;
        this.userOwnerRepository = userOwnerRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/all")
    ResponseEntity<List<Panel>> all() {
        return new ResponseEntity<>(panelRepository.findAll(), HttpStatus.OK);

    }
    @PostMapping(path ="/add", consumes = "application/json", produces = "application/json")
    ResponseEntity addPanel(@RequestBody Panel panel){
        panelRepository.save(panel);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/{id}")
     ResponseEntity<?> replacePanel(@RequestBody Panel newPanel, @PathVariable Long id) {
        return panelRepository.findById(id)
                .map(panel -> {
                    panel.setCategory(newPanel.getCategory());
                    panel.setModel(newPanel.getModel());
                    panel.setNumberFace(newPanel.getNumberFace());
                    panelRepository.save(panel);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.badRequest().build());
        
    }

    @DeleteMapping("/Delete/{id}")
    ResponseEntity delete(@PathVariable final Long id){
        panelRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/addPanelToUser/{panelId}/{userId}")
    ResponseEntity addPanelToUser(@PathVariable Long panelId , @PathVariable Long userId){

        ResponseEntity<String> forEntity = restTemplate.getForEntity(usersExchange + "/{id}", String.class, userId);
        if(forEntity.getStatusCode()==HttpStatus.OK) {
        Optional<Panel> panelById = panelRepository.findById(panelId);
        Optional<UserOwner> userOwner = userOwnerRepository.findByIdUser(userId);
        panelById.ifPresent(existingPanel -> {
            if(!userOwner.isPresent()){
                UserOwner userOwner1 = new UserOwner();
                userOwner1.setIdUser(userId);
                userOwner1.setOwnedPanels(Collections.singletonList(existingPanel));
                existingPanel.setUserOwner(userOwner1);
                userOwner1.setOwningPanel(LocalDate.now());
                userOwnerRepository.save(userOwner1);
                panelRepository.save(existingPanel);

            } else {
                UserOwner userOwner1 = userOwner.get();
                List<Panel> ownedPanels = userOwner1.getOwnedPanels();
                ownedPanels.add(existingPanel);
                userOwner1.setOwnedPanels(ownedPanels);
                existingPanel.setUserOwner(userOwner1);
                userOwnerRepository.save(userOwner1);
                panelRepository.save(existingPanel);
            }

        });

        return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.noContent().build();
        
    }

}
