package edu.proyectoApi.dto;

import java.util.Arrays;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) para la entidad Club.
 * <p>
 * Esta clase se utiliza para transferir datos entre la API y la capa de servicio, 
 * aislando la lógica de la entidad principal y proporcionando solo los datos necesarios.
 * </p>
 */
public class ClubDto {
    
    /** Nombre del club. */
    private String nombreClub;
    
    /** Email de contacto del club. */
    private String emailClub;
    
    /** Contraseña del club para autenticación. */
    private String passwdClub;
    
    /** Sede del club. */
    private String sedeClub;
    
    /** Imagen del club en formato de bytes. */
    private byte[] imagenClub;
    
    

    /**
     * Constructor vacío, requerido para la creación del DTO sin inicializar valores.
     */
    public ClubDto() {
        super();
    }

    /**
     * Constructor con parámetros para crear un nuevo ClubDto con todos sus atributos.
     *
     * @param nombreClub el nombre del club
     * @param emailClub el email de contacto del club
     * @param passwdClub la contraseña del club
     * @param sedeClub la sede del club
     * @param imagenClub la imagen del club en formato de bytes
     */
    public ClubDto(String nombreClub, String emailClub, String passwdClub, String sedeClub, byte[] imagenClub, boolean admin) {
        this.nombreClub = nombreClub;
        this.emailClub = emailClub;
        this.passwdClub = passwdClub;
        this.sedeClub = sedeClub;
        this.imagenClub = imagenClub;
       
    }
 
    /**
     * Obtiene el nombre del club.
     *
     * @return el nombre del club
     */
    public String getNombreClub() {
        return nombreClub;
    }

    /**
     * Establece el nombre del club.
     *
     * @param nombreClub el nuevo nombre del club
     */
    public void setNombreClub(String nombreClub) {
        this.nombreClub = nombreClub;
    }

    /**
     * Obtiene el email del club.
     *
     * @return el email del club
     */
    public String getEmailClub() {
        return emailClub;
    }

    /**
     * Establece el email del club.
     *
     * @param emailClub el nuevo email del club
     */
    public void setEmailClub(String emailClub) {
        this.emailClub = emailClub;
    }

    /**
     * Obtiene la contraseña del club.
     *
     * @return la contraseña del club
     */
    public String getPasswdClub() {
        return passwdClub;
    }

    /**
     * Establece la contraseña del club.
     *
     * @param passwdClub la nueva contraseña del club
     */
    public void setPasswdClub(String passwdClub) {
        this.passwdClub = passwdClub;
    }

    /**
     * Obtiene la sede del club.
     *
     * @return la sede del club
     */
    public String getSedeClub() {
        return sedeClub;
    }

    /**
     * Establece la sede del club.
     *
     * @param sedeClub la nueva sede del club
     */
    public void setSedeClub(String sedeClub) {
        this.sedeClub = sedeClub;
    }

    /**
     * Obtiene la imagen del club.
     *
     * @return la imagen del club en formato de bytes
     */
    public byte[] getImagenClub() {
        return imagenClub;
    }

    /**
     * Establece la imagen del club.
     *
     * @param imagenClub la nueva imagen del club en formato de bytes
     */
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClubDto other = (ClubDto) obj;
		
		return Objects.equals(nombreClub, other.nombreClub)
				&& Objects.equals(emailClub, other.emailClub) && Objects.equals(passwdClub, other.passwdClub)
				&& Objects.equals(imagenClub, other.imagenClub);
	}
}
