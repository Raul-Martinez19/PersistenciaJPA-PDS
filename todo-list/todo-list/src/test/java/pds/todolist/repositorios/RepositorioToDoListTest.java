package pds.todolist.repositorios;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pds.modelo.ToDoList;
import pds.modelo.ToDoListFija;

public class RepositorioToDoListTest {
    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    public static void setupClass() {
        emf = Persistence.createEntityManagerFactory("ejemplo"); // Nombre correcto
    }

    @Test
    public void testCrearLista() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        ToDoListFija lista = new ToDoListFija("Compras", 3);
        em.persist(lista);

        em.getTransaction().commit();

        ToDoList listaRecuperada = em.find(ToDoList.class, lista.getId());
        assertNotNull(listaRecuperada);
        em.close();
    }
}