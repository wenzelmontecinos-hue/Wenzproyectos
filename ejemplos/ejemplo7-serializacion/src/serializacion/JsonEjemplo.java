package serializacion;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonEjemplo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Serializar objeto → JSON
        Producto p = new Producto(1, "CIEN ANOS DE SOLEDAD", 25.0, 47);
        String json = mapper.writeValueAsString(p);
        System.out.println(json); // {"id":1,"nombre":"Libro","precio":25.5}
        
        // Deserializar JSON → objeto
        Producto restaurado = mapper.readValue(json, Producto.class);
        System.out.println("------------------");
        System.out.println(restaurado.getPrecio());
        System.out.println(restaurado.getNombre());
        System.out.println(restaurado.getPrecio());

    }
}