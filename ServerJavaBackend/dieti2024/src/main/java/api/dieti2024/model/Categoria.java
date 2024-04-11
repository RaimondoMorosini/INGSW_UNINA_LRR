package api.dieti2024.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "padre")
    private Categoria padre;

}
