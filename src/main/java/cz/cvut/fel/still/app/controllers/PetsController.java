package cz.cvut.fel.still.app.controllers;

import io.swagger.client.ApiClient;
import io.swagger.client.api.PetsApi;
import io.swagger.client.model.Pets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/local-pets")
public class PetsController {
    @Autowired
    private ApiClient client;

    @GetMapping
    public String[] get() {
        PetsApi petsApi = new PetsApi(client);
        Pets top10pets = petsApi.listPets(10);
        return top10pets.stream().map(pet -> pet.getName()).distinct().sorted().toArray(String[]::new);
    }
}
