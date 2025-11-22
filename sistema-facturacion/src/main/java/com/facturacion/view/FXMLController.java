package com.facturacion.view;

import com.facturacion.facade.InvoiceSystemFacade;
import com.facturacion.model.invoices.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase controladora para la interfaz gráfica de facturación
 * Maneja la interacción entre la interfaz y el sistema de facturación
 */
public class FXMLController {
    
    @FXML private TextField customerNameField;      // Campo de nombre del cliente
    @FXML private TextField customerContactField;   // Campo de contacto del cliente
    @FXML private TextField productNameField;       // Campo de nombre del producto
    @FXML private TextField productPriceField;      // Campo de precio del producto
    @FXML private TextField quantityField;          // Campo de cantidad
    @FXML private ComboBox<String> invoiceTypeCombo; // Selector de tipo de factura
    @FXML private TextArea invoiceDisplayArea;      // Área de visualización de facturas
    @FXML private Label statusLabel;                // Etiqueta de estado
    
    private InvoiceSystemFacade invoiceSystem;      // Sistema de facturación
    private ObservableList<Invoice> invoiceList;    // Lista de facturas para la tabla
    
    /**
     * Método de inicialización llamado automáticamente por JavaFX
     * Configura la interfaz y el sistema de facturación
     */
    @FXML
    public void initialize() {
        // Inicializar el sistema de facturación
        invoiceSystem = new InvoiceSystemFacade();
        
        // Configurar el combo box de tipos de factura
        ObservableList<String> types = FXCollections.observableArrayList("Producto", "Servicio");
        invoiceTypeCombo.setItems(types);
        invoiceTypeCombo.setValue("Producto"); // Valor por defecto
        
        // Inicializar la lista de facturas
        invoiceList = FXCollections.observableArrayList();
        
        // Limpiar el área de visualización
        invoiceDisplayArea.clear();
        updateStatus("Sistema inicializado correctamente");
    }
    
    /**
     * Maneja el evento de crear una factura
     * Se ejecuta cuando el usuario presiona el botón de crear factura
     */
    @FXML
    private void handleCreateInvoice() {
        try {
            // Obtener los valores de los campos
            String customerName = customerNameField.getText().trim();
            String customerContact = customerContactField.getText().trim();
            String productName = productNameField.getText().trim();
            String priceText = productPriceField.getText().trim();
            String quantityText = quantityField.getText().trim();
            String invoiceType = invoiceTypeCombo.getValue();
            
            // Validar que todos los campos estén completos
            if (customerName.isEmpty() || customerContact.isEmpty() || 
                productName.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
                showAlert("Error", "Por favor complete todos los campos", Alert.AlertType.ERROR);
                return;
            }
            
            // Convertir valores numéricos
            double price = Double.parseDouble(priceText);
            double quantity = Double.parseDouble(quantityText);
            
            // Validar que los valores sean positivos
            if (price <= 0 || quantity <= 0) {
                showAlert("Error", "El precio y la cantidad deben ser mayores que cero", Alert.AlertType.ERROR);
                return;
            }
            
            // Crear la factura según el tipo
            Invoice invoice;
            if ("Servicio".equals(invoiceType)) {
                invoice = invoiceSystem.createServiceInvoice(customerName, customerContact, 
                                                           productName, price, quantity);
            } else {
                invoice = invoiceSystem.createProductInvoice(customerName, customerContact, 
                                                           productName, price, (int) quantity);
            }
            
            // Agregar a la lista y mostrar
            invoiceList.add(invoice);
            displayInvoice(invoice);
            updateStatus("Factura creada exitosamente: " + invoice.getNumber());
            
            // Limpiar algunos campos para facilitar la siguiente factura
            clearProductFields();
            
        } catch (NumberFormatException e) {
            showAlert("Error", "Por favor ingrese valores numéricos válidos", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Error al crear la factura: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    /**
     * Maneja el evento de mostrar todas las facturas
     */
    @FXML
    private void handleShowAllInvoices() {
        invoiceDisplayArea.clear();
        java.util.List<Invoice> allInvoices = invoiceSystem.getAllInvoices();
        
        if (allInvoices.isEmpty()) {
            invoiceDisplayArea.setText("No hay facturas registradas");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("=== LISTADO DE TODAS LAS FACTURAS ===\n\n");
            for (Invoice invoice : allInvoices) {
                sb.append(invoice.toString()).append("\n");
                sb.append("----------------------------------------\n");
            }
            sb.append("\nTotal de facturas: ").append(allInvoices.size());
            invoiceDisplayArea.setText(sb.toString());
        }
        updateStatus("Mostrando todas las facturas");
    }
    
    /**
     * Maneja el evento de limpiar la pantalla
     */
    @FXML
    private void handleClearScreen() {
        invoiceDisplayArea.clear();
        updateStatus("Pantalla limpiada");
    }
    
    /**
     * Muestra una factura en el área de visualización
     * @param invoice Factura a mostrar
     */
    private void displayInvoice(Invoice invoice) {
        invoiceDisplayArea.appendText("=== NUEVA FACTURA CREADA ===\n");
        invoiceDisplayArea.appendText(invoice.toString());
        invoiceDisplayArea.appendText("\n============================\n\n");
    }
    
    /**
     * Limpia los campos relacionados con el producto
     */
    private void clearProductFields() {
        productNameField.clear();
        productPriceField.clear();
        quantityField.clear();
    }
    
    /**
     * Muestra una alerta al usuario
     * @param title Título de la alerta
     * @param message Mensaje de la alerta
     * @param type Tipo de alerta
     */
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Actualiza la etiqueta de estado
     * @param message Mensaje a mostrar
     */
    private void updateStatus(String message) {
        statusLabel.setText("Estado: " + message);
    }
}