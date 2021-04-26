package fr.sid.facture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Client {

    private Long id_client;
    private String nom;
    private String prenom;
    private String email;
    @JsonIgnore
    private String password;
}
