package serializacion;

import java.io.*;

public class Lectura {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("productos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                System.out.println("Producto: " + partes[0] +
                        " | Precio: " + partes[1]);
            }
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
    }
}