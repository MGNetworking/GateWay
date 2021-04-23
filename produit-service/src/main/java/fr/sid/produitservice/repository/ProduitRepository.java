package fr.sid.produitservice.repository;

import fr.sid.produitservice.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
