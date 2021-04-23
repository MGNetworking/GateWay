package fr.sid.facture.model;

import lombok.Data;

@Data
public class Produit {

    private Long id_produit;
    private String nom;
    private double prix;
    private Integer quantite;
}
