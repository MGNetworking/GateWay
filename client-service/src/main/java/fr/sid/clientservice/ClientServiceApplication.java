package fr.sid.clientservice;

import fr.sid.clientservice.entities.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration
public class ClientServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClientServiceApplication.class, args);

    }

    /**
     * Permet d'exposer l'id  de l'objet client
     * @return
     */
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer()
    {
        return RepositoryRestConfigurer.withConfig(config -> {
            config.exposeIdsFor(Client.class);
        });
    }

}
