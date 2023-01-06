package com.biblioteca.entidad;

public class ActaInventario {
	private String codActInv, codprod, descripcion,nombreDife;
	private int codDifere, stoc;
	public String getCodActInv() {
		return codActInv;
	}
	public void setCodActInv(String codActInv) {
		this.codActInv = codActInv;
	}
	public String getCodprod() {
		return codprod;
	}
	public void setCodprod(String codprod) {
		this.codprod = codprod;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreDife() {
		return nombreDife;
	}
	public void setNombreDife(String nombreDife) {
		this.nombreDife = nombreDife;
	}
	public int getCodDifere() {
		return codDifere;
	}
	public void setCodDifere(int codDifere) {
		this.codDifere = codDifere;
	}
	public int getStoc() {
		return stoc;
	}
	public void setStoc(int stoc) {
		this.stoc = stoc;
	}
	
	
}
