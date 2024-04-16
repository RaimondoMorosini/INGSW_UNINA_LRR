package api.dieti2024.util;

import api.dieti2024.dto.auth.UserDetailsDto;
import org.springframework.security.core.context.SecurityContextHolder;

public class ControllerRestUtil {

    /**
     * Metodo per ottenere l'email dell'utente tramite il SecurityContext
     *
     * @return email dell'utente
     */
    public String getEmailOfUtenteCorrente() {
        UserDetailsDto dettagliUser = (UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return dettagliUser.email();
    }

}
