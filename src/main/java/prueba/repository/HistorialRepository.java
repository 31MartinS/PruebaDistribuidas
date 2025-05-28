package prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.model.HistorialEvaluacion;

import java.util.List;

public interface HistorialRepository extends JpaRepository<HistorialEvaluacion, Long> {
    List<HistorialEvaluacion> findByClienteId(Long clienteId);
}
