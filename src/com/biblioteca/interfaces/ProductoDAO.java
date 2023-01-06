package com.biblioteca.interfaces;

import java.util.ArrayList;

import com.biblioteca.entidad.Producto;

public interface ProductoDAO {
	public int save(Producto bean);
	public int update(Producto bean);
	int deleteByID(String cod);
	public ArrayList<Producto> findAll();
	public ArrayList<Producto> findAllByProducto(String cod);
}
