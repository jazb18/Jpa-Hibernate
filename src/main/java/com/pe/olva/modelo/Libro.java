package com.pe.olva.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LIBRO")
public class Libro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_LIBRO")
	private Long id; 
	
	@Column(name = "LIBRO")
	private String titulo;
	
	/**
	 *Por parte de un ManytoOne siempre es necesario declararle un fetch lazy ya que no sea representado. 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="AUTOR_ID_AUTOR")
	private Autor autor;
	
	public Libro() {
		// TODO Auto-generated constructor stub
	}
	
	public Libro(Long id, String titulo, Autor autor) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + "]";
	}

}
