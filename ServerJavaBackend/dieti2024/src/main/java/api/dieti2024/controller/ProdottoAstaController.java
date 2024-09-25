package api.dieti2024.controller;

import api.dieti2024.dto.MultipartfileDTO;
import api.dieti2024.dto.asta.CreaAstaDTO;
import api.dieti2024.dto.asta.ImmagineAstaDTO;
import api.dieti2024.dto.asta.ricerca.FiltroDto;
import api.dieti2024.dto.asta.ricerca.InfoDatiAstaDTO;
import api.dieti2024.service.asta.AstaFacadeService;
import api.dieti2024.service.asta.AstaService;
import api.dieti2024.service.asta.ProdottoService;
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

    @PostMapping("public/asta/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Errore: Nessun file selezionato";
        }
        imageContainerUtil.uploadImage(file,file.getOriginalFilename());

        return "Immagine caricata con successo! ";


    }@PostMapping(value = "public/asta/uploadImages" )
    public String uploadImage(@ModelAttribute MultipartfileDTO input) {
        List<MultipartFile> files = input.files();
        System.out.println("id asttttttttttttttttttttaaaaaaaaaaaaaa: "+input.idAsta());
        if (files.isEmpty()) {
            return "Errore: Nessun file selezionato";
        }
        StringBuilder message= new StringBuilder();
        for(MultipartFile file:files){
            if (!file.isEmpty()){
            imageContainerUtil.uploadImage(file,file.getOriginalFilename());
            String link = "https://dieti24.blob.core.windows.net/upload/"+file.getOriginalFilename();
                message.append("Immagine caricata con successo! "+link +"\n");
            }
            else message.append("Errore: file non trovato\n");
        }

        return message.toString();
    }

}
