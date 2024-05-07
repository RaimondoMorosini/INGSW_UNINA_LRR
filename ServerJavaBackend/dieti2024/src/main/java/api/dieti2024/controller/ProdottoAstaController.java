package api.dieti2024.controller;

import api.dieti2024.dto.asta.CreaAstaDTO;
import api.dieti2024.dto.asta.ricerca.FiltroDto;
import api.dieti2024.dto.asta.ricerca.InfoDatiAstaDTO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.service.Asta.AstaFacadeService;
import api.dieti2024.service.Asta.AstaService;
import api.dieti2024.service.Asta.ProdottoService;
import api.dieti2024.util.ImageContainerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping
public class ProdottoAstaController {

    @Autowired
    ProdottoService prodottoService;
    @Autowired
    AstaService astaService;

    @Autowired
    AstaFacadeService astaFacadeService;
    @Autowired
    ImageContainerUtil imageContainerUtil;

    @PostMapping("public/asta/getAllAste")
    public List<InfoDatiAstaDTO> getAllAste(@RequestBody FiltroDto filtroDto){
        try {
            return astaService.getAllAstaProdotto(filtroDto);
        }catch (Exception e){
            return null;
        }
    }



    @PostMapping("/asta/creaasta")
    public ResponseEntity addProdottoAsta(@RequestBody CreaAstaDTO datiPerCreazioneDtoInput){
        try {
            astaFacadeService.creaAsta(datiPerCreazioneDtoInput);
            return ResponseEntity.ok().build();
        }catch(ApiException e){
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno al server");
        }


    }

    @PostMapping("public/asta/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Errore: Nessun file selezionato";
        }
            imageContainerUtil.uploadImage(file,file.getOriginalFilename());
            return "Immagine caricata con successo!";
    }

}
