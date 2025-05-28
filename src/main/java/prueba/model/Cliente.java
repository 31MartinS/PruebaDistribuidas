package prueba.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int puntajeCrediticio;
    private double montoSolicitado;
    private int plazoEnMeses;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Deuda> deudasActuales;
}