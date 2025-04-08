package com.soside.backend.controllers;
import com.soside.backend.enums.Role;
import com.soside.backend.models.User;
import com.soside.backend.payload.RegistrationRequest;
import com.soside.backend.payload.UserUpdateRequest;
import com.soside.backend.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin/users") // Préfixe pour les endpoints d'administration des utilisateurs
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = this.userService.gerAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> addUser(@RequestBody RegistrationRequest registrationRequest,
                                        @RequestParam(required = false) String roles) {
        List<Role> roleList = null;
        if (roles != null && !roles.isEmpty()) {
            roleList = Arrays.stream(roles.split(","))
                    .map(String::trim)
                    .map(String::toUpperCase)
                    .map(Role::valueOf)
                    .collect(Collectors.toList());
        }
        try {
            User newUser = userService.addUser(registrationRequest, roleList);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Vous pouvez retourner un message d'erreur dans le corps si vous le souhaitez
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            // Ou si vous voulez inclure un message :
            // return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Le corps peut être null
            // ou
            // return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create") // Alias pour addUser
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody RegistrationRequest registrationRequest,
                                           @RequestParam(required = false) String roles) {
        return addUser(registrationRequest, roles);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            User updatedUser = userService.editUser(id, userUpdateRequest);
            return ResponseEntity.ok(updatedUser);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (org.springframework.security.access.AccessDeniedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (org.springframework.security.access.AccessDeniedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/change-role/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> changeRole(@PathVariable Long id, @RequestParam(required = false) List<Role> roles) {

        System.out.println(roles);

        try {
            userService.changeRole(id, roles);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (org.springframework.security.access.AccessDeniedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
