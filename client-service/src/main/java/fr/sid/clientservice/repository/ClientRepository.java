package fr.sid.clientservice.repository;

import fr.sid.clientservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    @RestResource(path = "byNom")
    public Client findByNom(@Param("nom") String nom);

}
