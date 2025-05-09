package co.edu.poli.ejercicio.Model;

public class Producto {

    private String nombre;
    private double precio;
    private int stock;
    private String idProducto;

    public Producto(String nombre, double precio, int stock, String idProducto) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

}
