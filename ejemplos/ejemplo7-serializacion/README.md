# Unidad 7: Entrada/Salida, Archivos y Serialización

Este repositorio contiene la implementación práctica y los conceptos fundamentales sobre el manejo de persistencia, operaciones de entrada/salida (E/S) y serialización de datos en Java[cite: 1].

---

## Conceptos Teóricos

### 1. Persistencia y Flujo de Archivos (E/S)
La **persistencia** es la capacidad de guardar la información más allá del tiempo de ejecución de un programa[cite: 1]. Java maneja la entrada/salida mediante **Streams** (flujos de datos)[cite: 1]:
* **Byte Streams:** Manejan datos binarios de 8 bits (imágenes, archivos comprimidos) mediante `InputStream` y `OutputStream`[cite: 1].
* **Character Streams:** Manejan datos de texto de 16 bits (Unicode) mediante `Reader` y `Writer`[cite: 1].

> **Buena práctica:** Siempre se debe utilizar la estructura `try-with-resources` para garantizar el cierre automático de los flujos de archivos[cite: 1].

### 2. Buffers y Rendimiento
Un **Buffer** es un espacio de memoria intermedia que acumula datos y los transfiere en bloques[cite: 1]. Reduce drásticamente las operaciones directas con el disco duro (que son costosas en tiempo), mejorando significativamente el rendimiento de la aplicación[cite: 1].
* **Ejemplo:** Envolver un `FileReader` dentro de un `BufferedReader` permite leer líneas completas (`readLine()`) en lugar de procesar carácter por carácter[cite: 1].

### 3. Serialización y Deserialización
* **Serialización:** Proceso de transformar un objeto en memoria a un formato almacenable (como texto o binario) o transmisible por red[cite: 1].
* **Deserialización:** Proceso inverso que reconstruye el objeto en memoria a partir de los datos almacenados[cite: 1].

### 4. JSON vs. XML vs. Serialización Binaria
* **JSON:** Formato ligero, compacto y de alta legibilidad, dominante en APIs REST y microservicios[cite: 1].
* **XML:** Formato basado en etiquetas, ideal para documentos con estructuras complejas o integraciones bancarias/empresariales[cite: 1].
* **Serialización Nativa (Java):** Se implementa mediante la interfaz `Serializable` para generar archivos binarios (`.bin`) de uso interno[cite: 1].

---

## Requisitos Previos

* **Java Development Kit (JDK 17+)**
* **Librerías de Jackson (v2.17.0):**
  * `jackson-databind-2.17.0.jar`[cite: 1]
  * `jackson-core-2.17.0.jar`
  * `jackson-annotations-2.17.0.jar`

---

## Estrategia de Archivos del Proyecto

```text
ejemplo7-serializacion/
├── lib/
│   ├── jackson-annotations-2.17.0.jar
│   ├── jackson-core-2.17.0.jar
│   └── jackson-databind-2.17.0.jar
├── src/
│   └── serializacion/
│       ├── Producto.java
│       └── JsonEjemplo.java
├── bin/
└── README.md
```
