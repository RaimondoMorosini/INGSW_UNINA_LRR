package api.dieti2024.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class OffertaAstaIngleseDTO {

    String emailUtente;

    double prezzoProposto;

    long tempoOfferta;

    public OffertaAstaIngleseDTO(String utente, double prezzoOfferto, long tempo) {
        this.emailUtente = utente;
        this.prezzoProposto = prezzoOfferto;
        this.tempoOfferta = tempo;
    }

}
