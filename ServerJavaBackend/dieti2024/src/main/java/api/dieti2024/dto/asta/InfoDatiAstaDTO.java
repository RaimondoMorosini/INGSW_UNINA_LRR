package api.dieti2024.dto.asta;

import lombok.Data;

import java.util.List;

@Data
public class InfoDatiAstaDTO {

    private double baseAsta;
    private double prezzoAttuale;
    private long dataScadenza;
    private long dataInizio;
    private String tipoAsta;
    private String emailUtenteCreatore;
    private String nome;
    private List<String> immagini;
    private String descrizione;
    private String categoria;

    public InfoDatiAstaDTO(double baseAsta, double prezzoAttuale, long dataScadenza, long dataInizio, String tipoAsta,
                           String creatore, String nome, List<String> immagini, String descrizione, String categoria){

        this.baseAsta = baseAsta;

        this.prezzoAttuale = prezzoAttuale;

        this.dataScadenza = dataScadenza;

        this.dataInizio = dataInizio;

        this.tipoAsta = tipoAsta;

        this.emailUtenteCreatore = creatore;

        this.nome = nome;

        this.immagini = immagini;

        this.descrizione = descrizione;

        this.categoria = categoria;

    }
}
