package fr.sid.facture.web.controller;

import fr.sid.facture.entities.Facture;
import fr.sid.facture.feign.ClientRestClient;
import fr.sid.facture.feign.ProduitRestClient;
import fr.sid.facture.model.Client;
import fr.sid.facture.model.Produit;
import fr.sid.facture.repository.*;
import fr.sid.facture.web.exceptions.FacturationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class FaturationRestController {

    private FactureRepository factureRepository;
    private ItemProduitRepository itemProduitRepository;

    private ClientRestClient clientRestClient;
    private ProduitRestClient produitRestClient;

    public FaturationRestController(FactureRepository factureRepository,
                                    ItemProduitRepository itemProduitRepository,
                                    ClientRestClient clientRestClient,
                                    ProduitRestClient produitRestClient) {
        this.factureRepository = factureRepository;
        this.itemProduitRepository = itemProduitRepository;
        this.clientRestClient = clientRestClient;
        this.produitRestClient = produitRestClient;
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
            Produit produit = produitRestClient.getProduitById(itemProduit.getId_produit());
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
    public ResponseEntity<Facture> addFature(@RequestBody Facture facture) {

        ResponseEntity<Facture> factureResponseEntity;

        // parti client
        Client client = this.clientRestClient.getClientByNom(facture.getClient().getNom());


        if (client.getId_client() == null) {

            factureResponseEntity = new ResponseEntity<Facture>(facture, HttpStatus.NON_AUTHORITATIVE_INFORMATION);

            throw new FacturationException("La facture n'a pas pu être enregistrée pour cause , " +
                    "les reference du client n'a pas était trouver en base de donées .");

        } else {


            // parti facture
            facture.setDate(new Date());
            facture.setClient(client);
            facture.setId_client(client.getId_client());

            try{

                // todo creer un service pour gérer tout les actions
            // todo fair un rollback
            // mise a jour des produits
            if ( this.factureRepository.save(facture) != null){

                facture.getItemProduits().forEach(itemProduit -> {

                    Produit prod = this.produitRestClient.getProduitById(itemProduit.getProduit().getId_produit());

                    prod.setQuantite( prod.getQuantite() - itemProduit.getQuantity() );

                    this.produitRestClient.saveProduit(prod);
                });
            }

            // TODO save item

            }catch (RuntimeException run){
                log.error("Une Erreur est survenu,\n" +
                        "la facture n'a pas put étre enregistrer : " +
                        run.getMessage() + "\n" +
                        run.getCause());
            }


            factureResponseEntity = new ResponseEntity<Facture>(facture, HttpStatus.CREATED);
        }


        return factureResponseEntity;

    }

}
