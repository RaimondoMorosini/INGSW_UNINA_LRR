package api.dieti2024.dto;

import api.dieti2024.dto.asta.InfoProdottoPerCreazioneDTO;
import api.dieti2024.dto.asta.InputAstaDTO;

@Deprecated
public record DatiCreazioneAstaDTODEPRECATO(
        InputAstaDTO datiAsta,
        String datiExtraJson,
        InfoProdottoPerCreazioneDTO datiProdotto) {

}
