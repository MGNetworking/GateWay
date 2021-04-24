package fr.sid.facture.feign;

import fr.sid.facture.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientRestClient {

    @GetMapping(path = "/clients/{id}")
    Client getClientById(@PathVariable(name = "id")Long id);

    @GetMapping(path = "/clients/{nom}")
    Client getClientByName(@PathVariable(name = "nom") String nom);
}
