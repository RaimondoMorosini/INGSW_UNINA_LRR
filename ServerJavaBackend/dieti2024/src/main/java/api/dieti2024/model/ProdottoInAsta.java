package api.dieti2024.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "prodotto_in_asta")
public class ProdottoInAsta {

    @Id
    @Column(name = "prodotto_in_asta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome_prodotto")
    private String nomeProdotto;

    @Column(name = "tipo")
    private String tipoAsta;

    @Column(name = "base_asta")
    private float baseAsta;

    @Column(name = "prezzo_attuale")
    private float PrezzoAttuale;

    @Column(name = "immagine")
    private String pathImmagine;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "data_inizio")
    private ZonedDateTime dataInizio;

    @Column(name = "data_scadenza")
    private ZonedDateTime dataScadenza;

    @Column(name = "categoria")
    private String categoria;
}
