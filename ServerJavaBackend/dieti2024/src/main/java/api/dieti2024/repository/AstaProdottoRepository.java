package api.dieti2024.repository;

import api.dieti2024.dto.asta.InfoDatiAstaDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AstaProdottoRepository extends JpaRepository<InfoDatiAstaDTO,Integer> {
}
