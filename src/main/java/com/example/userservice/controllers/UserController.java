package com.example.userservice.controllers;

import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public User saveUser(@Validated @RequestBody User user) {
        User user1 = userRepository.findByCognitoID(user.getCognitoID());
        if (user1 != null) {
            return user1;
        }
        return userRepository.save(user);
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

    @PostMapping("/update")
    public User updateUser(@Validated @RequestBody User user) {
        User user1 = userRepository.findByCognitoID(user.getCognitoID());
        if (user1 != null) {
            return userRepository.save(user);
        }
        return null;
    }

    @GetMapping("/health")
    public ResponseEntity<String> handleNotifications() {
        // parse here the values
        return new ResponseEntity<>("result successful result",
                HttpStatus.OK);
    }
}
