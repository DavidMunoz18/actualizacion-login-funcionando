package edu.proyectoApi.controladores;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.proyectoApi.dto.LoginClub;
import edu.proyectoApi.dto.UsuarioDto;
import edu.proyectoApi.servicios.GestionUsuarioInterfaz;
import edu.proyectoApi.servicios.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioControladores {

    private final GestionUsuarioInterfaz usuarioInterfaz;
    private final AuthenticationManager authenticationManager; // Para autenticar a los usuarios

    @Autowired
    private JwtUtil jwtUtil; // Inyectamos el JwtUtil para generar el token JWT

    @Autowired
    public UsuarioControladores(GestionUsuarioInterfaz usuarioInterfaz, AuthenticationManager authenticationManager) {
        this.usuarioInterfaz = usuarioInterfaz;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        try {
            usuarioInterfaz.altaUsuario(usuarioDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{nicknameUsuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String nicknameUsuario) {
        try {
            usuarioInterfaz.eliminarUsuario(nicknameUsuario);
            return ResponseEntity.ok("Usuario eliminado con éxito.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado: " + nicknameUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<String> modificarUsuario(
            @PathVariable long idUsuario,
            @RequestParam(required = false) String nuevoNombre,
            @RequestParam(required = false) String nuevoTelefono,
            @RequestParam(required = false) MultipartFile nuevaFoto) {

        try {
            byte[] fotoBytes = null;

            if (nuevaFoto != null && !nuevaFoto.isEmpty()) {
                fotoBytes = nuevaFoto.getBytes(); // Convertir la foto a bytes
            }

            boolean exito = usuarioInterfaz.modificarUsuario(idUsuario, nuevoNombre, nuevoTelefono, fotoBytes);

            if (exito) {
                return ResponseEntity.ok("Usuario modificado con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la foto.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginClub loginClub) {
        System.out.println("Email: " + loginClub.getEmail() + ", Password: " + loginClub.getPassword()); // Depuración

        try {
            // Autenticar al usuario usando el AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginClub.getEmail(), loginClub.getPassword())
            );

            // Establecer el contexto de seguridad (opcional, dependiendo de cómo manejes la seguridad)
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generar el token JWT
            String token = jwtUtil.generarToken(loginClub.getEmail());
            return ResponseEntity.ok("Token JWT: " + token);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas. Por favor, verifica tu email y contraseña.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor: " + e.getMessage());
        }
    }

}
