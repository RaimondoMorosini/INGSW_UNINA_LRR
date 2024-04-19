package api.dieti2024.dto.asta;

import api.dieti2024.model.Prodotto;
import api.dieti2024.model.ValoreSpecificoPerProdotto;

import java.util.List;

public record InfoProdottoPerCreazioneDTO(
        String nomeProdotto,
        String descrizioneProdotto,
        List<String> immagini,
        String categoriaProdotto,
        List<CoppiaCaratteristicaValoreDTO> caratteristicheProdotto) {
    public Prodotto toProdotto() {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(nomeProdotto);
        prodotto.setDescrizione(descrizioneProdotto);
        prodotto.setImmagini(immagini);
        prodotto.setCategoria(categoriaProdotto);
        return prodotto;
    }

    public List<ValoreSpecificoPerProdotto> toListValoreSpecificoPerProdotto(int idProdotto) {
        List<ValoreSpecificoPerProdotto> valoriSpecifici = List.of();
        for (CoppiaCaratteristicaValoreDTO coppia : caratteristicheProdotto) {
            ValoreSpecificoPerProdotto valore = new ValoreSpecificoPerProdotto();
            valore.setIdProdotto(idProdotto);
            valore.setIdCaratteristica(coppia.idCaratteristica());
            valore.setValore(coppia.valore());
            valoriSpecifici.add(valore);
        }
        return valoriSpecifici;
    }
}
