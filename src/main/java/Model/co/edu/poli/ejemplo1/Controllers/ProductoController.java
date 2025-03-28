package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Model.*;
import Model.co.edu.poli.ejemplo1.Services.ProductoDAO;
import Model.co.edu.poli.ejemplo1.Services.ProductoDAOimp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoController {

    private ProductoDAO productoDAO;

    @FXML
    private Button btnSalir;

    public ProductoController() throws SQLException {
        this.productoDAO = new ProductoDAOimp();
    }

    @FXML
    public void registrarProducto() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Registrar Producto");
        dialog.setHeaderText("Ingrese los detalles del producto");

        dialog.setContentText("ID del producto:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String id = result.get();

        dialog.setContentText("Tipo del producto (Electrico/Alimenticio):");
        result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String tipo = result.get();

        dialog.setContentText("Descripcion del producto:");
        result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String descripcion = result.get();

        ProductoFactory factory;
        Producto producto = null;

        if ("Electrico".equalsIgnoreCase(tipo)) {
            dialog.setContentText("Voltaje del producto:");
            result = dialog.showAndWait();
            if (!result.isPresent()) return;
            String voltaje = result.get();
            factory = new ElectricoFactory();
            producto = factory.crearProducto(id, tipo, descripcion, voltaje);
        } else if ("Alimenticio".equalsIgnoreCase(tipo)) {
            dialog.setContentText("Calorias del producto:");
            result = dialog.showAndWait();
            if (!result.isPresent()) return;
            String calorias = result.get();
            factory = new AlimenticioFactory();
            producto = factory.crearProducto(id, tipo, descripcion, calorias);
        } else {
            showAlert("Tipo de producto no valido.");
            return;
        }

        if (producto != null) {
            productoDAO.registrar(producto);
            showAlert("Producto registrado exitosamente.");
        }
    }

    @FXML
    public void mostrarTodosLosProductos() {
        List<Producto> productos = productoDAO.obtenerTodos();
        StringBuilder sb = new StringBuilder();
        for (Producto producto : productos) {
            sb.append(producto).append("\n");
        }
        showAlert(sb.toString());
    }

    @FXML
    public void actualizarProducto() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Actualizar Producto");
        dialog.setHeaderText("Ingrese el ID del producto a actualizar");

        dialog.setContentText("ID del producto:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String id = result.get();

        Producto producto = productoDAO.obtenerPorId(id);
        if (producto != null) {
            dialog.setContentText("Nueva descripcion del producto:");
            result = dialog.showAndWait();
            if (!result.isPresent()) return;
            String descripcion = result.get();
            producto.setDescripcion(descripcion);

            if (producto instanceof Electrico) {
                dialog.setContentText("Nuevo voltaje del producto:");
                result = dialog.showAndWait();
                if (!result.isPresent()) return;
                String voltaje = result.get();
                ((Electrico) producto).setVoltaje(voltaje);
            } else if (producto instanceof Alimenticio) {
                dialog.setContentText("Nuevas calorias del producto:");
                result = dialog.showAndWait();
                if (!result.isPresent()) return;
                String calorias = result.get();
                ((Alimenticio) producto).setCalorias(calorias);
            }

            productoDAO.actualizar(producto);
            showAlert("Producto actualizado exitosamente.");
        } else {
            showAlert("Producto no encontrado.");
        }
    }

    @FXML
    public void eliminarProducto() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Producto");
        dialog.setHeaderText("Ingrese el ID del producto a eliminar");

        dialog.setContentText("ID del producto:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String id = result.get();

        productoDAO.eliminar(id);
        showAlert("Producto eliminado exitosamente.");
    }

    @FXML
    public void mostrarProductosPorTipo() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Consultar Productos por Tipo");
        dialog.setHeaderText("Ingrese el tipo de producto a consultar");

        dialog.setContentText("Tipo de producto:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String tipo = result.get();

        List<Producto> productos = productoDAO.obtenerPorTipo(tipo);
        StringBuilder sb = new StringBuilder();
        for (Producto producto : productos) {
            sb.append(producto).append("\n");
        }
        showAlert(sb.toString());
    }

    @FXML
    public void clonarProducto() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Clonar Producto");
        dialog.setHeaderText("Ingrese el ID del producto a clonar");

        dialog.setContentText("ID del producto:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String id = result.get();

        Producto producto = productoDAO.obtenerPorId(id);
        if (producto != null) {
            Producto clon = producto.clonar();

            dialog.setContentText("Nuevo ID para el producto clonado:");
            result = dialog.showAndWait();
            if (!result.isPresent()) return;
            String nuevoId = result.get();
            clon.setId(nuevoId);

            dialog.setContentText("Nueva descripcion para el producto clonado:");
            result = dialog.showAndWait();
            if (!result.isPresent()) return;
            String nuevaDescripcion = result.get();
            clon.setDescripcion(nuevaDescripcion);

            if (clon instanceof Electrico) {
                dialog.setContentText("Nuevo voltaje para el producto clonado:");
                result = dialog.showAndWait();
                if (!result.isPresent()) return;
                String nuevoVoltaje = result.get();
                ((Electrico) clon).setVoltaje(nuevoVoltaje);
            } else if (clon instanceof Alimenticio) {
                dialog.setContentText("Nuevas calorias para el producto clonado:");
                result = dialog.showAndWait();
                if (!result.isPresent()) return;
                String nuevasCalorias = result.get();
                ((Alimenticio) clon).setCalorias(nuevasCalorias);
            }

            productoDAO.registrar(clon);
            showAlert("Producto clonado y modificado exitosamente.");
        } else {
            showAlert("Producto no encontrado.");
        }
    }

    @FXML
    public void construirProveedorPasoAPaso() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Construcción de Proveedor");
        dialog.setHeaderText("Ingrese los detalles del proveedor");

        // Paso 1: Evaluación
        dialog.setContentText("Ingrese la puntuación de la evaluación del proveedor:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) return;
        int puntuacion = Integer.parseInt(result.get());

        dialog.setContentText("Ingrese los comentarios de la evaluación del proveedor:");
        result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String comentarios = result.get();

        Evaluacion evaluacion = new Evaluacion(puntuacion, comentarios);
        List<Evaluacion> evaluaciones = List.of(evaluacion);

        // Paso 2: Certificación
        dialog.setContentText("Ingrese el tipo de certificación del proveedor:");
        result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String tipoCertificacion = result.get();

        dialog.setContentText("Ingrese la fecha de la certificación del proveedor:");
        result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String fechaCertificacion = result.get();

        Certificacion certificacion = new Certificacion(tipoCertificacion, fechaCertificacion);
        List<Certificacion> certificaciones = List.of(certificacion);

        // Paso 3: Política de Entrega
        dialog.setContentText("Ingrese el tiempo estimado de entrega del proveedor:");
        result = dialog.showAndWait();
        if (!result.isPresent()) return;
        int tiempoEstimado = Integer.parseInt(result.get());

        dialog.setContentText("Ingrese las condiciones de entrega del proveedor:");
        result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String condiciones = result.get();

        PoliticaDeEntrega politicaDeEntrega = new PoliticaDeEntrega(tiempoEstimado, condiciones);
        List<PoliticaDeEntrega> politicasEntrega = List.of(politicaDeEntrega);

        // Construcción del proveedor paso a paso
        Proveedor proveedor = new Proveedor.ProveedorBuilder()
                .setEvaluaciones(evaluaciones)
                .setCertificaciones(certificaciones)
                .setPoliticasEntrega(politicasEntrega)
                .build();

        // Mostrar resultado final
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Proveedor Construido");
        alert.setHeaderText("Proveedor creado exitosamente");
        alert.setContentText(proveedor.toString());
        alert.showAndWait();
    }



    @FXML
    private void salir() {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}