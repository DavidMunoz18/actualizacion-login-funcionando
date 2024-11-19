package edu.proyectoApi.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Arrays;
import java.util.Objects;

import edu.proyectoApi.dto.ClubDto;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

/**
 * Clase que representa la entidad Club en la base de datos.
 * <p>
 * Esta clase contiene los atributos que definen a un club, como su nombre,
 * email, contraseña, sede y una imagen. Está mapeada como una entidad JPA para 
 * facilitar su persistencia en la base de datos.
 * </p>
 */
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "clubs", schema = "proyecto") // Define el nombre de la tabla y el esquema
public class Club {

    /** Identificador único del club, generado automáticamente. */
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID automáticamente
    @Column(name = "id_club", nullable = false, updatable = false) // Configura la columna de la base de datos
    private long idClub;

    @Column(name = "nombre_club", nullable = false, length = 100) // Campo requerido, longitud máxima 100
    private String nombreClub;

    /** Email de contacto del club. */
    @Column(name = "email_club", nullable = false, unique = true, length = 150) // Campo único y requerido
    private String emailClub;

    /** Contraseña del club para autenticación. */
    @Column(name = "passwd_club", nullable = false, length = 255) // Campo requerido, longitud máxima 255
    private String passwdClub;

    /** Sede del club. */
    @Column(name = "sede_club", nullable = true, length = 200) // Campo opcional
    private String sedeClub;

    /** Imagen del club en formato de bytes. */
    @Column(name = "logo_club", columnDefinition = "bytea") // Personaliza la definición del tipo de columna
    @Basic(fetch = FetchType.LAZY) // Indica carga diferida para este campo
    private byte[] imagenClub;

    /******************************* CONSTRUCTORES ***********************************/

    /**
     * Constructor vacío, requerido por JPA.
     */
    public Club() {
    }

    /**
     * Constructor para crear un nuevo club sin el ID.
     *
     * @param nombreClub el nombre del club
     * @param emailClub el email del club
     * @param passwdClub la contraseña del club
     * @param sedeClub la sede del club
     * @param imagenClub la imagen del club en formato de bytes
     */
    public Club(String nombreClub, String emailClub, String passwdClub, String sedeClub, byte[] imagenClub) {
        this.nombreClub = nombreClub;
        this.emailClub = emailClub;
        this.passwdClub = passwdClub;
        this.sedeClub = sedeClub;
        this.imagenClub = imagenClub;
    }

    /******************************* GETTERS Y SETTERS **************************************/
    public long getIdClub() {
        return idClub; // Solo tiene getter, ya que no queremos que se establezca manualmente
    }

    public String getNombreClub() {
        return nombreClub;
    }

    public void setNombreClub(String nombreClub) {
        this.nombreClub = nombreClub;
    }

    public String getEmailClub() {
        return emailClub;
    }

    public void setEmailClub(String emailClub) {
        this.emailClub = emailClub;
    }

    public String getPasswdClub() {
        return passwdClub;
    }

    public void setPasswdClub(String passwdClub) {
        this.passwdClub = passwdClub;
    }

    public String getSedeClub() {
        return sedeClub;
    }

    public void setSedeClub(String sedeClub) {
        this.sedeClub = sedeClub;
    }

    public byte[] getImagenClub() {
        return imagenClub;
    }

    public void setImagenClub(byte[] imagenClub) {
        this.imagenClub = imagenClub;
    }

    /*************************************** METODOS ***************************************/
    /**
     * Devuelve una representación en cadena de texto del objeto ClubDto.
     *
     * @return una cadena que representa el ClubDto con sus atributos
     */
    @Override
    public String toString() {
        return "ClubDto [nombreClub=" + nombreClub + ", emailClub=" + emailClub + ", passwdClub=" + passwdClub
                + ", sedeClub=" + sedeClub + ", imagenClub=" + Arrays.toString(imagenClub) + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreClub, emailClub, passwdClub, sedeClub, imagenClub);
    }
}
