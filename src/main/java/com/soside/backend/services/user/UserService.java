package com.soside.backend.services.user;

import com.soside.backend.enums.Role;
import com.soside.backend.models.PasswordResetToken;
import com.soside.backend.models.User;
import com.soside.backend.payload.RegistrationRequest;
import com.soside.backend.payload.UserUpdateRequest;
import com.soside.backend.repositories.PasswordResetTokenRepository;
import com.soside.backend.repositories.UserRepository;
import com.soside.backend.services.email.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;

    @Value("${password.reset.token.expiry.minutes:30}")
    private int passwordResetTokenExpiryMinutes;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordResetTokenRepository passwordResetTokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Nouvelle méthode pour ajouter un utilisateur (similaire à register mais potentiellement avec plus d'options)
    public User addUser(RegistrationRequest registrationRequest, List<Role> roles) {
        if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        User newUser = User.builder()
                .email(registrationRequest.getEmail())
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .roles(roles != null ? roles : Collections.singletonList(Role.CITIZEN)) // Permettre de spécifier les rôles
                .build();
        return userRepository.save(newUser);
    }

    // Alias pour addUser si vous préférez cette nomenclature
    public User createUser(RegistrationRequest registrationRequest, List<Role> roles) {
        return addUser(registrationRequest, roles);
    }

    public User editUser(Long userId, UserUpdateRequest userUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        User userToEdit = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

        // Vérifiez si l'utilisateur connecté a le droit de modifier cet utilisateur (par exemple, ADMIN peut tout modifier)
        // Vous pourriez ajouter une logique plus fine ici
        if (!currentUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("You do not have permission to edit this user.");
        }

        if (userUpdateRequest.getEmail() != null) {
            userToEdit.setEmail(userUpdateRequest.getEmail());
        }
        if (userUpdateRequest.getPassword() != null) {
            userToEdit.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        }
        if (userUpdateRequest.getRoles() != null && !userUpdateRequest.getRoles().isEmpty()) {
            userToEdit.setRoles(userUpdateRequest.getRoles().stream().map(Role::valueOf).collect(Collectors.toList()));
        }

        return userRepository.save(userToEdit);
    }

    public void deleteUser(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();

        if (!currentUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("You do not have permission to delete this user.");
        }
        userRepository.deleteById(userId);
    }

    public void changeRole(Long userId, String newRole) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();

        if (!currentUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("You do not have permission to change user roles.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

        try {
            Role role = Role.valueOf(newRole.toUpperCase());
            user.setRoles(Collections.singletonList(role));
            userRepository.save(user);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + newRole);
        }
    }

    public void forgotPassword(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(passwordResetTokenExpiryMinutes);

        PasswordResetToken resetToken = new PasswordResetToken(token, user, expiryDate);
        passwordResetTokenRepository.save(resetToken);

        // Send email with the reset link
        String resetLink = "http:localhost:8000/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(user.getEmail(), resetLink); // Implement this method
    }

    public String resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid password reset token"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            passwordResetTokenRepository.delete(resetToken);
            throw new IllegalArgumentException("Password reset token has expired");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        passwordResetTokenRepository.delete(resetToken); // Optionally delete the token after use
        return "Password reset successfully";
    }
}