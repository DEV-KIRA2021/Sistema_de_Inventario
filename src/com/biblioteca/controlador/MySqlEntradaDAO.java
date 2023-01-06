package com.biblioteca.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.biblioteca.entidad.EntradaProductos;
import com.biblioteca.entidad.Producto;
import com.biblioteca.interfaces.EntradaDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlEntradaDAO implements EntradaDAO {

	@Override
	public int save(EntradaProductos bean, ArrayList<Producto> lista) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstmBol=null,pstmDet=null;
		try {
			//paso 1: obtener conexi�n a bd
			cn=MySqlConexion.getConexion();
			//paso 2: bloquear los commit's
			cn.setAutoCommit(false);
			//paso 3: sentencia sql para boleta 
			String sqlBol="insert into ProductosEntrada values(?,?,?,?,?)";
			//paso 4: crear pstmBol
			pstmBol=cn.prepareStatement(sqlBol);
			//paso 5: par�metros
			pstmBol.setString(1, bean.getCodigoEntrada());
			pstmBol.setDate(2, bean.getFecha());
			pstmBol.setString(3, bean.getCodigoProducto());
			pstmBol.setString(4, bean.getDescripcionProd());
			pstmBol.setInt(5, bean.getCantidad());
			//paso 6: ejecutar
			salida=pstmBol.executeUpdate();
			//confirmar insert�s
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
				salida=-1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmDet!=null) pstmDet.close();
				if(pstmBol!=null) pstmBol.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public ArrayList<EntradaProductos> findAll() {
		ArrayList<EntradaProductos> data=new ArrayList<EntradaProductos>();
		Connection cn=null;
		PreparedStatement pstm=null;
		//objeto para guardar el resultado de un select
		ResultSet rs=null;
		try {
			//PASO 1: conexi�n b.d.
			cn=MySqlConexion.getConexion();
			//PASO 2: sentencia sql
			String sql="select ep.codProdEnt,ep.fechaProdEnt,ep.codProd,ep.descripProdEnt,"+
							"ep.cantidadProdEnt from ProductosEntrada ep join Productos p on ep.codProd=p.codProd";
			//PASO 3:crear pstm y enviar la sentencia sql
			pstm=cn.prepareStatement(sql);
			//PASO 4: par�metros "? que tiene la sentencia"
			
			
			//PASO 5:ejecutar sentencia y guardar el valor en el objeto "rs"
			//executeQuery() es un m�todo para ejecutar un select
			rs=pstm.executeQuery();
			//PASO 6: bucle para realizar recorrido sobre "rs"
			while(rs.next()) {
				//PASO 7: crear objeto de la clase Libro
				EntradaProductos ep=new EntradaProductos();
				//PASO 8: asignar valor a los atributos del objeto "lib" seg�n la fila actual 
				ep.setCodigoEntrada(rs.getString(1));//1 es la columna c�digo
				ep.setFecha(rs.getDate(2));//2 es columna t�tulo
				ep.setCodigoProducto(rs.getString(3));//3 es la columna precio
				ep.setDescripcionProd(rs.getString(4));//4 es la columna cantidad
				ep.setCantidad(rs.getInt(5));//5 es la columna nombre editorial
				//PASO 9: enviar objeto "lib" dentro del arreglo "data"
				data.add(ep);
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

}
