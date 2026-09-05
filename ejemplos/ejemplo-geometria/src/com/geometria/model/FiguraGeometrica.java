package com.geometria.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Clase abstracta que sirve como plantilla base para todas las figuras geométricas.
 *
 * Las anotaciones de Jackson permiten serializar/deserializar de forma polimórfica:
 * al guardar se incluye el campo "tipo" y al cargar se instancia la subclase correcta.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Circulo.class, name = "circulo"),
        @JsonSubTypes.Type(value = Rectangulo.class, name = "rectangulo")
})
public abstract class FiguraGeometrica {
    protected String nombre;

    // Constructor vacío (Obligatorio para que Jackson pueda deserializar)
    protected FiguraGeometrica() {}

    public FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos abstractos que deben ser implementados por las subclases
    public abstract double calcularArea();
    public abstract double calcularPerimetro();

    @Override
    public String toString() {
        return String.format("--- %s ---\nÁrea: %.2f\nPerímetro: %.2f", 
                nombre, calcularArea(), calcularPerimetro());
    }
}