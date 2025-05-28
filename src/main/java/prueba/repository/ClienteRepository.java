package prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
