package edu.proyectoApi.dto;

import java.util.Arrays;

public class UsuarioDto {

    private String nicknameUsuario;
    private String nombreUsuario;
    private String dniUsuario;
    private String telefonoUsuario;
    private byte[] fotoUsuario;
    private String emailUsuario;
    private String passwdUsuario;
    private String rol; // Aquí agregamos el campo rol, tipo String

    // Constructor vacío
    public UsuarioDto() {}

    // Constructor con parámetros
    public UsuarioDto(String nicknameUsuario, String nombreUsuario, String dniUsuario,
                      String telefonoUsuario, byte[] fotoUsuario, String emailUsuario,
                      String passwdUsuario, String rol) { // Ahora 'rol' en lugar de booleanos
        this.nicknameUsuario = nicknameUsuario;
        this.nombreUsuario = nombreUsuario;
        this.dniUsuario = dniUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fotoUsuario = fotoUsuario;
        this.emailUsuario = emailUsuario;
        this.passwdUsuario = passwdUsuario;
        this.rol = rol; // Aquí se asigna el rol
    }

    // Getters y Setters
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
        return rol; // Ahora obtenemos el rol
    }

    public void setRol(String rol) {
        this.rol = rol; // Ahora asignamos el rol
    }

    // Método toString (opcional, si lo necesitas)
    @Override
    public String toString() {
        return "UsuarioDto [nicknameUsuario=" + nicknameUsuario +
               ", nombreUsuario=" + nombreUsuario + ", dniUsuario=" + dniUsuario +
               ", telefonoUsuario=" + telefonoUsuario + ", fotoUsuario=" + Arrays.toString(fotoUsuario) +
               ", emailUsuario=" + emailUsuario + ", passwdUsuario=" + passwdUsuario +
               ", rol=" + rol + "]"; // Cambiado esAdministrador por rol
    }
}
