package edu.proyectoApi.dao;

import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios",schema="dlk") // Nombre de la tabla en la base de datos
public class Usuario {

    /** Identificador único del usuario, generado automáticamente. */
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID automáticamente
    @Column(name = "id_usuario", nullable = false, updatable = false) // Configuración de la columna en la base de datos
    private long idUsuario;

    @Column(name = "nickname_usuario", nullable = false, unique = true, length = 50) // Campo único y requerido
    private String nicknameUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 100) // Campo requerido
    private String nombreUsuario;

    @Column(name = "dni_usuario", nullable = false, unique = true, length = 20) // Campo único y requerido
    private String dniUsuario;

    @Column(name = "telefono_usuario", nullable = true, length = 15) // Campo opcional
    private String telefonoUsuario;

    @Column(name = "foto_usuario", columnDefinition = "bytea") // Personaliza el tipo de columna
    @Basic(fetch = FetchType.LAZY) // Indica carga diferida para este campo
    private byte[] fotoUsuario;

    @Column(name = "email_usuario", nullable = false, unique = true, length = 150) // Campo único y requerido
    private String emailUsuario;

    @Column(name = "passwd_usuario", nullable = false, length = 255) // Campo requerido
    private String passwdUsuario;

    /** Rol asignado al usuario, define los permisos de acceso (ADMIN, USER, etc.). */
    @Column(name = "rol_usuario", nullable = false, length = 50) // Campo requerido con longitud máxima
    private String rol;

    // ============================
    // Constructores
    // ============================

    /**
     * Constructor vacío. Necesario para JPA.
     */
    public Usuario() {
    }

    /**
     * Constructor con todos los campos (excepto el ID).
     * Útil para crear nuevos objetos antes de persistirlos.
     */
    public Usuario(String nicknameUsuario, String nombreUsuario, String dniUsuario, String telefonoUsuario, 
                   byte[] fotoUsuario, String emailUsuario, String passwdUsuario, String rol) {
        this.nicknameUsuario = nicknameUsuario;
        this.nombreUsuario = nombreUsuario;
        this.dniUsuario = dniUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fotoUsuario = fotoUsuario;
        this.emailUsuario = emailUsuario;
        this.passwdUsuario = passwdUsuario;
        this.rol = rol;
    }

    /**
     * Constructor completo (incluyendo ID).
     * Útil para pruebas o cuando el ID ya está definido.
     */
    public Usuario(long idUsuario, String nicknameUsuario, String nombreUsuario, String dniUsuario, 
                   String telefonoUsuario, byte[] fotoUsuario, String emailUsuario, 
                   String passwdUsuario, String rol) {
        this.idUsuario = idUsuario;
        this.nicknameUsuario = nicknameUsuario;
        this.nombreUsuario = nombreUsuario;
        this.dniUsuario = dniUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fotoUsuario = fotoUsuario;
        this.emailUsuario = emailUsuario;
        this.passwdUsuario = passwdUsuario;
        this.rol = rol;
    }

    // ============================
    // Getters y Setters
    // ============================

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public byte[] getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(byte[] fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getPasswdUsuario() {
        return passwdUsuario;
    }

    public void setPasswdUsuario(String passwdUsuario) {
        this.passwdUsuario = passwdUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // ============================
    // Método toString
    // ============================

    @Override
    public String toString() {
        return "Usuario{" +
               "idUsuario=" + idUsuario +
               ", nicknameUsuario='" + nicknameUsuario + '\'' +
               ", nombreUsuario='" + nombreUsuario + '\'' +
               ", dniUsuario='" + dniUsuario + '\'' +
               ", telefonoUsuario='" + telefonoUsuario + '\'' +
               ", emailUsuario='" + emailUsuario + '\'' +
               ", rol='" + rol + '\'' +
               '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(nicknameUsuario, nombreUsuario, dniUsuario, telefonoUsuario, fotoUsuario, emailUsuario, passwdUsuario, rol);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(nicknameUsuario, other.nicknameUsuario) &&
                Objects.equals(nombreUsuario, other.nombreUsuario) &&
                Objects.equals(dniUsuario, other.dniUsuario) &&
                Objects.equals(telefonoUsuario, other.telefonoUsuario) &&
                Arrays.equals(fotoUsuario, other.fotoUsuario) &&
                Objects.equals(emailUsuario, other.emailUsuario) &&
                Objects.equals(passwdUsuario, other.passwdUsuario) &&
                Objects.equals(rol, other.rol); // Compara el rol también
    }
}