package com.tresenraya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tresenraya.model.Jugador;
import com.tresenraya.model.Partida;
import com.tresenraya.model.Tablero;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String ARCHIVO_JSON = "practicas/practica-3-en-raya/partida.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tablero tablero = new Tablero();
        Jugador j1 = new Jugador("Jugador 1", 'X');
        Jugador j2 = new Jugador("Jugador 2", 'O');
        Jugador jugadorActual = j1;

        System.out.println("=== JUEGO TRES EN RAYA ===");
        System.out.println("1. Nueva Partida");
        System.out.println("2. Cargar Partida Guardada");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
        
        int opcionInicio = sc.nextInt();

        if (opcionInicio == 3) {
            System.out.println("¡Gracias por jugar! Hasta luego.");
            sc.close();
            return;
        }

        if (opcionInicio == 2) {
            Partida guardada = cargarPartida();
            if (guardada != null) {
                tablero = guardada.getTablero();
                j1 = guardada.getJugador1();
                j2 = guardada.getJugador2();
                jugadorActual = (guardada.getTurno() == 1) ? j1 : j2;
                System.out.println("¡Partida cargada exitosamente!");
            } else {
                System.out.println("No se pudo cargar la partida. Iniciando nueva partida...");
            }
        }

        boolean jugando = true;

        while (jugando) {
            tablero.mostrar();
            System.out.println("\n--- Turno de " + jugadorActual.getNombre() + " (" + jugadorActual.getSimbolo() + ") ---");
            System.out.println("1. Realizar movimiento");
            System.out.println("2. Guardar y salir");
            System.out.println("3. Salir sin guardar");
            System.out.print("Seleccione una opción: ");
            
            int opcionMenu = sc.nextInt();

            if (opcionMenu == 1) {
                System.out.print("Ingrese fila (0-2): ");
                int fila = sc.nextInt();
                System.out.print("Ingrese columna (0-2): ");
                int columna = sc.nextInt();

                if (tablero.hacerMovimiento(fila, columna, jugadorActual.getSimbolo())) {
                    if (tablero.hayGanador(jugadorActual.getSimbolo())) {
                        tablero.mostrar();
                        System.out.println("\n¡Felicidades " + jugadorActual.getNombre() + "! Has ganado.");
                        jugando = false;
                    } else if (tablero.estaLleno()) {
                        tablero.mostrar();
                        System.out.println("\n¡Es un empate!");
                        jugando = false;
                    } else {
                        jugadorActual = (jugadorActual == j1) ? j2 : j1;
                    }
                } else {
                    System.out.println("Movimiento inválido o casilla ocupada. Intenta de nuevo.");
                }
            } else if (opcionMenu == 2) {
                int turnoNumero = (jugadorActual == j1) ? 1 : 2;
                Partida estadoActual = new Partida(tablero, j1, j2, turnoNumero);
                guardarPartida(estadoActual);
                System.out.println("\nPartida guardada correctamente en " + ARCHIVO_JSON + ". ¡Hasta luego!");
                jugando = false;
            } else if (opcionMenu == 3) {
                System.out.println("\nSaliendo del juego sin guardar... ¡Hasta luego!");
                jugando = false;
            } else {
                System.out.println("Opción no válida. Por favor elija 1, 2 o 3.");
            }
        }

        sc.close();
    }

    // Método para serializar (Guardar a JSON)
    private static void guardarPartida(Partida partida) {
        try {
            File archivo = new File(ARCHIVO_JSON);
            if (archivo.getParentFile() != null) {
                archivo.getParentFile().mkdirs();
            }
            mapper.writeValue(archivo, partida);
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    // Método para deserializar (Leer desde JSON)
    private static Partida cargarPartida() {
        try {
            File file = new File(ARCHIVO_JSON);
            if (!file.exists()) {
                System.out.println("No existe ningún archivo de partida guardada (" + ARCHIVO_JSON + ").");
                return null;
            }
            return mapper.readValue(file, Partida.class);
        } catch (IOException e) {
            System.out.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
    }
}