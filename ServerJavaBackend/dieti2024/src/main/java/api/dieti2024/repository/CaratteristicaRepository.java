package api.dieti2024.repository;

import api.dieti2024.model.Caratteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaratteristicaRepository extends JpaRepository<Caratteristica, Integer> {

}
