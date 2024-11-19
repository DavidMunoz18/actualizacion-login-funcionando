package edu.proyectoApi.repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.proyectoApi.dao.Club;

/**
 * Repositorio para la entidad Club que proporciona métodos para interactuar
 * con la base de datos.
 */
@Repository
public interface ClubRepositorios extends JpaRepository<Club, Long> {

    /**
     * Elimina un club por su nombre.
     *
     * @param nombreClub El nombre del club a eliminar.
     */
    void deleteByNombreClub(String nombreClub);

    /**
     * Encuentra un club por su email y contraseña.
     *
     * @param emailClub El email del club.
     * @param passwdClub La contraseña del club.
     * @return Un Optional que contiene el club encontrado, si existe.
     */
    Optional<Club> findByEmailClubAndPasswdClub(String emailClub, String passwdClub);
    
    Optional<Club> findByIdClub(Long idClub); // Encuentra un club por su ID

    
  

}