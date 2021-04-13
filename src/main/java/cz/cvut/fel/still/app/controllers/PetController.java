package cz.cvut.fel.still.app.controllers;

import cz.cvut.fel.still.api.controllers.PetApi;
import cz.cvut.fel.still.api.model.dto.ModelApiResponse;
import cz.cvut.fel.still.api.model.dto.Pet;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PetController implements PetApi {
    @Override
    public ResponseEntity<Pet> addPet(@Valid Pet pet) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePet(Long petId, String apiKey) {
        return null;
    }

    @Override
    public ResponseEntity<List<Pet>> findPetsByStatus(@Valid String status) {
        return null;
    }

    @Override
    public ResponseEntity<List<Pet>> findPetsByTags(@Valid List<String> tags) {
        return null;
    }

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<Pet> updatePet(@Valid Pet pet) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updatePetWithForm(Long petId, @Valid String name, @Valid String status) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> uploadFile(Long petId, @Valid String additionalMetadata, @Valid Resource body) {
        return null;
    }
}
