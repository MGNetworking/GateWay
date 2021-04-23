package fr.sid.facture.repository;

import fr.sid.facture.entities.ItemProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;


@RepositoryRestResource
public interface ItemProduitRepository extends JpaRepository<ItemProduit, Long> {

    public Collection<ItemProduit> findByItemProduit(Long id);
}

