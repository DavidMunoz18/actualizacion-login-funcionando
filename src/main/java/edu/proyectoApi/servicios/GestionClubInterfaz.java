package edu.proyectoApi.servicios;

import java.util.Optional;

import edu.proyectoApi.dao.Club;
import edu.proyectoApi.dto.ClubDto;

/**
 * Interfaz que define los servicios de gestión de clubes.
 */
public interface GestionClubInterfaz {

    /**
     * Registra un nuevo club en el sistema.
     * 
     * @param clubDto El objeto ClubDto que contiene los detalles del club a registrar.
     * @throws IOException Si ocurre un error durante la operación de registro.
     */
    void altaClub(ClubDto clubDto);

    /**
     * Elimina un club existente del sistema por su nombre.
     * 
     * @param nombreClub El nombre del club a eliminar.
     * @throws IOException Si ocurre un error durante la operación de eliminación.
     */
    void eliminarClub(String nombreClub);

    /**
     * Inicia sesión en el club utilizando las credenciales proporcionadas.
     * 
     * @param email La dirección de correo electrónico del club.
     * @param password La contraseña del club.
     * @return Un Optional que contiene el ClubDto si la autenticación es exitosa, o vacío si no lo es.
     */
    Optional<ClubDto> loginClub(String email, String password);
    
    /**
     * Modificar un campo específico del club.
     *
     * @param nombreClub nombre del club a modificar
     * @param campo campo a modificar (nombreclub, sedeclub, imagenclub, etc.)
     * @param nuevoValor el nuevo valor del campo
     * @return un Optional con el ClubDto actualizado
     */
    

    public boolean modificarClub(long idClub, String nuevoNombre, String nuevaSede, byte[] nuevaImagen);

}