package api.dieti2024.dto.asta;

public record AnteprimaAstaDTO(
        int idProdotto,
        String urlCopertinaImmagine,
        String nomeProdotto,
        String CategoriaPrincipale,
        String TipoAsta,
        double prezzoAttuale,
        long dataScadenzaUnixTime
) {
}
