package api.dieti2024.service.Asta;

import api.dieti2024.dto.asta.InfoDatiAstaDTO;
import api.dieti2024.model.Prodotto;
import api.dieti2024.repository.ProdottoRepository;
import api.dieti2024.util.TipoPermesso;
import api.dieti2024.dto.asta.InputAstaDTO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Asta;
import api.dieti2024.repository.AstaRepository;
import api.dieti2024.repository.PermessoRepository;
import api.dieti2024.util.CalendarioUtil;
import api.dieti2024.util.ControllerRestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AstaService {

    @Autowired
    private AstaRepository astaRepository;

    @Autowired
    private PermessoRepository permessoRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    public void checkDatiInputi(InputAstaDTO inputAstaDTO) {

        if (inputAstaDTO == null) throw new ApiException("Dati asta mancanti", HttpStatus.BAD_REQUEST);

        if (inputAstaDTO.baseAsta() <= 0) throw new ApiException("Base asta non valida", HttpStatus.BAD_REQUEST);

        if (!CalendarioUtil.verificaIntevalloDate(inputAstaDTO.dataInizio(), inputAstaDTO.dataScadenza())) throw new ApiException("Tempo asta non valido", HttpStatus.BAD_REQUEST);

        if (!checkPermessoDiCreazione(inputAstaDTO.tipoAsta())) throw new ApiException("Permesso di creazione asta non valido", HttpStatus.FORBIDDEN);

    }

    private boolean checkPermessoDiCreazione(String tipoAsta) {
        String emailUtente = ControllerRestUtil.getEmailOfUtenteCorrente();
        List<String> permessiUtente = permessoRepository.getPermessiUtente(emailUtente);
        return TipoPermesso.haPermessoDiCreazione(tipoAsta, permessiUtente) ;
    }

    public int salvaAsta (InputAstaDTO inputAstaDTO,int idProdotto){
        String emailUtenteCreatore = ControllerRestUtil.getEmailOfUtenteCorrente();
        Asta asta = inputAstaDTO.toAsta(idProdotto, emailUtenteCreatore);
        return astaRepository.save(asta).getId();
    }

    public List<InfoDatiAstaDTO> getAllAstaProdotto(){

        List<InfoDatiAstaDTO> listaDaRestituire = new ArrayList<>();

        List<Asta> tutteLeAste = astaRepository.findAll();

        for(Asta asta: tutteLeAste){

            int idProdotto = asta.getIdProdotto();

            Prodotto prodotto = prodottoRepository.findById(idProdotto).get();

            InfoDatiAstaDTO infoAsta = new InfoDatiAstaDTO(asta.getIdProdotto(),asta.getBaseAsta(),asta.getPrezzoAttuale(),asta.getDataScadenza(),asta.getDataInizio(),
                    asta.getTipoAsta(),asta.getEmailUtenteCreatore(),prodotto.getNome(),prodotto.getImmagini(),prodotto.getDescrizione(),prodotto.getCategoria());

            listaDaRestituire.add(infoAsta);
        }

        return listaDaRestituire;
    }

}

