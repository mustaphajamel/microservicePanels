package com.imaginepartners.microservice.panels.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Agence {


    @Id
    private long id ;

    @Column(name = "name")
    private String name ;

    @Column(name = "tel")
    private int tel ;

    @Column(name = "mail")
    private String mail ;

    public Agence() {
    }

    public Agence(String name, int tel, String mail) {
        this.name = name;
        this.tel = tel;
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return tel;
    }

    public void setType(int type) {
        this.tel = type;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
