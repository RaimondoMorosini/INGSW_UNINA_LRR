package api.dieti2024.service.asta;

import api.dieti2024.dto.asta.CreaAstaDTO;
import api.dieti2024.dto.asta.ImmagineAstaDTO;
import api.dieti2024.dto.asta.InputAstaDTO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.repository.AstaProdottoRepository;
import api.dieti2024.util.ControllerRestUtil;
import api.dieti2024.util.ImageContainerUtil;
import api.dieti2024.util.TipoAsta;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AstaFacadeService {
    final
    ProdottoService prodottoService;
    final
    AstaService astaService;
    final
    DatiAstaIngleseService datiAstaIngleseService;
    final
    AstaProdottoRepository astaProdottoRepository;
    final
    ImageContainerUtil imageContainerUtil;

    public AstaFacadeService(ProdottoService prodottoService, AstaService astaService, DatiAstaIngleseService datiAstaIngleseService, AstaProdottoRepository astaProdottoRepository, ImageContainerUtil imageContainerUtil) {
        this.prodottoService = prodottoService;
        this.astaService = astaService;
        this.datiAstaIngleseService = datiAstaIngleseService;
        this.astaProdottoRepository = astaProdottoRepository;
        this.imageContainerUtil = imageContainerUtil;
    }

    @Transactional
    public int creaAsta(CreaAstaDTO datiInput) {
            checkDatiInputValidi(datiInput);
            int idProdotto = prodottoService.salvaProdotto(datiInput.datiProdotto());
            int idAsta=astaService.salvaAsta(datiInput.datiAsta(), idProdotto);
            saveDatiExtraAsta(datiInput.datiAsta(),idAsta);
            salvaImmaginiProdotto(datiInput.datiProdotto().immagini(),idAsta);
            return idAsta;
    }

    private void saveDatiExtraAsta(InputAstaDTO inputAstaDTO,int idAsta) {
        if(!TipoAsta.INGLESE.equals(inputAstaDTO.tipoAsta())) return;
        datiAstaIngleseService.salvaDatiAstaInglese(inputAstaDTO, idAsta);
    }

    private void salvaImmaginiProdotto(List<MultipartFile> immagini, int idAsta) {
        //nome utente /idasta/numeroimmagine
        String identificativoUtente = ControllerRestUtil.getEmailOfUtenteCorrente();
        String path = identificativoUtente+"/"+idAsta;
        List<String> pathImmaginiSalvate = new ArrayList<>();
        int countImmaginiSalvateConSuccesso=0;
        for (MultipartFile multipartFile : immagini) {

            if (uploadImage(multipartFile, path + "/" + countImmaginiSalvateConSuccesso)) {
                countImmaginiSalvateConSuccesso++;
                pathImmaginiSalvate.add(path);
            }
        }
        astaProdottoRepository.updatePathImmagini(idAsta,pathImmaginiSalvate);
    }
    public boolean uploadImage(MultipartFile file, String path) {
        try {
            imageContainerUtil.uploadImage(file,path);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void checkDatiInputValidi(CreaAstaDTO datiInput) {
        if (datiInput == null) {
            throw new ApiException("Dati input mancanti", HttpStatus.BAD_REQUEST);
        }
        //TODO aggiungere controlli per vedere se utente ha il permesso di creare asta
        astaService.checkDatiInputi(datiInput.datiAsta());
        prodottoService.checkDatiInputi(datiInput.datiProdotto());

    }


    public String aggiornaImmaginiAsta(ImmagineAstaDTO immagineAstaDTO) {
        List<MultipartFile> files = immagineAstaDTO.files();
        int idAsta = immagineAstaDTO.idAsta();
        String identificativoUtente = ControllerRestUtil.getEmailOfUtenteCorrente();
        String PropritarioAsta = astaService.getIdUtentebyIdAsta(idAsta);
        if (!identificativoUtente.equals(PropritarioAsta)) {
            throw new ApiException("Non hai i permessi per modificare quest'asta", HttpStatus.FORBIDDEN);
        }
        StringBuilder message = new StringBuilder();
        message.append("Immagini caricate con successo! \necco i link:\n");
        List<String> links = new ArrayList<>();
        int counterErroriCaricamentoImg=0;
        for(int i=0;i<files.size();i++){

            try{
            String link=imageContainerUtil.uploadImage(files.get(i),
                    idAsta+"-"+(i-counterErroriCaricamentoImg)+"."+files.get(i).getOriginalFilename().split("\\.")[1]);
            message.append(link).append("\n");
            links.add(link);
            }catch (Exception e){
                counterErroriCaricamentoImg++;
                message.append("Errore: file non caricato\n");
            }

        }

        prodottoService.aggiornaPathImmagini(idAsta,links);
        return message.toString();

    }
}
