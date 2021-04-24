package fr.sid.facture.web.controller;

import fr.sid.facture.entities.Facture;
import fr.sid.facture.entities.ItemProduit;
import fr.sid.facture.feign.ClientRestClient;
import fr.sid.facture.feign.ItemProduitRestClient;
import fr.sid.facture.model.Client;
import fr.sid.facture.model.Commande;
import fr.sid.facture.model.Produit;
import fr.sid.facture.repository.*;
import fr.sid.facture.web.exceptions.FacturationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
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

    @GetMapping(path = "/fullFacture/{id}")
    public ResponseEntity<Facture>  getClient(@PathVariable(name = "id") Long id) {

        Facture facture = factureRepository.findById(id).get();

        if (facture != null){
            return new ResponseEntity<Facture>(facture, HttpStatus.CREATED);

        }else{
            return new ResponseEntity<Facture>(facture, HttpStatus.NOT_FOUND);
        }


    }

    /**
     * Permet la création d'une facture avec tout les élements item liéer a la facture
     *
     * @param facture
     * @return
     */
    @PostMapping(path = "/addfacture" ,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Facture> addFature(@RequestBody Commande commande) {

        Facture facture = new Facture();

        Client client = this.clientRestClient.getClientByName(commande.getNomClient());

        log.info("Info client : " + client);

        Collection<ItemProduit> itemProduits = new ArrayList<>();

        if (client == null) {
            throw new FacturationException("La facture n'a pas pu être enregistrée pour cause , " +
                    "le client n'a pas était trouver en base de donées .");
        } else {

            // pour chaque produit
            commande.getProduitCollection().forEach((produit, integer) -> {

                Produit prod = this.itemProduitRestClient.getProduitByName(produit);

                ItemProduit itemProduit = new ItemProduit();

                itemProduit.setId_itemProduit(prod.getId_produit());
                itemProduit.setQuantity(integer.longValue());
                itemProduit.setPrix(integer.longValue() * prod.getPrix());
                itemProduit.setProduit(prod);

                itemProduits.add(itemProduit);

            });


            facture.setClient(client.getId_client());
            facture.setDate(new Date());
            facture.setListItemProduit(itemProduits);
            log.info("Info : " + facture);
            this.factureRepository.save(facture);

        }

        return new ResponseEntity<Facture>(facture, HttpStatus.CREATED);
    }

}
