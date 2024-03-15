package api.dieti2024.service;

import api.dieti2024.dto.UserDetailsDto;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Utente;
import api.dieti2024.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class utenteService{

    @Autowired
    private UserRepository userRepository;

    public UserDetailsDto getUserDetails(String email) {

        Utente utenteModel = userRepository.findById(email).orElseThrow(() -> new ApiException("Utente non trovato", HttpStatus.NOT_FOUND));
        return UserDetailsDto.fromUserModel(utenteModel);
    }
}
