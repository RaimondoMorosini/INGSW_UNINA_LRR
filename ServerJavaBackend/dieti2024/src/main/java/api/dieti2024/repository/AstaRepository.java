package api.dieti2024.repository;

import api.dieti2024.model.Asta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AstaRepository extends JpaRepository<Asta, String> {

}
