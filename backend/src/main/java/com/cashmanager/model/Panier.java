package com.cashmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cascade;
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
@JsonIgnoreProperties(allowGetters = true)

public class Panier implements Serializable {
    public Panier() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes="The Panier id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "panier")
    @ApiModelProperty(notes="The client owning the Panier")
    private Client client;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "panier",targetEntity = Produit.class)
    private Set<Produit> produit = new HashSet<>();

    @Value("0")
    private Long quantite;

    @Value("0")
    private Double total;

    // GET and SET
    public Long getIdPanier(){ return this.id; }
    public void setIdPanier(Long idPanier){ this.id = idPanier; }

    public Long getQuantite() { return this.quantite;}
    public void setQuantite(Long quantite){ this.quantite = quantite;}

    public Double getTotal(){return this.total;}
    public void setTotal(Double total){ this.total = total;}

    public Set<Produit> getProduit() { return produit;}
    public void setProduit(Set<Produit> produit) { this.produit=produit;}
    public void removeProduit(Produit produit) {
        this.produit.remove(produit);
    }

    public Client getClient(){
        return this.client;
    }
    public void setClient(Client client){
        this.client = client;
    }


}
