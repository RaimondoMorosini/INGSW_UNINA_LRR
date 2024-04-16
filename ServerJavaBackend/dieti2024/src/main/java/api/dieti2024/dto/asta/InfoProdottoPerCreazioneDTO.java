package api.dieti2024.dto.asta;

import java.util.List;

public record InfoProdottoPerCreazioneDTO(
        String nomeProdotto,
        String descrizioneProdotto,
        List<String> immagini,
        String categoriaProdotto) {
}
