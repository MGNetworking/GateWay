package fr.sid.facture.feign;

import fr.sid.facture.model.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUIT-SERVICE")
public interface ProduitRestClient {

    @GetMapping(path = "/produits")
    PagedModel<Produit> pageProduits(@RequestParam(value = "page")int page ,
                                     @RequestParam(value = "size")int size);

    @GetMapping(path = "/produits/{id}")
    Produit getProduitById(@PathVariable(value = "id") Long id);

    @GetMapping(path = "/produits/{nom}")
    Produit getProduitByName(@PathVariable(value = "nom") String nom);

    @PostMapping(path = "/save/{produit}")
    Produit saveProduit(@PathVariable(value = "produit") Produit produit);
}
