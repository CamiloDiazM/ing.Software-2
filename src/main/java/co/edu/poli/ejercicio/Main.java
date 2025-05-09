package co.edu.poli.ejercicio;

import co.edu.poli.ejercicio.Model.*;

public class Main {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Samantha", "sam", "21");

        // Crear pedido para el cliente
        Pedido pedido = new Pedido(cliente);

        // Crear GestorPedido con el pedido
        GestorPedido gestor = new GestorPedido(pedido);

        // Crear productos
        Producto p1 = new Producto("Laptop", 300000, 10, "1"); // 10 en stock
        Producto p2 = new Producto("Mouse", 20000, 5, "2"); // 5 en stock

        // Establecer estrategia de cálculo de total
        gestor.setEstrategia(new CalculoSinDescuento()); // Aquí podrías cambiar la estrategia de cálculo

        // Agregar productos al pedido
        System.out.println("\n--- Agregar productos ---");
        gestor.agregarProducto(p1, 2); // Agregar 2 laptops
        gestor.agregarProducto(p2, 3); // Agregar 3 mouse

        // Calcular total
        System.out.println("\n--- Calcular total ---");
        gestor.calcularTotal();

        // Reducir stock después de la compra
        System.out.println("\n--- Reducir stock ---");
        gestor.reducirStock();

        // Mostrar estado final de stock
        System.out.println("\n--- Estado final de stock ---");
        System.out.println(p1.getNombre() + " stock restante: " + p1.getStock());
        System.out.println(p2.getNombre() + " stock restante: " + p2.getStock());

        // Configurar Chain of Responsibility para procesar el pedido
        System.out.println("\n--- Procesar pedido ---");
        Handler handlerDescuento = new DescuentoHandler();
        Handler handlerFactura = new FacturaHandler();
        Handler handlerStock = new StockHandler();

        // Configurar la cadena de responsabilidad
        handlerDescuento.setNext(handlerFactura);
        handlerFactura.setNext(handlerStock);

        gestor.setHandlerInicial(handlerDescuento);

        // Procesar pedido (usando Chain of Responsibility)
        gestor.procesarPedido();
    }

}
