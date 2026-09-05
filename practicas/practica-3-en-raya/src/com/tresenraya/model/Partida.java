package com.tresenraya.model;

import java.io.Serializable;

public class Partida implements Serializable {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private int turno; // 1 para Jugador 1, 2 para Jugador 2

    // Constructor vacío obligatorio para Jackson
    public Partida() {}

    public Partida(Tablero tablero, Jugador jugador1, Jugador jugador2, int turno) {
        this.tablero = tablero;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.turno = turno;
    }

    // Getters y Setters
    public Tablero getTablero() { return tablero; }
    public void setTablero(Tablero tablero) { this.tablero = tablero; }

    public Jugador getJugador1() { return jugador1; }
    public void setJugador1(Jugador jugador1) { this.jugador1 = jugador1; }

    public Jugador getJugador2() { return jugador2; }
    public void setJugador2(Jugador jugador2) { this.jugador2 = jugador2; }

    public int getTurno() { return turno; }
    public void setTurno(int turno) { this.turno = turno; }
}