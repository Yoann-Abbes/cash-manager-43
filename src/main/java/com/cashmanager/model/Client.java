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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated client ID")
    private Long idClient;

    @Column(name = "nom")
    @ApiModelProperty(notes = "The client last name")
    private String nom;

    @Column(name = "prenom")
    @ApiModelProperty(notes = "The client first name")
    private String prenom;

    @Column(name = "id_client")
    public Long getId() {
        return this.idClient;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
