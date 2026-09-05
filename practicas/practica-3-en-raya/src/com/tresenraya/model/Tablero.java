package com.tresenraya.model;

import java.io.Serializable;

public class Tablero implements Serializable {
    private char[][] matriz = new char[3][3];

    // Constructor por defecto
    public Tablero() {
        this.limpiar();
    }

    // Getter y Setter (Obligatorios para que Jackson guarde y lea la matriz)
    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    // Reinicia la matriz con espacios en blanco
    public void limpiar() {
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                this.matriz[fila][col] = ' ';
            }
        }
    }

    // Muestra el tablero en consola
    public void mostrar() {
        System.out.println("\n-------------");
        for (int fila = 0; fila < 3; fila++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(this.matriz[fila][col] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Coloca la ficha si la posición está libre
    public boolean hacerMovimiento(int fila, int col, char simbolo) {
        if (fila >= 0 && fila < 3 && col >= 0 && col < 3 && this.matriz[fila][col] == ' ') {
            this.matriz[fila][col] = simbolo;
            return true;
        } else {
            return false;
        }
    }

    // Verifica si hay un ganador en filas, columnas o diagonales
    public boolean hayGanador(char simbolo) {
        for (int i = 0; i < 3; i++) {
            if ((this.matriz[i][0] == simbolo && this.matriz[i][1] == simbolo && this.matriz[i][2] == simbolo) ||
                (this.matriz[0][i] == simbolo && this.matriz[1][i] == simbolo && this.matriz[2][i] == simbolo)) {
                return true;
            }
        }

        return (this.matriz[0][0] == simbolo && this.matriz[1][1] == simbolo && this.matriz[2][2] == simbolo) ||
               (this.matriz[0][2] == simbolo && this.matriz[1][1] == simbolo && this.matriz[2][0] == simbolo);
    }

    // Evalúa si el tablero está lleno (empate)
    public boolean estaLleno() {
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                if (this.matriz[fila][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}