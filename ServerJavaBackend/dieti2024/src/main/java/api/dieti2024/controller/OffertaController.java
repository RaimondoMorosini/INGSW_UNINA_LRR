package api.dieti2024.controller;

import api.dieti2024.dto.OffertaDto;
import api.dieti2024.service.OffertaService;
import api.dieti2024.util.CalendarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OffertaController {

    @Autowired
    OffertaService offertaService;

    @PostMapping("/faiOfferta")
    public void faiOfferta(@RequestBody OffertaDto offertaDto) {

        long tempoOfferta = CalendarioUtil.GetTime();
        try {

            offertaService.faiOfferta(offertaDto, tempoOfferta ) ;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
