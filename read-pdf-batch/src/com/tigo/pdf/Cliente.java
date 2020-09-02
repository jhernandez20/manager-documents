package com.tigo.pdf;

public class Cliente {
	
	private int id_Obligado;
	private int total_page;
	private int start_page;
	private int numero_factura;
	private int numero_resolucion;
	private int tipo_documento;
	
	public Cliente(int id_Obligado, int total_page, int start_page, int numero_factura, int numero_resolucion,
			int tipo_documento) {
		super();
		this.id_Obligado = id_Obligado;
		this.total_page = total_page;
		this.start_page = start_page;
		this.numero_factura = numero_factura;
		this.numero_resolucion = numero_resolucion;
		this.tipo_documento = tipo_documento;
	}

		
	public int getId_Obligado() {
		return id_Obligado;
	}
	public void setId_Obligado(int id_Obligado) {
		this.id_Obligado = id_Obligado;
	}
	public int getTotal_page() {
		return total_page;
	}
	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}
	public int getStart_page() {
		return start_page;
	}
	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}
	public int getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(int numero_factura) {
		this.numero_factura = numero_factura;
	}
	public int getNumero_resolucion() {
		return numero_resolucion;
	}
	public void setNumero_resolucion(int numero_resolucion) {
		this.numero_resolucion = numero_resolucion;
	}
	public int getTipo_documento() {
		return tipo_documento;
	}
	public void setTipo_documento(int tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	
}
