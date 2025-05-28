package prueba.service;

import prueba.dto.ResultadoResponse;
import prueba.model.Cliente;

public class EvaluadorRiesgoBajo extends EvaluadorRiesgo{
    public boolean aplica(Cliente c) {
        return calcularPuntaje(c) >= 80;
    }
    public ResultadoResponse evaluar(Cliente c) {
        int p = calcularPuntaje(c);
        return new ResultadoResponse("BAJO", true, p, "Cliente apto para préstamo con condiciones preferenciales", 6.5, c.getPlazoEnMeses());
    }

}
