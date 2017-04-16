package com.pe.olva.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTOR")
public class Autor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_AUTOR")
	private Long id;

	@Column(name = "AUTOR")
	private String nombre;

	@Column(name = "NACIONALIDAD")
	private String nacionalidad;

	// Siempre utilizar un mappedBy
	// El cascade es Obsional ya que por defecto siempre viene como lazy
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	private List<Libro> libros;

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
		for (Libro libro : libros) {
			libro.setAutor(this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Autor() {

	}

	public Autor(Long id, String nombre, String nacionalidad) {
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	// RELACION PARA QUE SE ENTERE AMBAS TABLAS DE LA RELACION
	public void addLibros(Libro libro) {
		if (!libros.contains(libro)) {
			libros.add(libro);
			libro.setAutor(this);
		}
	}

	public void removeLibros(Libro libro) {
		if (libros.contains(libro)) {
			libros.remove(libro);
			libro.setAutor(null);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libros == null) ? 0 : libros.hashCode());
		result = prime * result + ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libros == null) {
			if (other.libros != null)
				return false;
		} else if (!libros.equals(other.libros))
			return false;
		if (nacionalidad == null) {
			if (other.nacionalidad != null)
				return false;
		} else if (!nacionalidad.equals(other.nacionalidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}

}
