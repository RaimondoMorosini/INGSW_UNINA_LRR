package api.dieti2024.controller;

import api.dieti2024.dto.asta.CreaAstaDTO;
import api.dieti2024.dto.asta.ricerca.FiltroDto;
import api.dieti2024.dto.asta.ricerca.InfoDatiAstaDTO;
import api.dieti2024.service.Asta.AstaFacadeService;
import api.dieti2024.service.Asta.AstaService;
import api.dieti2024.service.Asta.ProdottoService;
import api.dieti2024.util.ImageContainerUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping
public class ProdottoAstaController {

    final
    ProdottoService prodottoService;
    final
    AstaService astaService;
    final
    AstaFacadeService astaFacadeService;
    final
    ImageContainerUtil imageContainerUtil;

    public ProdottoAstaController(ProdottoService prodottoService, AstaService astaService, AstaFacadeService astaFacadeService, ImageContainerUtil imageContainerUtil) {
        this.prodottoService = prodottoService;
        this.astaService = astaService;
        this.astaFacadeService = astaFacadeService;
        this.imageContainerUtil = imageContainerUtil;
    }

    @PostMapping("public/asta/getAllAsteDeprecato")
    public ResponseEntity< List<InfoDatiAstaDTO>> getAllAste(@RequestBody FiltroDto filtroDto){
        try {
            return ResponseEntity.ok( astaService.getAllAstaProdotto(filtroDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("public/asta/getNumeroAsteDeprecato")
    public ResponseEntity<Integer> getNumeroTotaleDiAstePerRicerca(@RequestBody FiltroDto filtroDto){
        try {
            return ResponseEntity.ok( astaService.getNumeroTotaleDiAstePerRicerca(filtroDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/asta/creaasta")
    public ResponseEntity<String> addProdottoAsta(@RequestBody CreaAstaDTO datiPerCreazioneDtoInput) {
            astaFacadeService.creaAsta(datiPerCreazioneDtoInput);
            return ResponseEntity.ok().build(); // Restituisce 200 OK senza corpo
    }


    @PostMapping("public/asta/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Errore: Nessun file selezionato";
        }
        imageContainerUtil.uploadImage(file,file.getOriginalFilename());

        return "Immagine caricata con successo! ";

    }@PostMapping(value = "public/asta/uploadImages" )
    public String uploadImage(@RequestParam("file") List<MultipartFile> files) {
        if (files.isEmpty()) {
            return "Errore: Nessun file selezionato";
        }
        StringBuilder message= new StringBuilder();
        for(MultipartFile file:files){
            if (!file.isEmpty()){
            imageContainerUtil.uploadImage(file,file.getOriginalFilename());
                message.append("Immagine caricata con successo!\n");
            }
            else message.append("Errore: file non trovato\n");
        }
        return message.toString();
    }

}
