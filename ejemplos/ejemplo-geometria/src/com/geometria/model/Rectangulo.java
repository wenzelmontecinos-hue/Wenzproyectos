package com.geometria.model;

/**
 * Representa un rectángulo. Hereda de FiguraGeometrica.
 */
public class Rectangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    // Constructor vacío (Obligatorio para que Jackson pueda deserializar)
    public Rectangulo() {}

    public Rectangulo(double base, double altura) {
        super("Rectángulo");
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (base + altura);
    }
}