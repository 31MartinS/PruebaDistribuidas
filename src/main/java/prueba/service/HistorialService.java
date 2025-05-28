package prueba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.dto.HistorialResponse;
import prueba.model.HistorialEvaluacion;
import prueba.repository.HistorialRepository;

import java.util.List;

@Service
public class HistorialService {
    @Autowired
    private HistorialRepository historialRepository;

    // Registrar evaluación en historial
    public void registrar(HistorialEvaluacion h) {
        historialRepository.save(h);
    }

    // Buscar historial por ID del cliente
    public List<HistorialResponse> historialPorClienteId(Long clienteId) {
        return historialRepository.findByClienteId(clienteId)
                .stream()
                .map(h -> new HistorialResponse(
                        h.getId(),
                        h.getClienteNombre(),
                        h.getTipoCliente(),
                        h.getMontoSolicitado(),
                        h.getPlazoEnMeses(),
                        h.getNivelRiesgo(),
                        h.isAprobado(),
                        h.getFechaConsulta()
                )).toList();
    }


}
