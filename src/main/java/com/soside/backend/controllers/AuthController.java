package com.soside.backend.controllers;

import com.soside.backend.enums.Role;
import com.soside.backend.models.User;
import com.soside.backend.payload.AuthenticationRequest;
import com.soside.backend.payload.AuthenticationResponse;
import com.soside.backend.payload.ForgotPasswordRequest;
import com.soside.backend.payload.RegistrationRequest;
import com.soside.backend.payload.ResetPasswordRequest;
import com.soside.backend.services.user.UserService;
import com.soside.backend.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        System.out.println("Tentative d'enregistrement de l'utilisateur : " + registrationRequest.getUsername());
        try {
            if (userService.loadUserByUsername(registrationRequest.getUsername()) != null) {
                System.out.println("Nom d'utilisateur déjà existant : " + registrationRequest.getUsername());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (UsernameNotFoundException e) {
            // Expected exception when user is not found, continue with registration
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification de l'utilisateur : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User newUser = User.builder()
                .email(registrationRequest.getEmail())
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .roles(Collections.singletonList(Role.CITIZEN))
                .build();

        try {
            System.out.println("Appel de userService.saveUser pour : " + newUser.getUsername());
            userService.saveUser(newUser);
            System.out.println("Utilisateur enregistré avec succès : " + newUser.getUsername());

            final UserDetails userDetails = userService.loadUserByUsername(newUser.getUsername());
            System.out.println("Détails de l'utilisateur chargés après l'enregistrement : " + userDetails.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            System.out.println("Token JWT généré pour : " + userDetails.getUsername());

            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationResponse(jwt));

        } catch (Exception e) {
            System.err.println("Erreur lors de l'enregistrement de l'utilisateur : " + e.getMessage());
            e.printStackTrace(); // Imprimez la trace de la pile pour plus de détails
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/forgot-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        try {
            userService.forgotPassword(forgotPasswordRequest.getUsername());
            return ResponseEntity.ok("Password reset link sent to your email address.");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping(value = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        try {
            String message = userService.resetPassword(resetPasswordRequest.getToken(), resetPasswordRequest.getNewPassword());
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}