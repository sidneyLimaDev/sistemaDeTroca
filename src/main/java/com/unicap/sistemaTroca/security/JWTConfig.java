package com.unicap.sistemaTroca.security;


import com.unicap.sistemaTroca.security.exceptionhandler.UnauthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class JWTConfig {

    @Autowired
    private JWTAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(csrf -> Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/autenticacao/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuario/pessoa").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuario/ong").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuario").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categoria").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categoria/{nome}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuario/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuario/nome/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/anuncio/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/anuncio/nome/{nome}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/anuncio").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/endereco/{cep}").permitAll()
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((exception) -> exception.authenticationEntryPoint(new UnauthorizedHandler()))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
