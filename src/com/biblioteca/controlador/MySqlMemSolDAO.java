package com.biblioteca.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.entidad.MemorandumSolicitud;
import com.biblioteca.interfaces.MemorandumSolicitudDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlMemSolDAO implements MemorandumSolicitudDAO {

	@Override
	public int genReport(MemorandumSolicitud bean) {
		int salida = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			
			cn = MySqlConexion.getConexion();
			
			String sql = "insert into Solicitud values(?, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);
			
			pstm.setString(1,  bean.getCodMemSol());
			pstm.setString(2, bean.getFechaMemSol());
			pstm.setString(3, bean.getDestinMemSol());
			pstm.setString(4, bean.getTemaMemSol());
			pstm.setString(5, bean.getFirmaMemSol());
			
			salida = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) 	pstm.close();
				if(cn != null) 	cn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}

	@Override
	public int update(MemorandumSolicitud bean) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = MySqlConexion.getConexion();
			
			String sql = "update Solicitud set codSoli = ? , fechaSoli = ? , destinoSoli = ? , temaSoli = ? , firmaSoli = ? where codSoli = ?;";
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, bean.getCodMemSol());
			pstm.setString(2, bean.getFechaMemSol());
			pstm.setString(3, bean.getDestinMemSol());
			pstm.setString(4, bean.getTemaMemSol());
			pstm.setString(5, bean.getFirmaMemSol());
			pstm.setString(6, bean.getCodMemSol());
			
			salida = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) pstm.close();
				if ( conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int delete(String codMemSol) {
		int salida = -1;
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlConexion.getConexion();
			
			String sql = "delete from Solicitud where codSoli = ?";
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, codMemSol);
			
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
	public ArrayList<MemorandumSolicitud> findall() {
		ArrayList<MemorandumSolicitud> data = new ArrayList<>();
		
		Connection cn =null;
		PreparedStatement pstm = null;
		
		ResultSet rs= null;
		
		try {
			cn= MySqlConexion.getConexion();
			
			String sql = "select * from Solicitud";
			
			pstm = cn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while(rs.next() == true) {
				MemorandumSolicitud memSol = new MemorandumSolicitud();
				
				memSol.setCodMemSol(rs.getString(1));
				memSol.setFechaMemSol(rs.getString(2));
				memSol.setDestinMemSol(rs.getString(3));
				memSol.setTemaMemSol(rs.getString(4));
				memSol.setFirmaMemSol(rs.getString(5));
				
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
