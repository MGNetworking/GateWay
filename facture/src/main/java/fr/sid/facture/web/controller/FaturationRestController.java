package fr.sid.facture.web.controller;

import fr.sid.facture.entities.Facture;
import fr.sid.facture.entities.ItemProduit;
import fr.sid.facture.feign.ClientRestClient;
import fr.sid.facture.feign.ItemProduitRestClient;
import fr.sid.facture.model.Client;
import fr.sid.facture.model.Produit;
import fr.sid.facture.repository.*;
import fr.sid.facture.web.exceptions.FacturationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
public class FaturationRestController {

    private FactureRepository factureRepository;
    private ItemProduitRepository itemProduitRepository;

    private ClientRestClient clientRestClient;
    private ItemProduitRestClient itemProduitRestClient;

    public FaturationRestController(FactureRepository factureRepository,
                                    ItemProduitRepository itemProduitRepository,
                                    ClientRestClient clientRestClient,
                                    ItemProduitRestClient itemProduitRestClient) {
        this.factureRepository = factureRepository;
        this.itemProduitRepository = itemProduitRepository;
        this.clientRestClient = clientRestClient;
        this.itemProduitRestClient = itemProduitRestClient;
    }

    /**
     * Affiche tout les données d'une facture concernant un client.
     * Le paramétre id correspond a l'identifiant de la facture.
     *
     * @param id to type Interger
     * @return Object to type Facture
     */
    @GetMapping(path = "/fullFacture/{id}")
    public ResponseEntity<Facture> getClient(@PathVariable(name = "id") Long id) {

        Facture facture = factureRepository.findById(id).get();

        // recherche du client dans le Micro service Client
        Client client = clientRestClient.getClientById(facture.getId_client());
        facture.setClient(client);

        facture.getItemProduits().forEach(itemProduit -> {
            // recherche du produit dasn micro service produit
            Produit produit = itemProduitRestClient.getProduitById(itemProduit.getId_produit());
            itemProduit.setProduit(produit);
        });

        if (facture != null) {
            return new ResponseEntity<Facture>(facture, HttpStatus.OK);

        } else {
            return new ResponseEntity<Facture>(facture, HttpStatus.NOT_FOUND);
        }


    }

    /**
     * Permet la création d'une facture avec un seul Item produit
     *
     * @param commande
     * @return
     */
    @PostMapping(path = "/addfacture",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Facture> addFature(@RequestBody ItemProduit itemProduit,
                                             Client client) {

        ResponseEntity<Facture> factureResponseEntity;

        // recherche info du client
        client = this.clientRestClient.getClientByName(client.getNom());

        log.info("Info client : " + client);

        Facture facture = new Facture();

        if (client.getId_client() == null) {

            factureResponseEntity = new ResponseEntity<Facture>(facture, HttpStatus.NON_AUTHORITATIVE_INFORMATION);

            throw new FacturationException("La facture n'a pas pu être enregistrée pour cause , " +
                    "les reference du client n'a pas était trouver en base de donées .");

        } else {


            // Création de la facture
            facture.setDate(new Date());
            facture.getItemProduits().add(itemProduit);
            facture.setClient(client);
            log.info("Info facture : " + facture + "\n" +
                    "Info produit : " + itemProduit);

            this.itemProduitRepository.save(itemProduit);
            this.factureRepository.save(facture);

            // TODO fair déduction des produits acheter dans la table Produit.

            factureResponseEntity = new ResponseEntity<Facture>(facture, HttpStatus.CREATED);
        }


        return factureResponseEntity;

    }

}
