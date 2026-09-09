# 🚀 Sistema Multi-Dominio de Gestión de Recursos y Turnos

> **Versión:** 1.0.0  
> **Lenguaje:** Java 17+  
> **Arquitectura:** Orientada a Objetos (POO) & Patrones de Diseño Gang of Four (GoF)

---

## 📋 Tabla de Contenidos
1. [Descripción del Proyecto](#-descripción-del-proyecto)
2. [Justificación y Caso de Uso](#-justificación-y-caso-de-uso)
3. [Características Principales](#-características-principales)
4. [Stack Tecnológico](#-stack-tecnológico)
5. [Arquitectura y Patrones de Diseño](#-arquitectura-y-patrones-de-diseño)
6. [Diagramas del Sistema (Mermaid)](#-diagramas-del-sistema-mermaid)
7. [Estructura del Proyecto](#-estructura-del-proyecto)
8. [Explicación Técnica del Código](#-explicación-técnica-del-código)
9. [Requisitos e Instalación](#-requisitos-e-instalación)
10. [Guía de Uso](#-guía-de-uso)
11. [Manejo de Errores y Validaciones](#-manejo-de-errores-y-validaciones)
12. [Futuras Mejoras](#-futuras-mejoras)
13. [Licencia y Contacto](#-licencia-y-contacto)

---

## 📖 Descripción del Proyecto

El **Sistema Multi-Dominio de Gestión de Recursos y Turnos** es una solución de software desarrollada en Java diseñada para optimizar y automatizar la administración de personal en organizaciones heterogéneas. La aplicación permite integrar múltiples sectores operativos (militar, industrial, gastronómico) bajo un solo marco de control de disponibilidad, cálculo de costos de turnos y asignación inteligente.

---

## 🎯 Justificación y Caso de Uso

### ¿Por qué se elige este proyecto?
* **Problemática Real:** Muchas organizaciones administran áreas operativas con reglas salariales y de asignación radicalmente distintas en hojas de cálculo separadas, lo que genera errores y desbalance en las cargas horarias.
* **Flexibilidad Operativa:** Permite cambiar el criterio de asignación de personal (equidad de carga horaria vs. minimización de costo) en tiempo de ejecución sin reiniciar el sistema.
* **Alineación con Buenas Prácticas:** Aplica principios **SOLID**, desacoplamiento de componentes, modularidad y reutilización de código mediante Herencia y Polimorfismo.

### ¿Qué hace al final la aplicación?
Al ser ejecutado, el programa despliega un panel interactivo por consola que permite:
* Registrar personal especializado aplicando tarifas e incentivos según su sector.
* Consultar listados consolidados y realizar filtrados por ámbito.
* Asignar turnos de manera automatizada evaluando algoritmos de decisión en tiempo real.
* Generar métricas estadísticas financieras y operativas del estado global de la entidad.

---

## ✨ Características Principales

* 🏢 **Gestión Multi-Sector:** Soporte integrado para personal Militar, Gastronómico e Industrial.
* 🤖 **Asignación Algorítmica (Strategy):** Cambia entre estrategias de selección por equidad de horas o por menor costo financiero.
* 🔒 **Garantía de Configuración Única (Singleton):** Acceso global controlado a la configuración general del sistema.
* 🏭 **Creación Desacoplada (Factory):** Instanciación estandarizada que evita errores de creación manual.
* 📊 **Módulo de Estadísticas:** Reporte de horas totales, promedios salariales y porcentaje de disponibilidad.

---

## 🛠️ Stack Tecnológico

| Tecnología | Herramienta / Uso |
| :--- | :--- |
| **Lenguaje principal** | Java 17 (o superior) |
| **Paradigmas** | Programación Orientada a Objetos (POO), Patrones GoF |
| **Modelado de diagramas** | Mermaid.js / UML |
| **Entorno recomendado** | Visual Studio Code / IntelliJ IDEA / Eclipse |
| **Control de versiones** | Git / GitHub |

---

## 📐 Arquitectura y Patrones de Diseño

El sistema aplica tres patrones de diseño creacionales y de comportamiento fundamentales:

1. **Singleton (`ConfiguracionSistema`):** Encapsula los parámetros del sistema asegurando una sola instancia en la JVM.
2. **Factory Method (`RecursoFactory`):** Desacopla al menú principal de la lógica de instanciación de las subclases de `RecursoHumano`.
3. **Strategy (`EstrategiaSeleccion`):** Interfaz que define el contrato de asignación, permitiendo que `EstrategiaEquidadHoras` y `EstrategiaMenorCosto` sean intercambiables.

---

## 📊 Diagramas del Sistema (Mermaid)

### Casos de Uso
```mermaid
graph TD
    user((Administrador / Operador))

    subgraph Sistema ["Sistema de Gestión de Recursos y Turnos"]
        UC1["UC1: Registrar Nuevo Recurso"]
        UC2["UC2: Listar Personal Registrado"]
        UC3["UC3: Filtrar Personal por Ámbito"]
        UC4["UC4: Asignar Turno Automático"]
        UC5["UC5: Generar Reporte Estadístico"]
        UC6["UC6: Cambiar Estado de Disponibilidad"]
        
        UC4_1["Algoritmo: Equidad de Horas"]
        UC4_2["Algoritmo: Menor Costo"]
    end

    user --> UC1
    user --> UC2
    user --> UC3
    user --> UC4
    user --> UC5
    user --> UC6

    UC4 -.->|include| UC4_1
    UC4 -.->|include| UC4_2


##  De clases

    classDiagram
    namespace config {
        class ConfiguracionSistema {
            -ConfiguracionSistema instancia$
            -String nombreSistema
            -ConfiguracionSistema()
            +getInstancia() ConfiguracionSistema$
            +getNombreSistema() String
        }
    }

    namespace model {
        class RecursoHumano {
            <<abstract>>
            -String id
            -String nombre
            -int horasAcumuladas
            -double tarifaBaseHora
            -boolean disponible
            +RecursoHumano(id, nombre, horas, tarifa)
            +calcularCostoTurno(horasTurno)* double
            +getId() String
            +getNombre() String
            +getHorasAcumuladas() int
            +getTarifaBaseHora() double
            +isDisponible() boolean
            +setDisponible(disponible) void
            +registrarHoras(horas) void
        }

        class EmpleadoGastronomico {
            -String areaTrabajo
            +EmpleadoGastronomico(id, nombre, horas, tarifa, area)
            +calcularCostoTurno(horasTurno) double
        }

        class OperarioIndustrial {
            -String especialidadMaquinaria
            +OperarioIndustrial(id, nombre, horas, tarifa, especialidad)
            +calcularCostoTurno(horasTurno) double
        }

        class PersonalMilitar {
            -String rangoOficialia
            +PersonalMilitar(id, nombre, horas, tarifa, rango)
            +calcularCostoTurno(horasTurno) double
        }
    }

    namespace factory {
        class RecursoFactory {
            +crearRecurso(ambito, id, nombre, horas, tarifa, extra)$ RecursoHumano
        }
    }

    namespace strategy {
        class EstrategiaSeleccion {
            <<interface>>
            +seleccionarMejorCandidato(candidatos, horasTurno)* RecursoHumano
        }

        class EstrategiaEquidadHoras {
            +seleccionarMejorCandidato(candidatos, horasTurno) RecursoHumano
        }

        class EstrategiaMenorCosto {
            +seleccionarMejorCandidato(candidatos, horasTurno) RecursoHumano
        }
    }

    RecursoHumano <|-- EmpleadoGastronomico
    RecursoHumano <|-- OperarioIndustrial
    RecursoHumano <|-- PersonalMilitar

    EstrategiaSeleccion <|.. EstrategiaEquidadHoras
    EstrategiaSeleccion <|.. EstrategiaMenorCosto

    RecursoFactory ..> RecursoHumano : instanciamiento
    EstrategiaSeleccion ..> RecursoHumano : evalua


📁 Estructura del Proyecto

    src/
└── com/
    └── gestion/
        ├── Main.java                        # Punto de entrada de la aplicación
        ├── config/
        │   └── ConfiguracionSistema.java    # Patrón Singleton
        ├── factory/
        │   └── RecursoFactory.java          # Patrón Factory Method
        ├── model/
        │   ├── RecursoHumano.java           # Clase Abstracta Base
        │   ├── EmpleadoGastronomico.java    # Subclase concreta
        │   ├── OperarioIndustrial.java      # Subclase concreta
        │   └── PersonalMilitar.java         # Subclase concreta
        └── strategy/
            ├── EstrategiaSeleccion.java     # Interfaz Strategy
            ├── EstrategiaEquidadHoras.java  # Algoritmo Equidad
            └── EstrategiaMenorCosto.java    # Algoritmo Costo Mínimo

Prerrequisitos
Java Development Kit (JDK): Versión 17 o superior instalada y configurada en las variables de entorno (JAVA_HOME).

Git: Para clonar el repositorio.

Pasos de Instalación y Compilación
Clonar el repositorio:

git clone [https://github.com/tu-usuario/gestion-recursos-turnos.git](https://github.com/tu-usuario/gestion-recursos-turnos.git)

cd gestion-recursos-turnos
Compilar el código fuente:

javac -d bin src/com/gestion/**/*.java src/com/gestion/Main.java
Ejecutar la aplicación:

java -cp bin com.gestion.Main


🎮 Guía de Uso
Al arrancar la aplicación, se desplegará el menú interactivo en consola:

Plaintext
====================================================
  Sistema Multi-Dominio de Gestión de Recursos y Turnos
====================================================
1. ➕ Registrar nuevo recurso
2. 📋 Ver lista completa de personal
3. 🔍 Filtrar personal por Ámbito
4. 🎯 Asignar turno automáticamente (Strategy)
5. 📊 Generar Reporte de Estadísticas
6. 🔄 Cambiar Estado de Disponibilidad
7. 🚪 Salir
Seleccione una opción: 


⚠️ Manejo de Errores y Validaciones
Validación de Entradas (Scanner): Limpieza de búfer de teclado y manejo de excepciones mediante bloques try-catch para evitar fallos si el usuario ingresa letras en lugar de números.

Control de Argumentos Nulos (IllegalArgumentException): La fábrica de creación verifica que los ámbitos o cadenas requeridas no estén vacías antes de intentar construir un objeto.

Verificación de Lista Vacía: Los algoritmos de selección retornan valores controlados (null o listas vacías) mostrando mensajes de advertencia si no existen candidatos elegibles.


🔮 Futuras Mejoras
[ ] Persistence de datos conectando una base de datos MySQL / PostgreSQL vía JDBC.

[ ] Implementación de una interfaz gráfica de usuario (GUI) usando JavaFX o Swing.

[ ] Exportación automática de reportes estadísticos a archivos PDF o Excel (.xlsx).

[ ] Autenticación de usuarios por roles (Administrador, Operador, Auditor).

📜 Licencia y Contacto
Este proyecto está bajo la Licencia MIT. Puedes consultar el archivo LICENSE para obtener más detalles.

Desarrollador: Grupo 2

Contacto / Repositorio: GitHub Profile

