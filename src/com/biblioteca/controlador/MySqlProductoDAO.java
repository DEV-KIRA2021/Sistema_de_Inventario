package com.biblioteca.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.entidad.Producto;
import com.biblioteca.interfaces.ProductoDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlProductoDAO implements ProductoDAO {

	@Override
	public int save(Producto bean) {
		int salida=-1;
		Connection conn=null;
		PreparedStatement pstm=null;
		try {
			//PASO 1: obtener conexi�n a base de datos
			conn=MySqlConexion.getConexion();
			//PASO 2: instrucci�n SQL INSERT INTO (asignar par�metros "?")
			String sql="insert into Productos values(?,?)";
			//PASO 3: crear objeto pstm y enviar la instrucci�n sql
			pstm=conn.prepareStatement(sql);
			//PASO 4: par�metros de la instrucci�n sql que maneja el objeto pstm
			pstm.setString(1, bean.getCodigoProd());
			pstm.setString(2, bean.getDescripcion());
			//PASO 5: ejecutar instrucci�n sql que maneja el objeto pstm
			salida=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int update(Producto bean) {
		int salida=-1;
		Connection conn=null;
		PreparedStatement pstm=null;
		try {
			//1
			conn=MySqlConexion.getConexion();
			//2
			String sql="update Productos set codProd=?,descripProd=? where codProd=?";
			//3
			pstm=conn.prepareStatement(sql);
			//4
			pstm.setString(1,bean.getCodigoProd());
			pstm.setString(2, bean.getDescripcion());
			pstm.setString(3,bean.getCodigoProd());
			
			//5
			salida=pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(conn!=null)conn.close(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int deleteByID(String cod) {
		int salida=-1;
		Connection conn=null;
		PreparedStatement pstm=null;
		try {
			//1
			conn=MySqlConexion.getConexion();
			//2
			String sql="delete from Productos where codProd=?";
			//3
			pstm=conn.prepareStatement(sql);
			//4
			pstm.setString(1, cod);
			//5
			salida=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public ArrayList<Producto> findAll() {
		ArrayList<Producto> data=new ArrayList<Producto>();
		Connection cn=null;
		PreparedStatement pstm=null;
		//objeto para guardar el resultado de un select
		ResultSet rs=null;
		try {
			//PASO 1: conexi�n b.d.
			cn=MySqlConexion.getConexion();
			//PASO 2: sentencia sql
			String sql="select p.codProd,p.descripProd from Productos p";
			//PASO 3:crear pstm y enviar la sentencia sql
			pstm=cn.prepareStatement(sql);
			//PASO 4: par�metros "? que tiene la sentencia"
			
			//PASO 5:ejecutar sentencia y guardar el valor en el objeto "rs"
			//executeQuery() es un m�todo para ejecutar un select
			rs=pstm.executeQuery();
			//PASO 6: bucle para realizar recorrido sobre "rs"
			while(rs.next()) {
				//PASO 7: crear objeto de la clase Libro
				Producto p=new Producto();
				//PASO 8: asignar valor a los atributos del objeto "lib" seg�n la fila actual 
				p.setCodigoProd(rs.getString(1));//1 es la columna c�digo
				p.setDescripcion(rs.getString(2));//2 es columna t�tulo
				//PASO 9: enviar objeto "lib" dentro del arreglo "data"
				data.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}

	@Override
	public ArrayList<Producto> findAllByProducto(String cod) {
		ArrayList<Producto> data=new ArrayList<Producto>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="SELECT * FROM Productos where codProd like ?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, cod+"%");
			rs=pstm.executeQuery();
			while(rs.next()){
				Producto c=new Producto();
				c.setCodigoProd(rs.getString(1));
				c.setDescripcion(rs.getString(2));
				data.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return data;
	}


}
