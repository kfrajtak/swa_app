package cz.cvut.fel.still.app.controllers;

/*import io.swagger.client.ApiClient;
import io.swagger.client.api.PetsApi;
import io.swagger.client.model.Pets;*/

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/local-pets")
public class PetsController {
    /*@GetMapping
    public Pets get(){
        ApiClient client = new ApiClient();
        client.setBasePath("www.nekde.cz");
        PetsApi petsApi = new PetsApi(client);
        return petsApi.listPets(10);
    }*/
}
