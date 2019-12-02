package com.cashmanager.model;
import com.cashmanager.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.beans.JavaBean;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="panier")
@ApiModel(description = "All details about the Panier")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Panier implements Serializable {
    public Panier() { this.total = 0.0;}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="The Panier id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Client.class)
    @ApiModelProperty(notes="The client owning the Panier")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    @Value("0")
    @ApiModelProperty(notes="The total quantity of produits in a panier")
    private int quantite;

    @Value("0")
    @ApiModelProperty(notes="Total prix of the panier")
    private Double total;

    // GET and SET
    public Long getIdPanier(){ return this.id; }
    public void setIdPanier(Long idPanier){ this.id = idPanier; }

    public int getQuantite() { return this.quantite;}
    public void setQuantite(int quantite){ this.quantite = quantite;}

    public Double getTotal(){return this.total;}
    public void setTotal(Double total){ this.total = total;}

    public Client getClient(){
        return this.client;
    }
    public void setClient(Client client){
        this.client = client;
    }


}
