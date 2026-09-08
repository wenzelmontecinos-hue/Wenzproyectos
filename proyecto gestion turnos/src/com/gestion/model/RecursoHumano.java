package com.gestion.model;

public abstract class RecursoHumano {
    private String id;
    private String nombre;
    private int horasAcumuladas;
    private double tarifaBaseHora;
    private boolean disponible; // <-- Nuevo atributo de estado

    public RecursoHumano(String id, String nombre, int horasAcumuladas, double tarifaBaseHora) {
        this.id = id;
        this.nombre = nombre;
        this.horasAcumuladas = horasAcumuladas;
        this.tarifaBaseHora = tarifaBaseHora;
        this.disponible = true; // Disponible por defecto
    }

    public abstract double calcularCostoTurno(int horasTurno);

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getHorasAcumuladas() { return horasAcumuladas; }
    public double getTarifaBaseHora() { return tarifaBaseHora; }
    public boolean isDisponible() { return disponible; }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void registrarHoras(int horas) {
        if (horas > 0) {
            this.horasAcumuladas += horas;
        }
    }
}