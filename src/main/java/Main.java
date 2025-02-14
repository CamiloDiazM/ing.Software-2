import Model.co.edu.poli.ejemplo1.model.Cliente;
import Model.co.edu.poli.ejemplo1.model.Pedido;
import Model.co.edu.poli.ejemplo1.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     Camilo Andres Diaz
     Andres David Gonzalez
     Samuel Cajica Pacheco
     */

    public static void main(String[] args) {

        Cliente cliente = new Cliente("1", "Juan Perez");

        Producto producto1 = new Producto("101", "Producto A");
        Producto producto2 = new Producto("102", "Producto B");

        List<Producto> productos = new ArrayList<>();
        productos.add(producto1);
        productos.add(producto2);

        Pedido pedido = new Pedido("001", cliente, "2023-10-01", productos);

        System.out.println(pedido);


    }
}
