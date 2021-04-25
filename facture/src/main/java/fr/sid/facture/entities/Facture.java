package fr.sid.facture.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.sid.facture.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "facture")
public class Facture {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    @OneToMany(mappedBy = "facture")
    private Collection<ItemProduit> itemProduits;

    private Long id_client;

    @Transient
    private Client client;
}
