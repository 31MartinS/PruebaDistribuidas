package prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.dto.ClienteRequest;
import prueba.dto.ResultadoResponse;
import prueba.model.*;
import prueba.repository.ClienteRepository;
import prueba.service.EvaluacionService;
import prueba.service.HistorialService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/evaluar-riesgo")
public class EvaluacionController {
    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private HistorialService historialService;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResultadoResponse evaluar(@RequestBody ClienteRequest dto) {
        Cliente cliente;

        if (dto.getTipoCliente().equalsIgnoreCase("NATURAL")) {
            PersonaNatural c = new PersonaNatural();
            c.setEdad(dto.getEdad());
            c.setIngresoMensual(dto.getIngresoMensual());
            cliente = c;
        } else {
            PersonaJuridica c = new PersonaJuridica();
            c.setAntiguedadAnios(dto.getAntiguedadAnios());
            c.setIngresoAnual(dto.getIngresoAnual());
            c.setEmpleados(dto.getEmpleados());
            cliente = c;
        }

        cliente.setNombre(dto.getNombre());
        cliente.setMontoSolicitado(dto.getMontoSolicitado());
        cliente.setPlazoEnMeses(dto.getPlazoEnMeses());
        cliente.setPuntajeCrediticio(dto.getPuntajeCrediticio());

        // Asignar deudas
        cliente.setDeudasActuales(dto.getDeudasActuales().stream().map(d -> {
            Deuda deuda = new Deuda();
            deuda.setMonto(d.getMonto());
            deuda.setPlazoMeses(d.getPlazoMeses());
            deuda.setCliente(cliente);
            return deuda;
        }).toList());

        // 🔑 Guardar el cliente y obtener entidad persistida
        Cliente clienteGuardado = clienteRepository.save(cliente);

        // Evaluar con la entidad persistida
        ResultadoResponse resultado = evaluacionService.evaluar(clienteGuardado);

        // Crear historial asociado al cliente
        HistorialEvaluacion h = new HistorialEvaluacion();
        h.setCliente(clienteGuardado);
        h.setClienteNombre(clienteGuardado.getNombre());
        h.setTipoCliente(dto.getTipoCliente());
        h.setMontoSolicitado(clienteGuardado.getMontoSolicitado());
        h.setPlazoEnMeses(clienteGuardado.getPlazoEnMeses());
        h.setNivelRiesgo(resultado.getNivelRiesgo());
        h.setAprobado(resultado.isAprobado());
        h.setFechaConsulta(LocalDateTime.now());

        // Guardar historial
        historialService.registrar(h);

        return resultado;
    }

}
