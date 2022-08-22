package com.example.userservice.controllers;

import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @GetMapping
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping
    public User saveUser(@Validated @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/editor")
    public ObjectNode checkEditor(@RequestParam(name = "id") String userID) throws JSONException {

        User user = userRepository.findByCognitoID(userID);
        boolean editor = false;

        if ( user.getName() == null
                || user.getDescription() == null || user.getCountryOfOrigin() == null) {
            editor = false;
        }
        else {
            editor = true;
        }
        ObjectNode objectNode = jacksonObjectMapper.createObjectNode();
        objectNode.put("id", userID);
        objectNode.put("editor", editor?"True":"False");
        return objectNode;
    }



}
