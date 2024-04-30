package api.dieti2024.dto.asta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InfoDatiAstaDTO {

    private int idProdottoAsta;
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
}
