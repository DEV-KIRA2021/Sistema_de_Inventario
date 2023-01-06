package com.biblioteca.interfaces;

import java.util.ArrayList;

import com.biblioteca.entidad.EntradaProductos;
import com.biblioteca.entidad.Producto;


public interface EntradaDAO {
	public int save(EntradaProductos bean,ArrayList<Producto> lista);
	public ArrayList<EntradaProductos> findAll();
}
