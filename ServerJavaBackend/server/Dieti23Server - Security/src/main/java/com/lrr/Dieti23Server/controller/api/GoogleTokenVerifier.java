package com.lrr.Dieti23Server.controller.api;

import com.lrr.Dieti23Server.dto.GoogleTokenInfo;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class GoogleTokenVerifier {

    private static final String GOOGLE_TOKEN_INFO_URL = "https://oauth2.googleapis.com/tokeninfo";
    private static final String CLIENT_ID = "523712309350-64ooftdu5bto8ln30pbkfjtr88fjpp15.apps.googleusercontent.com"; // Sostituire con il tuo ID cliente

    private RestTemplate restTemplate;

    public GoogleTokenVerifier() {
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/verificaGoogle/{token}/{email}")
    public ResponseEntity<GoogleTokenInfo> verifyToken(@PathVariable String accessToken, @PathVariable String userEmail) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("access_token", accessToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<GoogleTokenInfo> responseEntity = restTemplate.exchange(
                GOOGLE_TOKEN_INFO_URL,
                HttpMethod.POST,
                requestEntity,
                GoogleTokenInfo.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            GoogleTokenInfo tokenInfo = responseEntity.getBody();
            // Verifica se il token è stato emesso per la tua applicazione
            if (tokenInfo.getAud().equals(CLIENT_ID) && tokenInfo.getEmail().equals(userEmail)) {
                return ResponseEntity.ok(tokenInfo);
            } else {
                // Il token non soddisfa una delle condizioni
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            // Handle error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
