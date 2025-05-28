package prueba.service;

import org.springframework.stereotype.Service;
import prueba.dto.ResultadoResponse;
import prueba.model.Cliente;

import java.util.List;

@Service
public class EvaluacionService {
    private final List<EvaluadorRiesgo> evaluadores = List.of(
            new EvaluadorRiesgoBajo(), new EvaluadorRiesgoMedio(), new EvaluadorRiesgoAlto()
    );

    public ResultadoResponse evaluar(Cliente cliente) {
        return evaluadores.stream()
                .filter(e -> e.aplica(cliente))
                .findFirst()
                .map(e -> e.evaluar(cliente))
                .orElseThrow();
    }

}
