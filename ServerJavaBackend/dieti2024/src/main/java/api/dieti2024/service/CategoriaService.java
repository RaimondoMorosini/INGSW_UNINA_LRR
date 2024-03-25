package api.dieti2024.service;

import api.dieti2024.dto.CategoriaDTO;
import api.dieti2024.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> costruisciGerarchiaCategorie(){

        List<CategoriaDTO> listaCategorieDaRestituire = new ArrayList<>();

        List<String> categorieRadici =  categoriaRepository.getCategorieRadice();

        List<String> figli;

        /*
            Per ogni categoria radice estraiamo la lista dei suoi figli

         */
        for(String categoriaRadice : categorieRadici){

            CategoriaDTO categoriaRadiceDTO = new CategoriaDTO(categoriaRadice);

            if( (categoriaRepository.getNumeroFigli(categoriaRadice)) > 0 ){

                figli = categoriaRepository.getCategorieFiglie(categoriaRadice);

                /*
                    Per ogni figlio della categoria Radice viene fatto la stessa cosa ricorsivamente
                 */
                for(String figlio : figli){

                    CategoriaDTO figlioDTO = new CategoriaDTO(figlio);

                    costruisciGerarchiaRicorsivamente(figlioDTO);

                    categoriaRadiceDTO.getChildren().add(figlioDTO);
                }
            }

            listaCategorieDaRestituire.add(categoriaRadiceDTO);
        }

        return listaCategorieDaRestituire;
    }

    private void costruisciGerarchiaRicorsivamente(CategoriaDTO categoriaParametro){

        if( (categoriaRepository.getNumeroFigli(categoriaParametro.getLabel())) > 0 ){

            List<String> figli = categoriaRepository.getCategorieFiglie(categoriaParametro.getLabel());

            for (String figlio : figli){

                CategoriaDTO figlioDTO = new CategoriaDTO(figlio);

                costruisciGerarchiaRicorsivamente(figlioDTO);

                categoriaParametro.getChildren().add(figlioDTO);
            }
        }
    }

}
