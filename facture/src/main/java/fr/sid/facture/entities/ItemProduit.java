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
@Table(name = "itemproduit")
public class ItemProduit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_produit;
    private Integer quantity;
    private double prix;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "id_facture")
    private Facture facture;

    @Transient
    private Produit produit;




}
