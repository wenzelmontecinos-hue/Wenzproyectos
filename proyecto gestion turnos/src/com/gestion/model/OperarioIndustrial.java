package com.gestion.model;

public class OperarioIndustrial extends RecursoHumano {
    private String especialidadMaquinaria; // ej: "Torno CNC", "Línea de Ensamble"

    public OperarioIndustrial(String id, String nombre, int horasAcumuladas, double tarifaBaseHora, String especialidadMaquinaria) {
        super(id, nombre, horasAcumuladas, tarifaBaseHora);
        this.especialidadMaquinaria = especialidadMaquinaria;
    }

    @Override
    public double calcularCostoTurno(int horasTurno) {
        // Ejemplo de regla: Bonificación por riesgo de operación de maquinaria
        double bonificacionRiesgo = 15.0;
        return (getTarifaBaseHora() * horasTurno) + bonificacionRiesgo;
    }

    public String getEspecialidadMaquinaria() { return especialidadMaquinaria; }
}