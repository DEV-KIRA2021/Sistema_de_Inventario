package com.biblioteca.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.entidad.Inventario;
import com.biblioteca.utils.MySqlConexion;

public class MySqlInventarioDAO {
	public ArrayList<Inventario> findAll() {
		ArrayList<Inventario> data=new ArrayList<Inventario>();
		Connection cn=null;
		PreparedStatement pstm=null;
		//objeto para guardar el resultado de un select
		ResultSet rs=null;
		try {
			//PASO 1: conexi�n b.d.
			cn=MySqlConexion.getConexion();
			//PASO 2: sentencia sql
			String sql="select Productos.codProd,Productos.descripProd,ProductosEntrada.cantidadProdEnt,ProductosSalida.cantidadProdSal,ProductosEntrada.cantidadProdEnt-ProductosSalida.cantidadProdSal "
					+ "from Productos join ProductosEntrada "
					+ "on Productos.codProd = ProductosEntrada.codProd join ProductosSalida "
					+ "on ProductosSalida.codProd=Productos.codProd";
			//PASO 3:crear pstm y enviar la sentencia sql
			pstm=cn.prepareStatement(sql);
			//PASO 4: par�metros "? que tiene la sentencia"
			
			//PASO 5:ejecutar sentencia y guardar el valor en el objeto "rs"
			//executeQuery() es un m�todo para ejecutar un select
			rs=pstm.executeQuery();
			//PASO 6: bucle para realizar recorrido sobre "rs"
			while(rs.next()) {
				//PASO 7: crear objeto de la clase Libro
				Inventario pro=new Inventario();
				//PASO 8: asignar valor a los atributos del objeto "lib" seg�n la fila actual 
				pro.setCodigo(rs.getString(1));
				pro.setDescripcion(rs.getString(2));
				pro.setEntrada(rs.getInt(3));
				pro.setSalida(rs.getInt(4));
				pro.setStock(rs.getInt(5));
				//PASO 9: enviar objeto "lib" dentro del arreglo "data"
				data.add(pro);
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
	
	public ArrayList<Inventario> findAllByInventario(String cod) {
		ArrayList<Inventario> data=new ArrayList<Inventario>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select P.codProd,P.descripProd,E.cantidadProdEnt-S.cantidadProdSal \r\n"
					+ "					from Productos P join ProductosEntrada E\r\n"
					+ "					on P.codProd = E.codProd join ProductosSalida S\r\n"
					+ "					on S.codProd=P.codProd where P.codProd like ?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, cod+"%");
			rs=pstm.executeQuery();
			while(rs.next()){
				Inventario c=new Inventario();
				c.setCodigo(rs.getString(1));
				c.setDescripcion(rs.getString(2));

				c.setStock(rs.getInt(3));
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
