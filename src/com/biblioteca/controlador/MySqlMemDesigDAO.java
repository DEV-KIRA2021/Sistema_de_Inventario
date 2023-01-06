package com.biblioteca.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.entidad.MemDesig;
import com.biblioteca.interfaces.MemDesigDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlMemDesigDAO implements MemDesigDAO {

	@Override
	public int genReport(MemDesig bean) {
		int salida = -1;
		Connection conn=null;
		PreparedStatement pstm=null;
		
		try {
			conn = MySqlConexion.getConexion();
			
			String sql = "insert into Designacion values(?,?,?,?,?)";
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,  bean.getCodMemDesig());
			pstm.setString(2, bean.getFechaMemDesig());
			pstm.setString(3, bean.getDestinDesig());
			pstm.setString(4, bean.getTemaMemDesig());
			pstm.setString(5, bean.getFirmaMemDesig());
		
			
			salida = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) 	pstm.close();
				if(conn != null) 	conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}

	@Override
	public int update(MemDesig bean) {
		int salida = -1;
		Connection conn=null;
		PreparedStatement pstm=null;
		
		try {
			conn = MySqlConexion.getConexion();
			
			String sql = "update Designacion set codDesig= ?, fechaDesig=?, destinoDesig=?,  temaDesig =?, firmaDesig=? where codDesig= ?";
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,  bean.getCodMemDesig());
			pstm.setString(2, bean.getFechaMemDesig());
			pstm.setString(3, bean.getDestinDesig());
			pstm.setString(4, bean.getTemaMemDesig());
			pstm.setString(5, bean.getFirmaMemDesig());
			pstm.setString(6,  bean.getCodMemDesig());
			
			salida = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) 	pstm.close();
				if(conn != null) 	conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}

	@Override
	public int delete(String codMemDesig) {
    int salida = -1;
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlConexion.getConexion();
			
			String sql = "delete from Designacion where codDesig = ?";
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, codMemDesig);
			
			salida = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm !=null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}

	@Override
	public ArrayList<MemDesig> findall() {
		ArrayList<MemDesig> data = new ArrayList<>();
		
		Connection cn =null;
		PreparedStatement pstm = null;
		
		ResultSet rs= null;
		
		try {
			cn= MySqlConexion.getConexion();
			
			String sql = "select * from Designacion";
			
			pstm = cn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while(rs.next() == true) {
				MemDesig memSol = new MemDesig();
				
				memSol.setCodMemDesig(rs.getString(1));
				memSol.setFechaMemDesig(rs.getString(2));
				memSol.setDestinDesig(rs.getString(3));
				memSol.setTemaMemDesig(rs.getString(4));
				memSol.setFirmaMemDesig(rs.getString(5));
				
				data.add(memSol);
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
		
		return data;
	}

}
