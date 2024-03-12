package com.lrr.Dieti23Server.service;

import com.lrr.Dieti23Server.dto.UserDetailsDto;
import com.lrr.Dieti23Server.model.Utente;
import com.lrr.Dieti23Server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class utenteService{

    @Autowired
    private UserRepository userRepository;

    public UserDetailsDto getUserDetails(String email) {

        Utente utenteModel = userRepository.findByEmail(email).get();
        return UserDetailsDto.fromUserModel(utenteModel);
    }
}
