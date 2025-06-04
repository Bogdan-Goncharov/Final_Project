package com.project.security;


import com.project.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // Импорт HttpMethod
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/authenticate", "/users/register").permitAll() // Разрешаем регистрацию и аутентификацию всем
                        // Ограничения для AchievementController
                        .requestMatchers(HttpMethod.GET, "/achievements").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/achievements").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/achievements/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/achievements/**").hasRole("ADMIN")
                        // Ограничения для PlayerStatsController
                        .requestMatchers(HttpMethod.GET, "/player/stats/{userId}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/player/stats/{userId}").hasRole("ADMIN")
                        // Ограничения для UserController
                        .requestMatchers(HttpMethod.GET, "/user/{username}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}