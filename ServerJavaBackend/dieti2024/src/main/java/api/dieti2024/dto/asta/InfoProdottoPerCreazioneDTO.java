package api.dieti2024.dto.asta;

import api.dieti2024.model.Prodotto;

import java.util.List;

public record InfoProdottoPerCreazioneDTO(
        String nomeProdotto,
        String descrizioneProdotto,
        List<String> immagini,
        String categoriaProdotto) {
    public Prodotto toProdotto() {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(nomeProdotto);
        prodotto.setDescrizione(descrizioneProdotto);
        prodotto.setImmagini(immagini);
        prodotto.setCategoria(categoriaProdotto);
        return prodotto;
    }
}
