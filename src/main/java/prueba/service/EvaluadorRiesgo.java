package prueba.service;

import prueba.dto.ResultadoResponse;
import prueba.model.Cliente;
import prueba.model.Deuda;
import prueba.model.PersonaJuridica;
import prueba.model.PersonaNatural;

public abstract class EvaluadorRiesgo {

    public abstract boolean aplica(Cliente cliente);

    public abstract ResultadoResponse evaluar(Cliente cliente);

    protected int calcularPuntaje(Cliente c) {
        int p = 100;
        if (c.getPuntajeCrediticio() < 650) p -= 30;

        double deudaTotal = c.getDeudasActuales().stream().mapToDouble(Deuda::getMonto).sum();
        if (c instanceof PersonaNatural nat) {
            if (deudaTotal > nat.getIngresoMensual() * 0.4) p -= 15;
            if (c.getMontoSolicitado() > nat.getIngresoMensual() * 0.5) p -= 10;
        } else if (c instanceof PersonaJuridica jur) {
            if (jur.getAntiguedadAnios() < 3) { /* no penaliza numérico pero puede invalidar */ }
            if (deudaTotal > jur.getIngresoAnual() * 0.35) p -= 20;
            if (c.getMontoSolicitado() > jur.getIngresoAnual() * 0.3) p -= 15;
        }

        return p;
    }

    protected String riesgo(int puntaje) {
        return puntaje >= 80 ? "BAJO" : (puntaje >= 60 ? "MEDIO" : "ALTO");
    }

}
