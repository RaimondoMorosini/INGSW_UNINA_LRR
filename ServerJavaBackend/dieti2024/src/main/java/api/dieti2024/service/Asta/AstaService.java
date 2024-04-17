package api.dieti2024.service.Asta;

import api.dieti2024.dto.asta.InputAstaDTO;
import api.dieti2024.exceptions.ApiException;
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

    ControllerRestUtil controllerRestUtil = new ControllerRestUtil();

    @Autowired
    private AstaRepository astaRepository;

    @Autowired
    private PermessoRepository permessoRepository;

    public void checkDatiInputi(InputAstaDTO inputAstaDTO) {

        if (inputAstaDTO == null) {
            throw new ApiException("Dati asta mancanti", HttpStatus.BAD_REQUEST);
        }

        if (!CalendarioUtil.verificaIntevalloDate(inputAstaDTO.dataInizio(), inputAstaDTO.dataScadenza())) {

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

        //----------------------Controlla se l'utente ha il permesso di creare l'asta---------------------------------------------


        boolean haILPermessoDiCreazione;
        String emailUtente = controllerRestUtil.getEmailOfUtenteCorrente();
        List<String> permessiUtente = permessoRepository.getPermessiUtente(emailUtente);

        switch (inputAstaDTO.tipoAsta()) {

            case "asta_inglese":
                haILPermessoDiCreazione = permessiUtente.contains("crea_asta_inglese");
                break;
            case "asta_silenziosa":
                haILPermessoDiCreazione = permessiUtente.contains("crea_asta_silenziosa");
                break;
            case "asta_inversa":
                haILPermessoDiCreazione = permessiUtente.contains("crea_asta_inversa");
                break;

            default:
                haILPermessoDiCreazione = false;
                break;
        }

        if(!haILPermessoDiCreazione)
            throw new ApiException("Non ha il permesso di creare l'asta", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public void salvaAsta(InputAstaDTO inputAstaDTO, int idProdotto) {
        String emailUtenteCreatore = ControllerRestUtil.getEmailOfUtenteCorrente();
        astaRepository.save(inputAstaDTO.toAsta(idProdotto, emailUtenteCreatore));
    }

}

