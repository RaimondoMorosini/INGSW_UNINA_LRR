package api.dieti2024.repository;

import api.dieti2024.dto.OffertaAstaIngleseDTO;
import api.dieti2024.model.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OffertaRepository extends JpaRepository<Offerta, Long>{
    @Query(value = "SELECT * FROM offerta WHERE asta = ?1 ORDER BY tempo_offerta DESC", nativeQuery = true)
    List<Offerta> getOfferteByIdAsta(int idAsta);
}
