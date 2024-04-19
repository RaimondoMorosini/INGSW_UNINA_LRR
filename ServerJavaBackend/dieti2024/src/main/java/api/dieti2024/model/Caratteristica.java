package api.dieti2024.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "caratteristica")
public class Caratteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome_caratteristica")
    private String nomeCaratteristica;

    @Column(name = "tipo_caratteristica")
    private String tipoCaratteristica;

    @ElementCollection
    List<String> opzioniSelezionabili;
}
