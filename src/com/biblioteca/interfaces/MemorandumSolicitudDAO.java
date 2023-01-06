package com.biblioteca.interfaces;

import java.util.ArrayList;

import com.biblioteca.entidad.MemorandumSolicitud;

public interface MemorandumSolicitudDAO {
	public int genReport(MemorandumSolicitud bean);
	public int update(MemorandumSolicitud bean);
	public int delete(String codMemSol);
	public ArrayList<MemorandumSolicitud> findall();
}
