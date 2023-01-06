package com.biblioteca.interfaces;

import java.util.ArrayList;
import com.biblioteca.entidad.Producto;
import com.biblioteca.entidad.SalidaProductos;

public interface SalidaDAO {
	public int save(SalidaProductos bean,ArrayList<Producto> lista);
	public ArrayList<SalidaProductos> findAll();

}
