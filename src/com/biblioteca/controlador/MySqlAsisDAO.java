package com.biblioteca.controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biblioteca.entidad.Usuario;
import com.biblioteca.interfaces.AsistenteDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlAsisDAO implements AsistenteDAO{

		@Override
		public Usuario iniciarAsistente(String clave) {
			Usuario u = null;
			Connection cn = null;
			PreparedStatement pstm = null;
			ResultSet rs=null;
			try {
				cn=MySqlConexion.getConexion();
				String sql = "select nomUsu, apeUsu from Usuario "+
								"where idUsu = 'SUB' and passUsu = ?";
				pstm = cn.prepareStatement(sql);
				pstm.setString(1, clave);
				rs=pstm.executeQuery();
				//validar si existe registro
				if(rs.next()){
					//crear objeto "u"
					u=new Usuario();
					//setear
					u.setApellido(rs.getString(1));
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
