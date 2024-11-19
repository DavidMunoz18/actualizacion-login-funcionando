package edu.proyectoApi.dto;

/**
 * Representa los datos necesarios para iniciar sesión de un club.
 * <p>
 * Esta clase se utiliza para capturar y transferir las credenciales 
 * de inicio de sesión del club, como el email y la contraseña, entre 
 * la capa de presentación y la lógica de negocio.
 * </p>
 */
public class LoginClub {
    
    /** Email utilizado para iniciar sesión. */
    private String email;
    
    /** Contraseña utilizada para iniciar sesión. */
    private String password;

    /**
     * Constructor vacío, necesario para la deserialización y creación del objeto 
     * sin inicializar valores.
     */
    public LoginClub() {}

    /**
     * Constructor que inicializa el objeto LoginClub con el email y la contraseña.
     *
     * @param email el email del club para iniciar sesión
     * @param password la contraseña del club para iniciar sesión
     */
    public LoginClub(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Obtiene el email del club.
     *
     * @return el email del club
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del club.
     *
     * @param email el nuevo email del club
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del club.
     *
     * @return la contraseña del club
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del club.
     *
     * @param password la nueva contraseña del club
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
