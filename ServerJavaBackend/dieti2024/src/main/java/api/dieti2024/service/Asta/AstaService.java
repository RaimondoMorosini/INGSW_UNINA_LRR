package api.dieti2024.service.Asta;

import api.dieti2024.dto.asta.InputAstaDTO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.repository.AstaRepository;
import api.dieti2024.util.CalendarioUtil;
import api.dieti2024.util.ControllerRestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AstaService {

    @Autowired
    private AstaRepository astaRepository;
    public void checkDatiInputi(InputAstaDTO inputAstaDTO) {
        if (inputAstaDTO == null) {
            throw new ApiException("Dati asta mancanti", HttpStatus.BAD_REQUEST);
        }
        try {
            if (!CalendarioUtil.verificaIntevalloDate(inputAstaDTO.dataInizio(), inputAstaDTO.dataScadenza())) {
                throw new ApiException("Tempo asta non valido", HttpStatus.BAD_REQUEST);
            }
            if (inputAstaDTO.baseAsta() <= 0) {
                throw new ApiException("Base asta non valida", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            throw new ApiException("Errore interno al server", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public void salvaAsta(InputAstaDTO inputAstaDTO, int idProdotto) {
        String emailUtenteCreatore = ControllerRestUtil.getEmailOfUtenteCorrente();
            astaRepository.save(inputAstaDTO.toAsta(idProdotto, emailUtenteCreatore));
    }
}