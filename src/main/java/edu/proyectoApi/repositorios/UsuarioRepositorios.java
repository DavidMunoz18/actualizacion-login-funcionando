package edu.proyectoApi.repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.proyectoApi.dao.Usuario;

/**
 * Repositorio para la entidad Usuario que proporciona métodos para interactuar
 * con la base de datos.
 */
@Repository
public interface UsuarioRepositorios extends JpaRepository<Usuario, Long> {

    /**
     * Elimina un usuario por su nickname.
     *
     * @param nicknameUsuario El nickname del usuario a eliminar.
     */
    void deleteByNicknameUsuario(String nicknameUsuario);

    /**
     * Encuentra un usuario por su email y contraseña.
     *
     * @param emailUsuario El email del usuario.
     * @param passwdUsuario La contraseña del usuario.
     * @return Un Optional que contiene el usuario encontrado, si existe.
     */
    Optional<Usuario> findByEmailUsuario(String email);
    Optional<Usuario> findByNicknameUsuario(String nickname);
    Optional<Usuario> findByEmailUsuarioAndPasswdUsuario(String email, String password);


    /**
     * Encuentra un usuario por su ID.
     *
     * @param idUsuario El ID del usuario.
     * @return Un Optional que contiene el usuario encontrado, si existe.
     */
    Optional<Usuario> findById(Long idUsuario);
}
