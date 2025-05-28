package prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.dto.HistorialResponse;
import prueba.service.HistorialService;

import java.util.List;

@RestController
@RequestMapping("/historial")

public class HistorialController {
    @Autowired
    private HistorialService service;

    @GetMapping("/{id}")
    public List<HistorialResponse> historialPorCliente(@PathVariable Long id) {
        return service.historialPorClienteId(id);
    }


}
