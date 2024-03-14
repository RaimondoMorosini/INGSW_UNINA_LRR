package com.lrr.Dieti23Server.security;

import com.lrr.Dieti23Server.dto.CredenzialiUtenteDTO;
import com.lrr.Dieti23Server.dto.DatiUtentePerTokenDTO;
import com.lrr.Dieti23Server.dto.ReqRes;
import com.lrr.Dieti23Server.exceptions.ApiException;
import com.lrr.Dieti23Server.model.Utente;
import com.lrr.Dieti23Server.repository.UserRepository;
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

    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try {
            Utente utenteModel = new Utente();
            utenteModel.setEmail(registrationRequest.getEmail());
            utenteModel.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            Utente userResult = utenteRepo.save(utenteModel);
            if (userResult != null) {
                resp.setUtenteModel(userResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
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
            String passwordutente = utenteRecuperatoTramiteEmail.getPassword();
            if (!passwordEncoder.matches(credenzialiUtenteDTO.password(), passwordutente)) {
                throw new ApiException("Password errata", HttpStatus.UNAUTHORIZED);
            }
            DatiUtentePerTokenDTO datiUtentePerTokenDTO = DatiUtentePerTokenDTO.fromUserModel(utenteRecuperatoTramiteEmail);
             return jwtUtils.generateToken(datiUtentePerTokenDTO);

        } catch (Exception e) {
            throw new ApiException("Utente non trovato", HttpStatus.NOT_FOUND);
        }

        }



}
