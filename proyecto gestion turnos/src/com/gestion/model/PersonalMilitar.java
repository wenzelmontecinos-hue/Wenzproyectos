package com.gestion.model;

public class PersonalMilitar extends RecursoHumano {
    private String rangoGrado; // ej: "Teniente", "Subteniente", "Sargento"

    public PersonalMilitar(String id, String nombre, int horasAcumuladas, double tarifaBaseHora, String rangoGrado) {
        super(id, nombre, horasAcumuladas, tarifaBaseHora);
        this.rangoGrado = rangoGrado;
    }

    @Override
    public double calcularCostoTurno(int horasTurno) {
        // Tarifa plana estándar acordada
        return getTarifaBaseHora() * horasTurno;
    }

    public String getRangoGrado() { return rangoGrado; }
}