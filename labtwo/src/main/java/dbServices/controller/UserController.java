package dbServices.controller;

import dbServices.model.UserEntity;
import dbServices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

    @RestController
    @RequestMapping("/users")
    public class UserController {

        @Autowired
        private UserRepository userRepository;

        @PostMapping
        public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
            Optional<UserEntity> existingUser = userRepository.findByUsername(userEntity.getUsername());
            if (existingUser.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(userRepository.save(userEntity));
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
            return userRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping("/username/{username}")
        public ResponseEntity<UserEntity> getUserByName(@PathVariable String username) {
            return userRepository.findByUsername(username)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    }

