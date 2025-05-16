package co.edu.poli.ejercicio;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;
import co.edu.poli.ejercicio.Model.Mediator.MediadorConcreto;
import co.edu.poli.ejercicio.Model.Mediator.Mediator;
import co.edu.poli.ejercicio.Model.Visitor.VisitorImpl;

public class Main {

    public static void main(String[] args) {
        Producto producto1 = new Producto("Laptop", 1000);
        Producto producto2 = new Producto("Smartphone", 800);

        Cliente cliente = new Cliente("Samuel", "1");
        Pedido pedido = new Pedido(cliente);

        // producto1.aceptar(new VisitorImpl());
        // producto2.aceptar(new VisitorImpl());

        // pedido.aceptar(new VisitorImpl());

        /*
         * Mediator
         */

        MediadorConcreto mediator = new MediadorConcreto();
        mediator.crearPedido(cliente); // hay que crear el pedido por que sino saca error xd
        mediator.agregarProductoAlPedido(cliente, producto1);
        mediator.agregarProductoAlPedido(cliente, producto2);
        mediator.eliminarProductoDelPedido(cliente, producto1);
        mediator.agregarProductoAlPedido(cliente, producto1);
        pedido = mediator.obtenerPedido(cliente);
        pedido.aceptar(new VisitorImpl());
        mediator.aplicarDescuento(cliente, 0.2);

        /*
         * Patron state
         * Profe wilson lo voy a extrañar :(
         */

    }

}
