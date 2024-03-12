package com.lrr.Dieti23Server.security;

import com.lrr.Dieti23Server.dto.CredenzialiUtenteDTO;
import com.lrr.Dieti23Server.dto.DatiUtentePerTokenDTO;
import com.lrr.Dieti23Server.dto.ReqRes;
import com.lrr.Dieti23Server.model.Utente;
import com.lrr.Dieti23Server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    public ReqRes login(CredenzialiUtenteDTO credenzialiUtenteDTO){
        ReqRes response = new ReqRes();

        try {

            Utente utenteTrovatobyEmail = utenteRepo.findByEmail(credenzialiUtenteDTO.email()).get();

            if(!utenteTrovatobyEmail.getPassword().equals(credenzialiUtenteDTO.password())){
                //lancia un eccezione
                return null;
            }


            System.out.println("USER IS: "+ user);
            var token = jwtUtils.generateToken(new DatiUtentePerTokenDTO.fromUserModel(utenteTrovatobyEmail));

        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
        UserTest users = utenteRepo.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}
