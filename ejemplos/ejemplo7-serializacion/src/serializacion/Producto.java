package serializacion;

import java.io.Serializable;

// Clase modelo para el objeto
public class Producto implements Serializable {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    // Constructor vacío (Obligatorio para que Jackson pueda deserializar)
    public Producto() {}

    public Producto(int id, String nombre, double precio, int c) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = c;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}