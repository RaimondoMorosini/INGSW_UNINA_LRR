package com.lrr.Dieti23Server.configuration;

import com.lrr.Dieti23Server.dto.UserDetailsDto;
import com.lrr.Dieti23Server.security.JWTUtils;
import com.lrr.Dieti23Server.service.utenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;


@Component
public class JWTAuthFIlter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private utenteService utenteService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain chain) throws ServletException, IOException {
        final String token = getToken(request, response, chain);
        if (token == null) return;

        if (isTokenNonVaido( token)){
            chain.doFilter(request, response);
            return;
        }

        final String username = jwtUtils.getUsername(token);

        // set user details on spring security context

        // Carica gli user details
        UserDetailsDto userDetails = utenteService.getUserDetails(username);

        // Crea l'oggetto Authentication
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, Collections.emptyList());
        // Imposta il dettaglio dell'autenticazione
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // Imposta il contesto di sicurezza di Spring
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continua con la catena di filtri
        chain.doFilter(request, response);
    }

    private boolean isTokenNonVaido( String token) throws IOException, ServletException {
        if (!jwtUtils.isTokenValid(token)) {
            //token non valido
            return true;
        }
        return false;
    }

    private static String getToken(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // recupero e controllo se esiste la il token jwt nel head della richiesta http
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return null;
        }

        final String token = header.substring(7);
        return token;
    }


}
