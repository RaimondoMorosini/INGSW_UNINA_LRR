package api.dieti2024.testFiltri;


import api.dieti2024.repository.AstaProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ControllerTest {

    @Autowired
    AstaProdottoRepository astaProdottoRepository;

    @PostMapping("public/testfiltro")
    public Object searchProducts(@RequestBody List<CoppiaValoriTest> caratteristiche) {
        CaratteristicheTest caratteristicheWrapper = new CaratteristicheTest(caratteristiche);
        return astaProdottoRepository.searchProductsWithSpecificValues(caratteristicheWrapper);
    }

    @Autowired
    ProdottoRepositoryImpl prodottoRepository;
    @GetMapping("public/query")
    public Object lanciaQuery() {
        String query= "Select * from prodotto where nome_prodotto LIKE '%Rai%'";
        int[] idProdotti = {2, 0};
        List<List<String>> caratteristiche = List.of(
                List.of("64GB"),
                List.of("IOS", "android")
                // Aggiungi ulteriori liste di caratteristiche qui
        );



         return prodottoRepository.lanciaQuery(idProdotti, caratteristiche);
    }
}
