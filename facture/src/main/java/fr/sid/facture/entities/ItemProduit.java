package fr.sid.facture.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.sid.facture.model.Client;
import fr.sid.facture.model.Produit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ItemProduit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_itemProduit;
    private double quantity;
    private double prix;
    private Long id_produit;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Facture facture;

    @Transient
    private Produit produit;




}
