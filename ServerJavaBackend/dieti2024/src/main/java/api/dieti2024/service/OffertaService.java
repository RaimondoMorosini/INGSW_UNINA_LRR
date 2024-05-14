package api.dieti2024.service;

import api.dieti2024.dto.OffertaDto;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Asta;
import api.dieti2024.model.DatiAstaInglese;
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
    AstaService astaService;
@Autowired
    PermessoRepository permessoRepository;

@Autowired
    AstaRepository astaRepository;
    public Offerta faiOfferta(OffertaDto offertaDto,long tempoOfferta) {
        offertaIsValido(offertaDto,tempoOfferta);
        String emailUtente = ControllerRestUtil.getEmailOfUtenteCorrente();
        Offerta offerta = new Offerta();
        offerta.setPrezzoProposto(offertaDto.prezzoProposto());
        offerta.setEmailUtente(emailUtente);
        offerta.setAstaId(offertaDto.idAsta());
        offerta.setTempoOfferta(tempoOfferta);
        return offertaRepository.save(offerta);

    }

    private void offertaIsValido(OffertaDto offertaDto, long tempoOfferta) {
        Asta asta = astaService.getAstaById(offertaDto.idAsta());
        String tipoAsta = asta.getTipoAsta();
        if(!ControllerRestUtil.hasPermeessoDiFareUnOfferta(tipoAsta))
            throw new ApiException("L'utente non ha il permesso di fare un offerta su "+tipoAsta, HttpStatus.FORBIDDEN);

        if(!CalendarioUtil.verificaScadenza(tempoOfferta,asta.getDataScadenza()))
            throw new ApiException("Asta scaduta", HttpStatus.BAD_REQUEST);

        if(offertaDto.prezzoProposto() <=0)
            throw new ApiException("Prezzo offerta inferiore alla base asta", HttpStatus.BAD_REQUEST);


        double baseAsta = asta.getBaseAsta();
        switch (tipoAsta){
            case TipoAsta.INGLESE:
                if (offertaDto.prezzoProposto()<=baseAsta || offertaDto.prezzoProposto()<=asta.getPrezzoAttuale())
                    throw new ApiException("Prezzo offerta inferiore al prezzo attuale", HttpStatus.BAD_REQUEST);
                DatiAstaInglese datiAstaInglese = astaService.getDatiAstaIngleseById(asta.getId());
                if (datiAstaInglese.getQuotaFissaPerLaPuntata()+baseAsta != offertaDto.prezzoProposto())
                    throw new ApiException("Prezzo offerta non valido", HttpStatus.BAD_REQUEST);
                break;
            case TipoAsta.SILENZIOSA:
                if (offertaDto.prezzoProposto()<=baseAsta)
                    throw new ApiException("Prezzo offerta superiore alla base asta", HttpStatus.BAD_REQUEST);
                break;
            case TipoAsta.APPALTO:
                if (offertaDto.prezzoProposto()<=baseAsta || offertaDto.prezzoProposto()<=asta.getPrezzoAttuale())
                    throw new ApiException("Prezzo offerta superiore al prezzo attuale ",HttpStatus.BAD_REQUEST);
                break;
        }


    }
}
