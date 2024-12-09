package api.dieti2024.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "dati_venditori")
public class DatiVenditori {

    @Id
    @Column(name = "nome_utente", nullable = false, length = 32)
    private String nomeUtente;

    @Column(name = "partita_iva", nullable = false, length = 11)
    private String partitaIva;

    @Column(name = "codice_fiscale", nullable = false, length = 16)
    private String codiceFiscale;

    @Column(name = "nome_azienda", nullable = false, length = 64)
    private String nomeAzienda;

    @Column(name = "numero_telefono", nullable = false, length = 16)
    private String numeroTelefono;
}

