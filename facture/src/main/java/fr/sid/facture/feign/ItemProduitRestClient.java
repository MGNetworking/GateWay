package fr.sid.facture.feign;

import fr.sid.facture.model.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUIT-SERVICE")
public interface ItemProduitRestClient {

    @GetMapping(path = "/produits")
    PagedModel<Produit> pageProduits(@RequestParam(value = "page")int page ,
                                     @RequestParam(value = "size")int size);

    @GetMapping(path = "/produits/{id}")
    Produit getProduitById(@PathVariable(value = "id") Long id);
}
