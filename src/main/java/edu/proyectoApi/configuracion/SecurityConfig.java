package edu.proyectoApi.configuracion;

import edu.proyectoApi.servicios.UsuarioServicio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UsuarioServicio usuarioServicio;

    // Constructor para inyectar el servicio de usuario
    public SecurityConfig(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()  // Método moderno para configurar autorización
            .requestMatchers("/api/usuarios/login", "/api/usuarios").permitAll()  // Permitir acceso sin autenticación
            .anyRequest().authenticated()  // El resto requiere autenticación
            .and()
            .formLogin(formLogin -> 
                formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/usuario", true)
                    .permitAll()
                    .successHandler((request, response, authentication) -> {
                        boolean isUser = authentication.getAuthorities().stream()
                            .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
                        if (isUser) {
                            response.sendRedirect("/usuario");
                        } else {
                            response.sendRedirect("/default");  // Página por defecto para otros roles
                        }
                    })
            )
            .logout(logout -> 
                logout.permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioServicio);
        authProvider.setPasswordEncoder(passwordEncoder()); // Configura el encoder para las contraseñas
        return authProvider;
    }
}
