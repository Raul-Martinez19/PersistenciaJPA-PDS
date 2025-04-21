package pds.todolist.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import pds.modelo.ToDoItem;
import pds.modelo.ToDoList;

public class RepositorioToDoList {
	private EntityManagerFactory emf;
	private EntityManager em;

	public RepositorioToDoList() {
		this.emf = Persistence.createEntityManagerFactory("todolistPU");
		this.em = emf.createEntityManager();
	}

	public void add(ToDoList lista) {
		em.getTransaction().begin();
		em.persist(lista);
		em.getTransaction().commit();
	}

	public void add(ToDoList lista, ToDoItem tarea) {
		em.getTransaction().begin();
		lista.addItem(tarea);
		tarea.setLista(lista); // Establece la relación bidireccional
		em.merge(lista);
		em.getTransaction().commit();
	}

	public List<ToDoList> getToDoLists() {
		return em.createQuery("SELECT l FROM ToDoList l", ToDoList.class).getResultList();
	}

	public void remove(ToDoList lista) {
		em.getTransaction().begin();
		em.remove(em.merge(lista));
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
}F