package com.biblioteca.interfaces;

import java.util.ArrayList;

import com.biblioteca.entidad.MemDesig;

public interface MemDesigDAO {
	public int genReport(MemDesig bean);
	public int update(MemDesig bean);
	public int delete(String codMemDesig);
	public ArrayList<MemDesig> findall();
}
