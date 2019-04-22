package com.imaginepartners.microservice.panels.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "faces")
public class Face {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private float lenght;

    @NotNull
    @Column
    private float width;

    @NotNull
    @Column
    private float area;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "panel_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Panel panel;


    public Face(float lenght, float width) {
        this.lenght = lenght;
        this.width = width;
        this.area = lenght*width;
    }

    public Long getId() {
        return id;
    }

    public float getLenght() {
        return lenght;
    }

    public void setLenght(float lenght) {
        this.lenght = lenght;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }
}
