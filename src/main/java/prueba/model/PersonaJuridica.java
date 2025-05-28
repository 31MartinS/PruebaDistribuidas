package prueba.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PersonaJuridica extends Cliente{

    private int antiguedadAnios;
    private double ingresoAnual;
    private int empleados;

}
