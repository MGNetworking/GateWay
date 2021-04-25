package fr.sid.facture.repository;

import fr.sid.facture.entities.ItemProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@RepositoryRestResource
public interface ItemProduitRepository extends JpaRepository<ItemProduit, Long> {

    public Collection<ItemProduit> findByFacture(Long id);
}

