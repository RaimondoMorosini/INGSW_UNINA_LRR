package api.dieti2024.dto.asta;


public record InputAstaDTO(
        double baseAsta,
        long dataScadenza,
        long dataInizio,
        String tipoAsta,
        String datiExtraJson
        ) {
}
