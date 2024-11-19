package edu.proyectoApi.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.proyectoApi.dao.Usuario;
import edu.proyectoApi.dto.UsuarioDto;
import edu.proyectoApi.repositorios.UsuarioRepositorios;

@Service
public class GestionUsuarioImplementacion implements GestionUsuarioInterfaz {

    private final UsuarioRepositorios usuarioRepositorios;
    private final PasswordEncoder passwordEncoder; // Inyección de PasswordEncoder
    @Autowired
    private JwtUtil jwtUtil;  // Inyectamos el JwtUtil para generar el token

    @Autowired
    public GestionUsuarioImplementacion(UsuarioRepositorios usuarioRepositorio, PasswordEncoder passwordEncoder) {
        this.usuarioRepositorios = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void altaUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setNicknameUsuario(usuarioDto.getNicknameUsuario());
        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        usuario.setDniUsuario(usuarioDto.getDniUsuario());
        usuario.setTelefonoUsuario(usuarioDto.getTelefonoUsuario());
        usuario.setFotoUsuario(usuarioDto.getFotoUsuario());
        usuario.setEmailUsuario(usuarioDto.getEmailUsuario());
        usuario.setPasswdUsuario(passwordEncoder.encode(usuarioDto.getPasswdUsuario()));  // Cifrar la contraseña
        usuario.setRol(usuarioDto.getRol());

        usuarioRepositorios.save(usuario);
    }

    @Override
    @Transactional
    public boolean eliminarUsuario(String nicknameUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepositorios.findByNicknameUsuario(nicknameUsuario);

        if (usuarioOpt.isPresent()) {
            usuarioRepositorios.delete(usuarioOpt.get());
            return true;  // Retorna true si el usuario fue eliminado exitosamente
        }

        return false;  // Si el usuario no existe, retornar false
    }

    @Override
    @Transactional
    public boolean modificarUsuario(long idUsuario, String nuevoNombre, String nuevoTelefono, byte[] nuevaFoto) {
        Optional<Usuario> usuarioOpt = usuarioRepositorios.findById(idUsuario);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                usuario.setNombreUsuario(nuevoNombre);
            }
            if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
                usuario.setTelefonoUsuario(nuevoTelefono);
            }
            if (nuevaFoto != null && nuevaFoto.length > 0) {
                usuario.setFotoUsuario(nuevaFoto);
            }

            usuarioRepositorios.save(usuario);
            return true;
        }

        return false;
    }

    public Optional<String> loginUsuario(String email, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepositorios.findByEmailUsuario(email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(password, usuario.getPasswdUsuario())) {
                return Optional.of(jwtUtil.generarToken(email)); // Devuelve el token JWT
            }
        }
        return Optional.empty();
    }
}
