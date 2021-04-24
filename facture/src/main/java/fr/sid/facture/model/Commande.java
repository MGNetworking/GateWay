package fr.sid.facture.model;

import fr.sid.facture.entities.ItemProduit;
import lombok.Data;

import java.util.Collection;
import java.util.Map;

@Data
public class Commande {

    private String nomClient;
    private Map<String, Integer> produitCollection;
}
