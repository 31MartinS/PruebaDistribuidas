package prueba.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Deuda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double monto;
    private int plazoMeses;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
