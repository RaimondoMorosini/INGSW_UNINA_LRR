package api.dieti2024.service;

import api.dieti2024.dto.OffertaDto;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Asta;
import api.dieti2024.model.Offerta;
import api.dieti2024.repository.AstaRepository;
import api.dieti2024.repository.OffertaRepository;
import api.dieti2024.repository.PermessoRepository;
import api.dieti2024.service.Asta.AstaService;
import api.dieti2024.util.CalendarioUtil;
import api.dieti2024.util.ControllerRestUtil;
import api.dieti2024.util.TipoAsta;
import api.dieti2024.util.TipoPermesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class OffertaService {

@Autowired
OffertaRepository offertaRepository;

@Autowired
    AstaRepository astaRepository;
@Autowired
    PermessoRepository permessoRepository;

    public void faiOfferta(OffertaDto offertaDto,long tempoOfferta) {
        offertaIsValido(offertaDto,tempoOfferta);
        String emailUtente = ControllerRestUtil.getEmailOfUtenteCorrente();
        Offerta offerta = new Offerta();
        offerta.setPrezzoProposto(offertaDto.prezzoProposto());
        offerta.setEmailUtente(emailUtente);
        offerta.setAstaId(offertaDto.idAsta());
        offerta.setTempoOfferta(tempoOfferta);
        offertaRepository.save(offerta);


    }

    private void offertaIsValido(OffertaDto offertaDto, long tempoOfferta) {
        Asta asta = astaRepository.findById(offertaDto.idAsta()).orElseThrow();
        String tipoAsta = asta.getTipoAsta();
        if(!ControllerRestUtil.hasPermeessoDiFareUnOfferta(tipoAsta))
            throw new ApiException("Utente non ha il permesso di fare un offerta", HttpStatus.FORBIDDEN);

        double baseAsta = asta.getBaseAsta();
        switch (tipoAsta){
            case TipoAsta.INGLESE:

                break;
            case TipoAsta.SILENZIOSA:
                if (offertaDto.prezzoProposto()<=0)
                    throw new IllegalArgumentException("Prezzo offerta inferiore alla base asta");
                break;
            case TipoAsta.APPALTO:

                break;
        }


    }
}
