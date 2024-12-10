package api.dieti2024.repository;

import api.dieti2024.model.DatiVenditori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatiVenditoreRepository extends JpaRepository<DatiVenditori,String> {
}
