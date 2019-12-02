package com.cashmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.beans.JavaBean;
import java.io.Serializable;

@Entity
@Table(name = "Client")
@JavaBean
@ApiModel(description = "All details about the Client. ")

public class Client implements Serializable {
    public Client () {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated client ID")
    private Long id;

    @NotBlank
    @Column
    @ApiModelProperty(notes = "The client last name")
    private String nom;

    @NotBlank
    @Column
    @ApiModelProperty(notes = "The client's email adress")
    private String email;

    @Column
    @NotBlank
    private String mdp;

    @NotBlank
    @Column
    @ApiModelProperty(notes = "The client's login")
    private String login;

    @NotBlank
    @Column
    @ApiModelProperty(notes = "The client first name")
    private String prenom;


    public Long getId() { return this.id; }

    public String getLogin(){ return this.login; }
    public void setLogin(String login){this.login = login;}

    public String getEmail() {return this.email;}
    public void setEmail(String email){ this.email = email;}

    public String getMdp(){ return this.mdp;}
    public void setMdp(String mdp) { this.mdp = mdp;}

    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}