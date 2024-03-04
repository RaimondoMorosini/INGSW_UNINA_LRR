package lrr.server.modello;

import java.time.Instant;

@SuppressWarnings("ALL")
public class ProdottoInAsta {
    private String nomeProdotto;
    private String descrizione;
    private String immagini;
    private float baseAsta;
    private Instant dataScadenza;
    private Instant dataInizio;
    private float prezzoAttuale;


    public  ProdottoInAsta(String nomeProdotto, String descrizione, String immagini, float baseAsta, Instant dataScadenza, Instant dataInizio, float prezzoAttuale){
        this.nomeProdotto = nomeProdotto;
        this.descrizione = descrizione;
        this.immagini = immagini;
        this.baseAsta = baseAsta;
        this.dataScadenza = dataScadenza;
        this.dataInizio = dataInizio;
        this.prezzoAttuale = prezzoAttuale;
    }

}
