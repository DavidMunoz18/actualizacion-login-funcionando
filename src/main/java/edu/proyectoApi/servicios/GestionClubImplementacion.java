package edu.proyectoApi.servicios;

import edu.proyectoApi.dao.Club;
import edu.proyectoApi.dto.ClubDto;
import edu.proyectoApi.repositorios.ClubRepositorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Implementación del servicio de club que maneja las operaciones relacionadas con los clubes.
 * <p>
 * Esta clase contiene la lógica para registrar, eliminar y autenticar un club,
 * y utiliza el repositorio de Club para interactuar con la base de datos.
 * </p>
 */
@Service
public class GestionClubImplementacion implements GestionClubInterfaz {

    /** Repositorio de Club que permite realizar operaciones de base de datos. */
    private final ClubRepositorios clubRepositorio;

    /**
     * Constructor que inyecta el repositorio de Club.
     *
     * @param clubRepositorio repositorio de Club que se utiliza para acceder a la base de datos
     */
    @Autowired
    public GestionClubImplementacion(ClubRepositorios clubRepositorio) {
        this.clubRepositorio = clubRepositorio;
    }

    /**
     * Da de alta un nuevo club en la base de datos.
     * <p>
     * Convierte un objeto {@link ClubDto} a {@link Club} y lo guarda en la base de datos.
     * </p>
     *
     * @param clubDto datos del club que se desea dar de alta
     */
    @Override
    @Transactional
    public void altaClub(ClubDto clubDto) {
        // Convertir ClubDto a Club sin establecer idClub
        Club club = new Club();
        club.setNombreClub(clubDto.getNombreClub());
        club.setEmailClub(clubDto.getEmailClub());
        club.setPasswdClub(clubDto.getPasswdClub());
        club.setSedeClub(clubDto.getSedeClub());
        club.setImagenClub(clubDto.getImagenClub());
        
        // Guardar el club en la base de datos
        clubRepositorio.save(club);
    }

    /**
     * Elimina un club existente de la base de datos usando el nombre del club.
     *
     * @param nombreClub nombre del club que se desea eliminar
     */
    @Override
    @Transactional
    public void eliminarClub(String nombreClub) {
        clubRepositorio.deleteByNombreClub(nombreClub);
    }

    /**
     * Autentica un club utilizando su email y contraseña.
     * <p>
     * Busca el club en la base de datos y, si se encuentra, convierte el {@link Club}
     * a un {@link ClubDto} para transferir los datos.
     * </p>
     *
     * @param email    email del club
     * @param password contraseña del club
     * @return un {@link Optional} que contiene el {@link ClubDto} si el club es encontrado; de lo contrario, un Optional vacío
     */
    @Override
    public Optional<ClubDto> loginClub(String email, String password) {
        Optional<Club> clubOpt = clubRepositorio.findByEmailClubAndPasswdClub(email, password);
        
        if (clubOpt.isPresent()) {
            Club club = clubOpt.get();
            // Convertir Club a ClubDto
            ClubDto clubDto = new ClubDto();
            clubDto.setNombreClub(club.getNombreClub());
            clubDto.setEmailClub(club.getEmailClub());
            clubDto.setPasswdClub(club.getPasswdClub());
            clubDto.setSedeClub(club.getSedeClub());
            club.setImagenClub(clubDto.getImagenClub());
            return Optional.of(clubDto);
        }
        return Optional.empty();
    }
    
    /**
     * Modifica los campos nombre, sede e imagen de un club existente en la base de datos.
     *
     * @param idClub identificador del club a modificar
     * @param clubDto objeto DTO que contiene los nuevos datos del club
     * @return un {@link Optional<ClubDto>} con los datos actualizados si la modificación fue exitosa; de lo contrario, un Optional vacío
     */
    @Transactional
    public boolean modificarClub(long idClub, String nuevoNombre, String nuevaSede, byte[] nuevaImagen) {
        Optional<Club> clubOpt = clubRepositorio.findById(idClub);

        if (clubOpt.isPresent()) {
            Club club = clubOpt.get();
            // Modificar los campos
            if (nuevoNombre != null) club.setNombreClub(nuevoNombre);
            if (nuevaSede != null) club.setSedeClub(nuevaSede);
            if (nuevaImagen != null) club.setImagenClub(nuevaImagen);

            clubRepositorio.save(club); // Guardar los cambios
            return true;
        }

        return false; // Si el club no existe
    }
}
	

