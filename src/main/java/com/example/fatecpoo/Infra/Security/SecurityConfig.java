package com.example.fatecpoo.Infra.Security;

import com.example.fatecpoo.Infra.Security.Impl.CustomUserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.AccessDeniedException;

@Configuration
@EnableWebSecurity
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SecurityConfig {



    public static final String SECURITY = "bearerAuth";

    private CustomUserDetailsServiceImpl customUserDetailsService;

    private SecurityFilter securityFilter;

    @Autowired
    public SecurityConfig(CustomUserDetailsServiceImpl customUserDetailsService, SecurityFilter securityFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{


        //System.out.println(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers("/v3/api-docs/**", "swagger-ui/**", "swagger-ui.html").permitAll()
                        .requestMatchers("/", "/index.html", "/**").permitAll()
                        .requestMatchers(HttpMethod.GET).hasAnyAuthority("USER","ADMIN")
                        .anyRequest().hasAuthority("ADMIN")//authenticated()
                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}
