package fr.sid.facture.model;

import lombok.Data;

@Data
public class Client {

    private Long id_client;
    private String nom;
    private String prenom;
    private String email;
    private String password;
}
