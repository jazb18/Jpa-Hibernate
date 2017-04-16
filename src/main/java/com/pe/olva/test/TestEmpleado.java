package com.pe.olva.test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mysql.fabric.xmlrpc.base.Array;
import com.pe.olva.modelo.Autor;
import com.pe.olva.modelo.Direccion;
import com.pe.olva.modelo.Empleado;
import com.pe.olva.modelo.Libro;

public class TestEmpleado {

	// @PersistenceContext(unitName = "Persistencia")
	// @PersistenceUnit(unitName = "Persistencia")

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

	public static void main(String[] args) {
		/*saveTodo();
		
		update();
		find();
		
		imprimirTodo();*/
	
		crearDatosAutorLibros();
		imprimiendoAutores();
	}

	private static void saveTodo() {

		EntityManager manager = emf.createEntityManager();

		Empleado e = new Empleado(10L, "NEYRA", "OMAR", LocalDate.of(1991, 11, 8));
		e.setDireccion(new Direccion(3L, "Calle Falsa, 123", "SpringField", "SpringField", "EEUU"));
		// e.setDireccion(new Direccion(1L,"Calle Falsa, 123",
		// "SpringField","SpringField","EEUU"));
		// Empleado e2 = new Empleado(11L, "Zafra", "Jose", LocalDate.of(1988,
		// 4, 23));

		manager.getTransaction().begin();
		manager.persist(e);
		manager.getTransaction().commit();
		manager.close();
	}

	@SuppressWarnings("unchecked")
	private static void imprimirTodo() {
		EntityManager manager = emf.createEntityManager();
		List<Empleado> empleados = (List<Empleado>) manager.createQuery("FROM Empleado").getResultList();
		System.out.println(" en esta base de datos hay " + empleados.size() + " Empleados ");
		empleados.forEach(System.out::println);
		manager.close();
	}

	private static void find() {
		EntityManager manager = emf.createEntityManager();
		Empleado e = manager.find(Empleado.class, 11L);
		System.out.println(e);
	}

	private static void update() {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Empleado e = manager.find(Empleado.class, 13L);
		e.setApellido("Guevara");
		manager.merge(e);
		manager.getTransaction().commit();
		manager.close();
	}

	private static void crearDatosAutorLibros() {

		EntityManager manager = emf.createEntityManager();

		manager.getTransaction().begin();
		
		//EJEMPLO DE RELACION DE AUTOR - LIBRO
		/*Autor autor = new Autor(1L, "Jose Zafra", "Peruano");
		Autor autor2 = new Autor(2L, "Vicky Mio", "Peruana");
		Autor autor3 = new Autor(3L, "Juan Diego Meiggs", "Peruano");

		manager.persist(autor);
		manager.persist(autor2);
		manager.persist(autor3);

		manager.persist(new Libro(1L, "Java Enterprise", autor));
		manager.persist(new Libro(2L, "Programar en Cobol es Divertido", autor3));
		manager.persist(new Libro(3L, "Programar en python es divertido", autor2));
		*/
		
		
		
		//EJEMPLO DE RELACION DE LIBRO - AUTOR
		Libro l1 = new  Libro();
		l1.setId(17L);
		l1.setTitulo("TEST MY CODE BEST");
		
		

		Autor a1 = new Autor(); 
		a1.setId(121L);
		a1.setNacionalidad("DOMINICAN");
		a1.setNombre("JUNIO VERA");
		
		try {
			a1.setLibros(Arrays.asList(l1));
			
			manager.persist(a1);
			manager.persist(l1);
			/*a1.addLibros(l1);
			manager.persist(l1);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		manager.getTransaction().commit();
		manager.close();

	}

	private static void imprimiendoAutores() {

		EntityManager manager = emf.createEntityManager();
		Autor autor = manager.find(Autor.class, 1L);
		List<Libro> libros = autor.getLibros();
		libros.forEach(System.out::println);

		manager.close();

	}
}
