package fr.sid.facture.web;

import fr.sid.facture.entities.Facture;
import fr.sid.facture.feign.ItemProduitRestClient;
import fr.sid.facture.model.Client;
import fr.sid.facture.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaturationRestController {

    private FactureRepository factureRepository;
    private ItemProduitRepository itemProduitRepository;
    private FaturationRestController faturationRestController;
    private ItemProduitRestClient itemProduitRestClient;

    public FaturationRestController(FactureRepository factureRepository,
                                    ItemProduitRepository itemProduitRepository,
                                    FaturationRestController faturationRestController,
                                    ItemProduitRestClient itemProduitRestClient) {
        this.factureRepository = factureRepository;
        this.itemProduitRepository = itemProduitRepository;
        this.faturationRestController = faturationRestController;
        this.itemProduitRestClient = itemProduitRestClient;
    }

    @GetMapping(path = "/fullFacture/{id}")
    public Facture getClient(@PathVariable(name = "id") Long id) {

        Facture facture = factureRepository.findById(id).get();

        return facture ;

    }

}
