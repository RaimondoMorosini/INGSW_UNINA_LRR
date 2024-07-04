package api.dieti2024.service.Asta;

import api.dieti2024.dto.asta.CreaAstaDTO;
import api.dieti2024.dto.asta.InputAstaDTO;
import api.dieti2024.dto.asta.ricerca.FiltroDto;
import api.dieti2024.dto.asta.ricerca.InfoDatiAstaDTO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.repository.AstaProdottoRepository;
import api.dieti2024.util.ControllerRestUtil;
import api.dieti2024.util.ImageContainerUtil;
import api.dieti2024.util.TipoAsta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AstaFacadeService {
    @Autowired
    ProdottoService prodottoService;
    @Autowired
    AstaService astaService;
    @Autowired
    DatiAstaIngleseService datiAstaIngleseService;
    @Autowired
    AstaProdottoRepository astaProdottoRepository;
    @Autowired
    ImageContainerUtil imageContainerUtil;

    @Transactional
    public void creaAsta(CreaAstaDTO datiInput) {
            checkDatiInputValidi(datiInput);
            int idProdotto = prodottoService.salvaProdotto(datiInput.datiProdotto());
            int idAsta=astaService.salvaAsta(datiInput.datiAsta(), idProdotto);
            saveDatiExtraAsta(datiInput.datiAsta(),idAsta);
            salvaImmaginiProdotto(datiInput.datiProdotto().immagini(),idAsta);
        System.out.println("Asta creata con successo");
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
        for (int i = 0; i < immagini.size(); i++) {
            path = path+"/"+countImmaginiSalvateConSuccesso;
            if (uploadImage(immagini.get(i),path)){
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






}
