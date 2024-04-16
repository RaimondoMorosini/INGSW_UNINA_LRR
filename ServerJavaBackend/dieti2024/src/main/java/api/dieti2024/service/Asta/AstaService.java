package api.dieti2024.service.Asta;

import api.dieti2024.dto.asta.InputAstaDTO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.repository.AstaRepository;
import api.dieti2024.util.CalendarioUtil;
import api.dieti2024.util.ControllerRestUtil;
<<<<<<< Updated upstream
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
        try{
            if(!CalendarioUtil.verificaIntevalloDate(inputAstaDTO.dataInizio(), inputAstaDTO.dataScadenza()) ){
>>>>>>> Stashed changes
        try {
            if (!CalendarioUtil.verificaIntevalloDate(inputAstaDTO.dataInizio(), inputAstaDTO.dataScadenza())) {
                throw new ApiException("Tempo asta non valido", HttpStatus.BAD_REQUEST);
            }
            if (inputAstaDTO.baseAsta() <= 0) {
                throw new ApiException("Base asta non valida", HttpStatus.BAD_REQUEST);
            }

<<<<<<< Updated upstream
=======
        }catch (Exception e){
>>>>>>> Stashed changes
        } catch (Exception e) {
            throw new ApiException("Errore interno al server", HttpStatus.INTERNAL_SERVER_ERROR);

        }
<<<<<<< Updated upstream
=======

        //Controlla se l'utente ha il permesso di creare l'asta
        boolean haILPermessoDiCreazione;
        ControllerRestUtil controllerRestUtil = new ControllerRestUtil();
        String emailUtente = controllerRestUtil.getEmailOfUtenteCorrente();

        switch (inputAstaDTO.tipoAsta()){

            case "asta_inglese":
                //gestisci
                break;
            case "asta_silenziosa":
                //gestisci
                break;
            case "asta_inversa":
                //gestisci
                break;

            default:
                haILPermessoDiCreazione = false;
                break;
        }
>>>>>>> Stashed changes
    }

    public void salvaAsta(InputAstaDTO inputAstaDTO, int idProdotto) {
        String emailUtenteCreatore = ControllerRestUtil.getEmailOfUtenteCorrente();
            astaRepository.save(inputAstaDTO.toAsta(idProdotto, emailUtenteCreatore));
    }
}