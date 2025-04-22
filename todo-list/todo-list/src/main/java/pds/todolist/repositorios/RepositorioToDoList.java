package pds.todolist.repositorios;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pds.modelo.ToDoItem;
import pds.modelo.ToDoList;

public class RepositorioToDoList {
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo");
	private EntityManager em = emf.createEntityManager();

	public void add(ToDoList lista) {
		try {
			em.getTransaction().begin();
			em.persist(lista);
			em.getTransaction().commit();
		} catch (Exception ex) {
			// Gestión de excepciones, rollback, etc.
		} finally {
			// Siempre cerrar el entity manager
			if (em != null && em.isOpen())
				em.close();
		}
	}

	public void add(ToDoList lista, ToDoItem tarea) {
		em.getTransaction().begin();
		lista.addItem(tarea);
		tarea.setLista(lista); // Bidireccionalidad
		em.persist(tarea);
		em.getTransaction().commit();
	}

	public List<ToDoList> getToDoLists() {
		return em.createQuery("SELECT l FROM ToDoList l", ToDoList.class).getResultList();
	}

	public void remove(ToDoList lista) {
		em.getTransaction().begin();
		em.remove(lista);
		em.getTransaction().commit();
	}

	public void remove(ToDoList actual, ToDoItem item) {
		em.getTransaction().begin();
		actual.removeItem(item);
		em.merge(actual);
		em.getTransaction().commit();
	}

	public void update(ToDoList lista, ToDoItem item) {
		em.getTransaction().begin();
		em.merge(item);
		em.getTransaction().commit();
	}

	public void close() {
		em.close();
		emf.close();
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}