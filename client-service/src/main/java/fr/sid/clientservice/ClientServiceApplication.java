package fr.sid.clientservice;

import fr.sid.clientservice.entities.Client;
import fr.sid.clientservice.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ClientServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClientServiceApplication.class, args);
    }

}
