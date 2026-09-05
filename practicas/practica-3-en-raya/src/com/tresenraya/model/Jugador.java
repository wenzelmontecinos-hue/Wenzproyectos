package com.tresenraya.model;

import java.io.Serializable;

// Clase modelo para representar a un jugador
public class Jugador implements Serializable {
    private String nombre;
    private char simbolo;

    // Constructor vacío (Obligatorio para que Jackson pueda deserializar)
    public Jugador() {}

    // Constructor con parámetros
    public Jugador(String nombre, char simbolo) {
        this.nombre = nombre;
        this.simbolo = simbolo;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }
}