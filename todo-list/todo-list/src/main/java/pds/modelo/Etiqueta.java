package pds.modelo;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Etiqueta implements Comparable<Etiqueta> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String valor;

    public Etiqueta() {
    } // Constructor vacío para JPA

    public Etiqueta(String valor) {
        this.valor = valor;
    }

    // Getters y Setters
    public String getValor() {
        return valor;
    }

    @Override
    public int compareTo(Etiqueta o) {
        return this.valor.compareTo(o.valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Etiqueta etiqueta = (Etiqueta) obj;
        return Objects.equals(valor, etiqueta.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}