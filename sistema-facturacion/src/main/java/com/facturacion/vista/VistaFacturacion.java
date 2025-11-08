package com.facturacion.vista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class VistaFacturacion {

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TextField txtProducto;

    @FXML
    private TextField txtCantidad;

    @FXML
    private Button btnGenerarFactura;

    @FXML
    private Label lblResultado;

    @FXML
    public void initialize() {
        btnGenerarFactura.setOnAction(event -> generarFactura());
    }

    private void generarFactura() {
        String nombreCliente = txtNombreCliente.getText();
        String producto = txtProducto.getText();
        int cantidad = Integer.parseInt(txtCantidad.getText());

        // Lógica para generar la factura y mostrar el resultado
        lblResultado.setText("Factura generada para " + nombreCliente + " por " + cantidad + " unidades de " + producto);
    }
}