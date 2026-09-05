package com.geometria.model;

/**
 * Representa un círculo. Hereda de FiguraGeometrica.
 */
public class Circulo extends FiguraGeometrica {
    private double radio;

    // Constructor vacío (Obligatorio para que Jackson pueda deserializar)
    public Circulo() {}

    public Circulo(double radio) {
        super("Círculo");
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }
}