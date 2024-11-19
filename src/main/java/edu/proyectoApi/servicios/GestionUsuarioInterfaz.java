package edu.proyectoApi.servicios;

import java.util.Optional;

import edu.proyectoApi.dto.UsuarioDto;

public interface GestionUsuarioInterfaz {
	 public void altaUsuario(UsuarioDto usuarioDto);
	 public boolean eliminarUsuario(String nicknameUsuario);
	 /*public Optional<String> loginUsuario(String email, String password);*/
	 public boolean modificarUsuario(long idUsuario, String nuevoNombre, String nuevoTelefono, byte[] nuevaFoto);

}
