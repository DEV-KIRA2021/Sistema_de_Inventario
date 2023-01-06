package com.biblioteca.interfaces;

import java.util.ArrayList;

import com.biblioteca.entidad.Inventario;

public interface InventarioDAO {
	public ArrayList<Inventario> findAll();
	public ArrayList<Inventario> findAllByInventario(String cod);
}
