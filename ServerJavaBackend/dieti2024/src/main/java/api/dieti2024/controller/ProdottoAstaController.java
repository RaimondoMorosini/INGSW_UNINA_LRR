package api.dieti2024.controller;

import api.dieti2024.dto.asta.CreaAstaDTO;
import api.dieti2024.dto.asta.ImmagineAstaDTO;
import api.dieti2024.dto.asta.ricerca.InfoDatiAstaDTO;
import api.dieti2024.service.asta.AstaFacadeService;
import api.dieti2024.util.ImageContainerUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping
public class ProdottoAstaController {

    final
    AstaFacadeService astaFacadeService;
    final
    ImageContainerUtil imageContainerUtil;

    public ProdottoAstaController(AstaFacadeService astaFacadeService, ImageContainerUtil imageContainerUtil) {
        this.astaFacadeService = astaFacadeService;
        this.imageContainerUtil = imageContainerUtil;
    }


    @PostMapping("/asta/creaAsta")
    public ResponseEntity<String> addProdottoAsta(@RequestBody CreaAstaDTO datiPerCreazioneDtoInput) {
            int idAsta= astaFacadeService.creaAsta(datiPerCreazioneDtoInput);
            String json="{\"message\":\"Asta creata con successo\",\"idAsta\":"+idAsta+"}";
            return ResponseEntity.ok(json);

    }
    @PostMapping("asta/AggiornaImgAsta")
    public ResponseEntity<String> aggiornaImgAsta(@ModelAttribute ImmagineAstaDTO immagineAstaDTO) {
        try {
            String msg=astaFacadeService.aggiornaImmaginiAsta(immagineAstaDTO);
            return ResponseEntity.ok(msg);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("public/prodottoAsta/{idAsta}")
    public ResponseEntity<InfoDatiAstaDTO> getInfoProdottoAsta(@PathVariable int idAsta) {
            InfoDatiAstaDTO dati= astaFacadeService.getInfoSingolaAsta(idAsta);
            return ResponseEntity.ok(dati);
    }

    //get dati extra delle asta
    @GetMapping("public/prodottoAsta/extra/{idAsta}")
    public ResponseEntity<String> getExtraInfoProdottoAsta(@PathVariable int idAsta) {
        String dati= astaFacadeService.getExtraInfoSingolaAsta(idAsta);
        return ResponseEntity.ok(dati);
    }

}
