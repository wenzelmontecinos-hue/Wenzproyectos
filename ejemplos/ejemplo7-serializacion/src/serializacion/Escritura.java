package serializacion;

import java.io.*;

public class Escritura {
    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("productos.txt"))) {
            bw.write("Libro;25.5");
            bw.newLine();
            bw.write("Cuaderno;8.0");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        }
    }
}