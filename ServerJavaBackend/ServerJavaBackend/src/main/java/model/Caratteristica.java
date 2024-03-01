package model;

import java.util.List;

public class Caratteristica {
    private int id;
    private String tipo;

    private List<String> opzioniSelezionabili;

    private  Categoria categoriaAssociata;

    public Caratteristica(int id, String tipo, List<String> opzioniSelezionabili, Categoria categoriaAssociata) {
        this.id = id;
        this.tipo = tipo;
        this.opzioniSelezionabili = opzioniSelezionabili;
        this.categoriaAssociata = categoriaAssociata;
    }

}
