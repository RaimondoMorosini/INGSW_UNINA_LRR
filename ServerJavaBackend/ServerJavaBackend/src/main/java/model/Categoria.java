package model;

import java.util.List;

public class Categoria {

    private int id;
    private String nome;

    private Categoria padre;
    private List<Categoria> figli;

    public Categoria(int id, String nome, Categoria padre, List<Categoria> figli) {
        this.id = id;
        this.nome = nome;
        this.padre = padre;
        this.figli = figli;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public Categoria getPadre() {
        return padre;
    }
    public  List<Categoria> getFigli() {
        return figli;
    }
}
