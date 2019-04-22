package com.imaginepartners.microservice.panels.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "panels")
public class Panel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column
    @NotNull
    private int  numberFace;

    @Column
    @NotNull
    private Category category;

    @Column
    @NotNull
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private UserOwner userOwner;

}
