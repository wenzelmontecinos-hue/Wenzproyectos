package com.gestion;

import com.gestion.config.ConfiguracionSistema;
import com.gestion.factory.RecursoFactory;
import com.gestion.model.*;
import com.gestion.strategy.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<RecursoHumano> personalDisponible = new ArrayList<>();
        
        boolean salir = false;

        while (!salir) {
            System.out.println("\n====================================================");
            System.out.println("  " + ConfiguracionSistema.getInstancia().getNombreSistema());
            System.out.println("====================================================");
            System.out.println("1. ➕ Registrar nuevo recurso");
            System.out.println("2. 📋 Ver lista completa de personal");
            System.out.println("3. 🔍 Filtrar personal por Ámbito (Gastronomía/Industrial/Militar)");
            System.out.println("4. 🎯 Asignar turno automáticamente (Patrón Strategy)");
            System.out.println("5. 📊 Generar Reporte de Estadísticas y Costos");
            System.out.println("6. 🔄 Cambiar Estado de Disponibilidad (Activo/Inactivo)");
            System.out.println("7. 🚪 Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    registrarPersonal(scanner, personalDisponible);
                    break;
                case 2:
                    mostrarTabla(personalDisponible, "LISTA COMPLETA DE PERSONAL");
                    break;
                case 3:
                    filtrarPorAmbito(scanner, personalDisponible);
                    break;
                case 4:
                    asignarTurno(scanner, personalDisponible);
                    break;
                case 5:
                    generarEstadisticas(personalDisponible);
                    break;
                case 6:
                    cambiarEstadoDisponibilidad(scanner, personalDisponible);
                    break;
                case 7:
                    salir = true;
                    System.out.println("\nSaliendo del sistema...");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        }
        scanner.close();
    }

    // 1. Registro
    private static void registrarPersonal(Scanner scanner, List<RecursoHumano> personal) {
        System.out.println("\n--- REGISTRO DE PERSONAL ---");
        System.out.println("1. 🍳 Gastronomía | 2. 🏭 Industrial | 3. 🪖 Militar");
        System.out.print("Elija el ámbito: ");
        int tipo = leerEntero(scanner);

        String ambito = (tipo == 1) ? "GASTRONOMIA" : (tipo == 2) ? "INDUSTRIAL" : (tipo == 3) ? "MILITAR" : "";
        if (ambito.isEmpty()) {
            System.out.println("❌ Ámbito no válido.");
            return;
        }

        System.out.print("ID / Código: ");
        String id = scanner.nextLine();
        System.out.print("Nombre Completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Horas Acumuladas Iniciales: ");
        int horas = leerEntero(scanner);
        System.out.print("Tarifa Base por Hora ($): ");
        double tarifa = leerDouble(scanner);
        System.out.print("Detalle Especial (Especialidad/Rango/Área): ");
        String extra = scanner.nextLine();

        try {
            personal.add(RecursoFactory.crearRecurso(ambito, id, nombre, horas, tarifa, extra));
            System.out.println("✅ Personal registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // 2. Tabla reutilizable
    private static void mostrarTabla(List<RecursoHumano> lista, String titulo) {
        System.out.println("\n==========================================================================================");
        System.out.println("                              📋 " + titulo);
        System.out.println("==========================================================================================");

        if (lista.isEmpty()) {
            System.out.println(" ⚠️ No hay registros para mostrar.");
            System.out.println("==========================================================================================");
            return;
        }

        System.out.printf("| %-6s | %-22s | %-10s | %-10s | %-14s | %-10s |\n", 
                          "ID", "NOMBRE", "HORAS ACUM", "TARIFA/H", "ÁMBITO", "ESTADO");
        System.out.println("------------------------------------------------------------------------------------------");

        for (RecursoHumano r : lista) {
            String estadoStr = r.isDisponible() ? "🟢 Activo" : "🔴 Inactivo";
            System.out.printf("| %-6s | %-22s | %-10s | %-10s | %-14s | %-10s |\n",
                              r.getId(),
                              r.getNombre(),
                              r.getHorasAcumuladas() + "h",
                              "$" + r.getTarifaBaseHora(),
                              r.getClass().getSimpleName().replace("Personal", "").replace("Empleado", "").replace("Operario", ""),
                              estadoStr);
        }
        System.out.println("==========================================================================================");
    }

    // 3. Filtrado por Ámbito
    private static void filtrarPorAmbito(Scanner scanner, List<RecursoHumano> personal) {
        System.out.println("\n--- FILTRAR POR ÁMBITO ---");
        System.out.println("1. Gastronomía | 2. Industrial | 3. Militar");
        System.out.print("Seleccione opción: ");
        int op = leerEntero(scanner);

        List<RecursoHumano> filtrados = new ArrayList<>();
        for (RecursoHumano r : personal) {
            if (op == 1 && r instanceof EmpleadoGastronomico) filtrados.add(r);
            else if (op == 2 && r instanceof OperarioIndustrial) filtrados.add(r);
            else if (op == 3 && r instanceof PersonalMilitar) filtrados.add(r);
        }

        mostrarTabla(filtrados, "RESULTADOS DEL FILTRO");
    }

    // 4. Asignación con Strategy (Validando disponibilidad)
    private static void asignarTurno(Scanner scanner, List<RecursoHumano> personal) {
        // Filtrar solo los disponibles primero
        List<RecursoHumano> disponibles = new ArrayList<>();
        for (RecursoHumano r : personal) {
            if (r.isDisponible()) disponibles.add(r);
        }

        if (disponibles.isEmpty()) {
            System.out.println("❌ No hay personal activo/disponible para asignar turnos.");
            return;
        }

        System.out.print("\nIngrese duración del turno (horas): ");
        int horasTurno = leerEntero(scanner);

        System.out.println("Seleccione Estrategia: 1. Equidad de Horas | 2. Optimización de Costo");
        int opEstrategia = leerEntero(scanner);

        EstrategiaSeleccion estrategia = (opEstrategia == 1) ? new EstrategiaEquidadHoras() : new EstrategiaMenorCosto();
        RecursoHumano asignado = estrategia.seleccionarMejorCandidato(disponibles, horasTurno);

        if (asignado != null) {
            System.out.println("\n🎯 CANDIDATO SELECCIONADO: " + asignado.getNombre() + " (ID: " + asignado.getId() + ")");
            System.out.println("Costo Estimado para " + horasTurno + "h: $" + asignado.calcularCostoTurno(horasTurno));

            System.out.print("¿Confirmar asignación? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                asignado.registrarHoras(horasTurno);
                System.out.println("✅ Horas sumadas correctamente.");
            }
        }
    }

    // 5. Módulo de Estadísticas y Analítica
    private static void generarEstadisticas(List<RecursoHumano> personal) {
        System.out.println("\n====================================================");
        System.out.println("        📊 REPORTE DE ESTADÍSTICAS DEL SISTEMA      ");
        System.out.println("====================================================");

        if (personal.isEmpty()) {
            System.out.println("No hay datos para calcular estadísticas.");
            return;
        }

        int totalHoras = 0;
        double costoHoraPromedio = 0;
        RecursoHumano masTrabajador = personal.get(0);

        for (RecursoHumano r : personal) {
            totalHoras += r.getHorasAcumuladas();
            costoHoraPromedio += r.getTarifaBaseHora();

            if (r.getHorasAcumuladas() > masTrabajador.getHorasAcumuladas()) {
                masTrabajador = r;
            }
        }

        costoHoraPromedio /= personal.size();

        System.out.println("• Total de Recursos Registrados: " + personal.size());
        System.out.println("• Acumulado Global de Horas: " + totalHoras + " hrs");
        System.out.printf("• Tarifa Promedio por Hora: $%.2f\n", costoHoraPromedio);
        System.out.println("• Recurso con Mayor Carga Laboral: " + masTrabajador.getNombre() + " (" + masTrabajador.getHorasAcumuladas() + " hrs)");
        System.out.println("====================================================");
    }

    // 6. Cambiar Disponibilidad
    private static void cambiarEstadoDisponibilidad(Scanner scanner, List<RecursoHumano> personal) {
        System.out.print("\nIngrese el ID del recurso a cambiar estado: ");
        String id = scanner.nextLine();

        for (RecursoHumano r : personal) {
            if (r.getId().equalsIgnoreCase(id)) {
                r.setDisponible(!r.isDisponible());
                System.out.println("✅ Estado actualizado. Nuevo estado de " + r.getNombre() + ": " + (r.isDisponible() ? "🟢 Activo" : "🔴 Inactivo"));
                return;
            }
        }
        System.out.println("❌ ID no encontrado.");
    }

    private static int leerEntero(Scanner scanner) {
        try { return Integer.parseInt(scanner.nextLine()); } catch (Exception e) { return -1; }
    }

    private static double leerDouble(Scanner scanner) {
        try { return Double.parseDouble(scanner.nextLine()); } catch (Exception e) { return 0.0; }
    }
}