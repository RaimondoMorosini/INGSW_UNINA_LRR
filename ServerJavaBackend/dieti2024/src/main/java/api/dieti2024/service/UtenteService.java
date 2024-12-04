package api.dieti2024.service;

import api.dieti2024.dto.ModificaProfiloDTO;
import api.dieti2024.dto.auth.UserDetailsDto;
import api.dieti2024.dto.utente.ProfiloUtentePublicoDTO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Utente;
import api.dieti2024.repository.UserRepository;
import api.dieti2024.util.ImageContainerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UtenteService {

    private final ImageContainerUtil imageContainerUtil;
    private final UserRepository utenteRepository;

    public UserDetailsDto getUserDetails(String email) {
 
        Utente utenteModel =getUtenteByEmail(email);
        return UserDetailsDto.fromUserModel(utenteModel);
    }

    /*
     * 
     * Metodo per ottenere un utente dal repository
     * 
     * @param email email dell'utente
     * 
     * @return utente
     * @throws ApiException se l'utente non è presente
     */
    private Utente getUtenteByEmail(String emai
                l) {
        return utenteRepository.findById(email).orElseThrow(() -> new ApiException("Utente non trovato", HttpStatus.NOT_FOUND));
    }
 
    public ProfiloUtentePublicoDTO getDatiProfilo(String email){
        Utente utente = getUtenteByEmail(email);
 
     

    }  
    public void updateDatiProfilo(String email,  ProfiloUtentePublicoDTO profiloUtentePublicoDTO){
        try {
            Utente utente = getUtenteByEmail(email);

            utente.setNome(profiloUtentePublicoDTO.nome());
            utente.setCognome(profiloUtentePublicoDTO.cognome());
            utente.setBio(profiloUtentePublicoDTO.bio());
            utente.setSiti(profiloUtentePublicoDTO.siti());
            utente.setAreaGeografica(profiloUtentePublicoDTO.areaGeografica());
             utenteRepository.save(utente);
        }catch (ApiException e) {
             throw e; 
        }catch (Exception e){
            throw new ApiException("Errore interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        public Boolean isProfiloCompleto(String email) {
    return utenteRepository.isProfiloCompleto(email);
    }

    public void modificaProfilo(String email, ModificaProfiloDTO modificaProfiloDTO) {
        Utente utente = getUtenteByEmail(email);
        utente.setNome(modificaProfiloDTO.nome());
        utente.setCognome(modificaProfiloDTO.cognome());
        utente.setBio(modificaProfiloDTO.bio());
        utente.setSiti(modificaProfiloDTO.sitiSocial().toString());
        ut ente.setAreaGeografica(modificaProfil oD TO.in dirizzo());
        if(modificaProfiloDTO.immagineProfilo()!=null){
            try { 
                            
                String linkImmagine= imageContainerUtil.uploadImage(modificaProfiloDTO.immagineProfilo(),"immagineProfilo-"+email+".jpg");
                 utente.setFotoPr ofilo(linkImmagine);
            }catch  (Exception e){
                //DO nothing
            }
        }

    
    }
}
