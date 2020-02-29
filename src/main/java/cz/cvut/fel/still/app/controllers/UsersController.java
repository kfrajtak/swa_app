package cz.cvut.fel.still.app.controllers;

import cz.cvut.fel.still.app.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final List<User> userList = new java.util.ArrayList<>();

    public UsersController() {
    }

    @GetMapping()
    public List<User> get() {
        return userList;
    }

    @GetMapping(path = "/{id}")
    public User get(@PathVariable int id) {
        User user = userList.stream()
                .filter(u -> u.getId() == id)
                .findAny()
                .orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return user;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        User userToDelete = get(id);
        userList.remove(userToDelete);
        return new ResponseEntity(HttpStatus.OK);
    }

    // @PutMapping(path = "/{key}") put replaces the at the URI location, not aplicable here

    // @PostMapping - creates new user
}
