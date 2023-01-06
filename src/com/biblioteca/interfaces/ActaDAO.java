package com.biblioteca.interfaces;

import java.util.ArrayList;

import com.biblioteca.entidad.ActaInventario;

public interface ActaDAO {
	public int save(ActaInventario bean);
	public ArrayList<ActaInventario> findAll();
	public ArrayList<ActaInventario> findAllByTipoDiferencia(String nomDif);
}
