package prueba.service;

import prueba.dto.ResultadoResponse;
import prueba.model.Cliente;

public class EvaluadorRiesgoMedio extends EvaluadorRiesgo{
    public boolean aplica(Cliente c) {
        int p = calcularPuntaje(c);
        return p >= 60 && p < 80;
    }

    public ResultadoResponse evaluar(Cliente c) {
        int p = calcularPuntaje(c);
        return new ResultadoResponse("MEDIO", true, p, "Cliente apto para préstamo con condiciones ajustadas", 8.0, c.getPlazoEnMeses());
    }

}
