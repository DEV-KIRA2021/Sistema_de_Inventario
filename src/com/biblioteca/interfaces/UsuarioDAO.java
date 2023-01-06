package com.biblioteca.interfaces;

import com.biblioteca.entidad.Usuario;

public interface UsuarioDAO {
	public Usuario iniciarSesion(String login,String clave);
}
