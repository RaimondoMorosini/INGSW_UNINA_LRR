package api.dieti2024.security;

import api.dieti2024.dto.CredenzialiUtenteDTO;
import api.dieti2024.dto.DatiUtentePerTokenDTO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Utente;
import api.dieti2024.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository utenteRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public String registrazione(CredenzialiUtenteDTO credenzialiUtenteDTO) {
        try{

        Utente utenteModel = new Utente();
        utenteModel.setEmail(credenzialiUtenteDTO.email());
        if(credenzialiUtenteDTO.metodoDiRegistrazione().equals("dieti")){
            utenteModel.setMetodoDiRegistrazione("dieti");
            utenteModel.setPassword(passwordEncoder.encode(credenzialiUtenteDTO.password()));
        }
        else{
            utenteModel.setMetodoDiRegistrazione("auth0");
        }

        utenteRepo.save(utenteModel);
        return jwtUtils.generateToken(DatiUtentePerTokenDTO.fromUserModel(utenteModel));
        }catch (Exception e){
            throw new ApiException("Errore nella registrazione", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    /**
     *
     * @param credenzialiUtenteDTO credenziali dell'utente
     * @return restituisce il token in caso di successo
     */
    public String login(CredenzialiUtenteDTO credenzialiUtenteDTO){

        Utente utenteRecuperatoTramiteEmail;
        try {
            String email = credenzialiUtenteDTO.email();
            utenteRecuperatoTramiteEmail = utenteRepo.findById(email).orElseThrow();
            String metodoDiRegistrazione =utenteRecuperatoTramiteEmail.getMetodoDiRegistrazione();
            if(!metodoDiRegistrazione.equals("dieti")){
                throw new ApiException("Utente non trovato", HttpStatus.NOT_FOUND);
            }
            String passwordutente = utenteRecuperatoTramiteEmail.getPassword();
         /*   if (!passwordEncoder.matches(credenzialiUtenteDTO.password(), passwordutente)) {
                throw new ApiException("Password errata", HttpStatus.UNAUTHORIZED);
            }*/
            DatiUtentePerTokenDTO datiUtentePerTokenDTO = DatiUtentePerTokenDTO.fromUserModel(utenteRecuperatoTramiteEmail);
             return jwtUtils.generateToken(datiUtentePerTokenDTO);

        } catch (Exception e) {
            throw new ApiException("Utente non trovato", HttpStatus.NOT_FOUND);
        }

        }



}
