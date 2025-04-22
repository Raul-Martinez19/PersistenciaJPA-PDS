package pds.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("fija")
public class ToDoListFija extends ToDoList {

	private final int numMaximoItems;

	public ToDoListFija(String titulo, int numMaximoItems) {
		super(titulo);
		this.numMaximoItems = numMaximoItems;
	}

	public int getNumMaximoItems() {
		return numMaximoItems;
	}

	@Override
	public boolean addItem(ToDoItem item) {
		if (items.size() > numMaximoItems)
			return false;
		return super.addItem(item);
	}

	@Override
	public List<ToDoItem> getTareasEnOrden() {
		return new ArrayList<>(items);
	}
}
