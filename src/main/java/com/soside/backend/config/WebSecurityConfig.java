package com.soside.backend.config;
import com.soside.backend.filters.JwtRequestFilter;
import com.soside.backend.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final UserService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    public WebSecurityConfig(@Lazy UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
                    configuration.setAllowedOrigins(java.util.List.of("http://localhost:4200")); // Add your Angular app's origin
                    configuration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    configuration.setAllowedHeaders(java.util.List.of("Authorization", "Content-Type"));
                    configuration.setAllowCredentials(true); // If you are using cookies with CORS
                    return configuration;
                }))
                .authorizeHttpRequests(auth -> auth
                        // Autorisation explicite pour /register en premier
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/register/").permitAll() // Pour gérer aussi la version avec un slash à la fin
                        .requestMatchers("/authenticate").permitAll()
                        .requestMatchers("/forgot-password").permitAll()
                        .requestMatchers("/reset-password").permitAll()
                        // Les autres règles d'autorisation
//                        .requestMatchers("/admin/users/**").permitAll()
                        .requestMatchers("/admin/users/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/health-records/**", "/api/care-histories/**").hasAuthority("MEDECIN")
                        .requestMatchers("/api/criminal-records/**", "/api/complaints/**", "/biometric-data/**").hasAnyAuthority("POLICE", "ADMIN")
                        .requestMatchers("/api/criminal-records/**", "/api/complaints/**", "/marriage-records/**", "/api/birth-records/**").permitAll()
                        .requestMatchers("/persons/**").hasAnyAuthority("MEDECIN", "POLICE", "JUSTICE", "ADMIN") // Adjust as needed
                        // Toute autre requête doit être authentifiée
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}