package api.dieti2024.service;

import api.dieti2024.dto.DatiProfiloUtenteDTO;
import api.dieti2024.dto.UserDetailsDto;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Utente;
import api.dieti2024.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class utenteService {

    @Autowired
    private UserRepository utenteRepository;

    public UserDetailsDto getUserDetails(String email) {

        Utente utenteModel =getUtenteByEmail(email);
        return UserDetailsDto.fromUserModel(utenteModel);
    }

    /*
     * Metodo per ottenere un utente dal repository
     * @param email email dell'utente
     * @return utente
     * @throws ApiException se l'utente non è presente
     */
    private Utente getUtenteByEmail(String email) {
        Utente utente = utenteRepository.findById(email).orElseThrow(() -> new ApiException("Utente non trovato", HttpStatus.NOT_FOUND));
        return utente;
    }

    public DatiProfiloUtenteDTO getDatiProfilo(String email){
        Utente utente = getUtenteByEmail(email);
        DatiProfiloUtenteDTO datiProfiloUtenteDTO = DatiProfiloUtenteDTO.fromUserModel(utente);
        return datiProfiloUtenteDTO;
    }
    public void updateDatiProfilo(String email,  DatiProfiloUtenteDTO datiProfiloUtenteDTO){
        try {
            Utente utente = getUtenteByEmail(email);

            utente.setNome(datiProfiloUtenteDTO.nome());
            utente.setCognome(datiProfiloUtenteDTO.cognome());
            utente.setBio(datiProfiloUtenteDTO.bio());
            utente.setSiti(datiProfiloUtenteDTO.siti());
            utente.setAreaGeografica(datiProfiloUtenteDTO.areaGeografica());
            utenteRepository.save(utente);
        }catch (ApiException e) {
            throw e;
        }catch (Exception e){
            throw new ApiException("Errore interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
