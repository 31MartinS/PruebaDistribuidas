package prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.model.Deuda;

public interface DeudaRepository extends JpaRepository<Deuda, Long> {}