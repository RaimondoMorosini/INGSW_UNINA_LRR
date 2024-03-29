package api.dieti2024.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import api.dieti2024.dto.DatiUtentePerTokenDTO;
import com.okta.jwt.AccessTokenVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {

    private final Algorithm hmac512 ;
    private final JWTVerifier verifier;

    public JWTUtils (@Value("${secretKey}") String secretKey )  {

        this.hmac512 = Algorithm.HMAC256(secretKey);
        this.verifier = JWT.require(this.hmac512).build();
    }
    private  static  final long EXPIRATION_TIME = 86400000; //24hours or 86400000 milisecs

    public String generateToken(DatiUtentePerTokenDTO datiTokenDTO){
        return JWT.create()
                .withSubject(datiTokenDTO.email())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(hmac512);
    }

    /**
     * Restituisce un nome utente presente nel token.
     * @param token token valido
     * @return nome utente estratto dal token, oppure null se non è possibile estrarlo correttamente
     */
    public String getUsername(String token){
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getSubject();
        } catch (JWTDecodeException e) {
            // In caso di errore durante la decodifica del token
            e.printStackTrace();
            return null;
        }
    }


    public boolean isTokenValid(final String token){
        try {
             verifier.verify(token);
             return true;
        } catch (final JWTVerificationException verificationEx) {
            return false;
        }
    }



}
