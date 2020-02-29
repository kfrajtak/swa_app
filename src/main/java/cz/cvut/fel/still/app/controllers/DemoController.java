package cz.cvut.fel.still.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashMap;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private final HashMap<String, String> map = new HashMap<>();

    public DemoController() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
    }

    @GetMapping(path = "")
    public HashMap<String, String> get() {
        return map;
    }

    @GetMapping(path = "/{key}")
    public Object get(@PathVariable String key) {
        if (key != null && map.containsKey(key)) {
            HashMap<String, String> m = new HashMap<>();
            m.put("key", key);
            m.put("value", map.get(key));
            m.put("timestamp", java.util.Date.from(Instant.now()).toString());

            return map;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{key}")
    public ResponseEntity delete(@PathVariable String key) {
        if (key != null && map.containsKey(key)) {
            map.remove(key);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/{key}")
    public String put(@PathVariable String key) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
