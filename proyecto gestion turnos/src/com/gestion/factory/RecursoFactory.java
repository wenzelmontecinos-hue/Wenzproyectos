package com.gestion.factory;

import com.gestion.model.*;

public class RecursoFactory {
    public static RecursoHumano crearRecurso(String ambito, String id, String nombre, int horas, double tarifa, String atributoEspecial) {
        switch (ambito.toUpperCase()) {
            case "GASTRONOMIA":
                return new EmpleadoGastronomico(id, nombre, horas, tarifa, atributoEspecial);
            case "INDUSTRIAL":
                return new OperarioIndustrial(id, nombre, horas, tarifa, atributoEspecial);
            case "MILITAR":
                return new PersonalMilitar(id, nombre, horas, tarifa, atributoEspecial);
            default:
                throw new IllegalArgumentException("Ámbito no soportado: " + ambito);
        }
    }
}