package api.dieti2024.repository;

import api.dieti2024.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {
}
