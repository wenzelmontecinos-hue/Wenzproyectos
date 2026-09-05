package com.geometria;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geometria.model.Circulo;
import com.geometria.model.FiguraGeometrica;
import com.geometria.model.Rectangulo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ARCHIVO_JSON = "figuras.json";
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final List<FiguraGeometrica> figuras = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    crearRectangulo();
                    break;
                case 2:
                    crearCirculo();
                    break;
                case 3:
                    mostrarFiguras();
                    break;
                case 4:
                    guardarEnJson();
                    break;
                case 5:
                    cargarDesdeJson();
                    break;
                case 6:
                    System.out.println("\n¡Gracias por usar la aplicación de Geometría!");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente nuevamente.");
            }
            System.out.println();
        } while (opcion != 6);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("==================================");
        System.out.println("   SISTEMA DE GEOMETRÍA (CONSOLA) ");
        System.out.println("==================================");
        System.out.println("1. Crear Rectángulo");
        System.out.println("2. Crear Círculo");
        System.out.println("3. Ver figuras creadas");
        System.out.println("4. Guardar figuras en JSON");
        System.out.println("5. Cargar figuras desde JSON");
        System.out.println("6. Salir");
        System.out.println("==================================");
    }

    private static void crearRectangulo() {
        System.out.println("\n--- Nuevo Rectángulo ---");
        double base = leerDoublePositivo("Ingrese la base: ");
        double altura = leerDoublePositivo("Ingrese la altura: ");

        // Polimorfismo: Referencia de clase abstracta apuntando a objeto hijo
        FiguraGeometrica rectangulo = new Rectangulo(base, altura);
        figuras.add(rectangulo);
        mostrarResultado(rectangulo);
    }

    private static void crearCirculo() {
        System.out.println("\n--- Nuevo Círculo ---");
        double radio = leerDoublePositivo("Ingrese el radio: ");

        FiguraGeometrica circulo = new Circulo(radio);
        figuras.add(circulo);
        mostrarResultado(circulo);
    }

    private static void mostrarResultado(FiguraGeometrica figura) {
        System.out.println("\nResultado:");
        System.out.println(figura.toString());
    }

    private static void mostrarFiguras() {
        System.out.println("\n--- Figuras creadas ---");
        if (figuras.isEmpty()) {
            System.out.println("No hay figuras registradas.");
        } else {
            for (int i = 0; i < figuras.size(); i++) {
                System.out.println((i + 1) + ". " + figuras.get(i));
            }
        }
    }

    private static void guardarEnJson() {
        try (Writer writer = new FileWriter(ARCHIVO_JSON)) {
            // Serializar la lista de figuras → JSON (TypeReference para conservar el polimorfismo "tipo")
            mapper.writerFor(new TypeReference<List<FiguraGeometrica>>() {})
                    .withDefaultPrettyPrinter()
                    .writeValue(writer, figuras);
            System.out.println("\nSe guardaron " + figuras.size() + " figuras en " + ARCHIVO_JSON + ".");
        } catch (IOException e) {
            System.out.println("\nError al guardar: " + e.getMessage());
        }
    }

    private static void cargarDesdeJson() {
        File archivo = new File(ARCHIVO_JSON);
        if (!archivo.exists()) {
            System.out.println("\nEl archivo " + ARCHIVO_JSON + " no existe. Cree figuras y guárdelas primero.");
            return;
        }

        try (Reader reader = new FileReader(archivo)) {
            // Deserializar JSON → lista de figuras
            List<FiguraGeometrica> cargadas = mapper.readValue(reader, new TypeReference<List<FiguraGeometrica>>() {});
            figuras.clear();
            figuras.addAll(cargadas);
            System.out.println("\nSe cargaron " + figuras.size() + " figuras desde " + ARCHIVO_JSON + ".");
        } catch (IOException e) {
            System.out.println("\nError al cargar: " + e.getMessage());
        }
    }

    private static double leerDoublePositivo(String mensaje) {
        double valor;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                if (valor > 0) {
                    break;
                } else {
                    System.out.println("Error: El número debe ser mayor a 0.");
                }
            } else {
                System.out.println("Error: Por favor ingrese un número válido.");
                scanner.next(); // Limpiar entrada incorrecta
            }
        }
        return valor;
    }

    private static int leerEntero(String mensaje) {
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Ingrese un número entero válido.");
            scanner.next();
            System.out.print(mensaje);
        }
        return scanner.nextInt();
    }
}