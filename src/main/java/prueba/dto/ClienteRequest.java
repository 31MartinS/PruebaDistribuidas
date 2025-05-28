package prueba.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClienteRequest {

    private String tipoCliente;
    private String nombre;
    private int puntajeCrediticio;
    private List<DeudaDto> deudasActuales;
    private double montoSolicitado;
    private int plazoEnMeses;

    // Persona Natural
    private int edad;
    private double ingresoMensual;

    // Persona Jurídica
    private int antiguedadAnios;
    private double ingresoAnual;
    private int empleados;

}
