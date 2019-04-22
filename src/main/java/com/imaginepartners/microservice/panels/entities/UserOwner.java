package com.imaginepartners.microservice.panels.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user_owner")
public class UserOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate owningPanel;

    @Column(unique = true)
    private Long idUser;

    @OneToMany(
            mappedBy = "userOwner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Panel> ownedPanels = new ArrayList<>();
}
