package prueba.service;

import prueba.dto.ResultadoResponse;
import prueba.model.Cliente;

public class EvaluadorRiesgoAlto extends EvaluadorRiesgo{
    public boolean aplica(Cliente c) {
        return calcularPuntaje(c) < 60;
    }
    public ResultadoResponse evaluar(Cliente c) {
        int p = calcularPuntaje(c);
        return new ResultadoResponse("ALTO", false, p, "Cliente no apto para préstamo", 0.0, 0);
    }

}
