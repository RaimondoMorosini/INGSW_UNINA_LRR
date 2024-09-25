package api.dieti2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdottoRepository extends JpaRepository<api.dieti2024.model.Prodotto, Integer> {

    @Query(value = "SELECT id from asta_join_prodotto p where p.id_asta = ?1", nativeQuery = true)
    Integer findIdProdottoByIdAsta(int idAsta);
}
