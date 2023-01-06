package com.biblioteca.entidad;

import java.sql.Date;

public class SalidaProductos {
	private String codigoSalida, descripcion, codigoProd;
	private Date fechaSalida;
	private int cantidad;
	public String getCodigoSalida() {
		return codigoSalida;
	}
	public void setCodigoSalida(String codigoSalida) {
		this.codigoSalida = codigoSalida;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getCodigoProd() {
		return codigoProd;
	}
	public void setCodigoProd(String codigoProd) {
		this.codigoProd = codigoProd;
	}
	
	
	
	

}
