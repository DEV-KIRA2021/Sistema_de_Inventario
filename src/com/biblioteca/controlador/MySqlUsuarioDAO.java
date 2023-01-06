package com.biblioteca.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biblioteca.entidad.Usuario;
import com.biblioteca.interfaces.UsuarioDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlUsuarioDAO implements UsuarioDAO{
	@Override
	public Usuario iniciarSesion(String login, String clave) {
		Usuario u = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql = "select nomUsu, apeUsu from Usuario "+
							"where idUsu = ? and passUsu = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, clave);
			rs=pstm.executeQuery();
			//validar si existe registro
			if(rs.next()){
				//crear objeto "u"
				u=new Usuario();
				//setear
				u.setNombre(rs.getString(1));
				u.setApellido(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return u;
	}
}
