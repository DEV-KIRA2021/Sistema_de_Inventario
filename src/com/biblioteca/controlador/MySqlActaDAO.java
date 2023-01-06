package com.biblioteca.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.entidad.Producto;
import com.biblioteca.entidad.ActaInventario;
import com.biblioteca.interfaces.ActaDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlActaDAO implements ActaDAO{
	@Override
	public int save(ActaInventario bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstmBol=null,pstmDet=null;
		try {
			//paso 1: obtener conexi�n a bd
			cn=MySqlConexion.getConexion();
			//paso 2: bloquear los commit's
			cn.setAutoCommit(false);
			//paso 3: 
			String sqlBol="insert into ActaInventario values(?,?,?,?,?)";
			//paso 4: crear pstmBol
			pstmBol=cn.prepareStatement(sqlBol);
			//paso 5: par�metros
			pstmBol.setString(1, bean.getCodActInv());
			pstmBol.setString(2, bean.getCodprod());
			pstmBol.setString(3, bean.getDescripcion());
			pstmBol.setInt(4, bean.getStoc());
			pstmBol.setInt(5, bean.getCodDifere());
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
	public ArrayList<ActaInventario> findAll() {
		ArrayList<ActaInventario> data=new ArrayList<ActaInventario>();
		Connection cn=null;
		PreparedStatement pstm=null;
		//objeto para guardar el resultado de un select
		ResultSet rs=null;
		try {
			//PASO 1: conexi�n b.d.
			cn=MySqlConexion.getConexion();
			//PASO 2: sentencia sql
			String sql="select a.codActInv,p.codProd,p.descripProd, e.cantidadProdEnt-s.cantidadProdSal, d.tipoDif\r\n"
					+ "					from ActaInventario a join Productos p\r\n"
					+ "					 on a.codProd = p.codProd join ProductosSalida s\r\n"
					+ "					on s.codProd=p.codProd join ProductosEntrada e\r\n"
					+ "					on e.codProd=p.codProd join tipoDiferencia d\r\n"
					+ "					on d.codDiferencia=a.codDiferencia";
			//PASO 3:crear pstm y enviar la sentencia sql
			pstm=cn.prepareStatement(sql);
			//PASO 4: par�metros "? que tiene la sentencia"
		
			
			//PASO 5:ejecutar sentencia y guardar el valor en el objeto "rs"
			//executeQuery() es un m�todo para ejecutar un select
			rs=pstm.executeQuery();
			//PASO 6: bucle para realizar recorrido sobre "rs"
			while(rs.next()) {
				//PASO 7: crear objeto de la clase Libro
				ActaInventario av=new ActaInventario();
				//PASO 8: asignar valor a los atributos del objeto "lib" seg�n la fila actual 
				av.setCodActInv(rs.getString(1));//1 es la columna c�digo
				av.setCodprod(rs.getString(2));//2 es columna t�tulo
				av.setDescripcion(rs.getString(3));//3 es la columna precio
				av.setStoc(rs.getInt(4));//4 es la columna cantidad
				av.setNombreDife(rs.getString(5));//5 es la columna nombre editorial
				data.add(av);
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
	public ArrayList<ActaInventario> findAllByTipoDiferencia(String nomDif) {
		ArrayList<ActaInventario> data=new ArrayList<ActaInventario>();
		Connection cn=null;
		CallableStatement cstm=null;
		//objeto para guardar el resultado de un select
		ResultSet rs=null;
		try {
			//PASO 1: conexi�n b.d.
			cn=MySqlConexion.getConexion();
			//PASO 2: sentencia sql
			String sql="call sp_reporte_actaInventario_por_tipoDif(?)";
			//PASO 3:crear pstm y enviar la sentencia sql
			cstm=cn.prepareCall(sql);
			//PASO 4: par�metros "? que tiene la sentencia"
			cstm.setString(1, nomDif);
			//PASO 5:ejecutar sentencia y guardar el valor en el objeto "rs"
			//executeQuery() es un m�todo para ejecutar un select
			rs=cstm.executeQuery();
			//PASO 6: bucle para realizar recorrido sobre "rs"
			while(rs.next()) {
				//PASO 7: crear objeto de la clase Libro
				ActaInventario a=new ActaInventario();
				//PASO 8: asignar valor a los atributos del objeto "lib" seg�n la fila actual 
				a.setCodActInv(rs.getString(1));//1 es la columna c�digo
				a.setCodprod(rs.getString(2));//2 es columna t�tulo
				a.setDescripcion(rs.getString(3));//3 es la columna precio
				a.setStoc(rs.getInt(4));//4 es la columna cantidad
				a.setNombreDife(rs.getString(5));//5 es la columna nombre editorial
				//PASO 9: enviar objeto "lib" dentro del arreglo "data"
				data.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(cstm!=null) cstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}
}
