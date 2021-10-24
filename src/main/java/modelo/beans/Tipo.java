package modelo.beans;

import java.io.Serializable;

public class Tipo implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	private int idTipo;
	private String nombre;
	private String descripcion;
	
	public Tipo() {
		super();
	}
	
	public Tipo(int id_tipo, String nombr, String descrip) {
		super();
		this.idTipo = id_tipo;
		this.nombre = nombr;
		this.descripcion = descrip;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Tipo))
			return false;
		Tipo other = (Tipo) obj;
		if (idTipo != other.idTipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tipo [idTipo=" + idTipo + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	
	
	

}
