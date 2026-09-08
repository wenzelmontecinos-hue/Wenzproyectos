package com.gestion.model;

public class EmpleadoGastronomico extends RecursoHumano {
    private String areaTrabajo; // ej: "Cocina Caliente", "Panadería", "Salón"

    public EmpleadoGastronomico(String id, String nombre, int horasAcumuladas, double tarifaBaseHora, String areaTrabajo) {
        super(id, nombre, horasAcumuladas, tarifaBaseHora);
        this.areaTrabajo = areaTrabajo;
    }

    @Override
    public double calcularCostoTurno(int horasTurno) {
        // Ejemplo de regla: Recargo del 10% por manipulación de alimentos/insumos
        return (getTarifaBaseHora() * horasTurno) * 1.10;
    }

    public String getAreaTrabajo() { return areaTrabajo; }
}