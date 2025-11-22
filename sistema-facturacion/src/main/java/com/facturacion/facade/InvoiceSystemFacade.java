package com.facturacion.facade;

import com.facturacion.controller.InvoiceController;
import com.facturacion.model.customer.Customer;
import com.facturacion.model.invoices.Invoice;
import com.facturacion.model.product.Product;
import java.util.List;

/**
 * Clase que implementa el patrón Facade para simplificar el sistema de facturación
 * Proporciona una interfaz unificada para las operaciones del sistema
 */
public class InvoiceSystemFacade {
    private InvoiceController controller;  // Controlador del sistema
    
    /**
     * Constructor del facade
     * Inicializa el controlador del sistema
     */
    public InvoiceSystemFacade() {
        this.controller = new InvoiceController();
    }
    
    /**
     * Crea una factura completa de forma simplificada
     * @param customerName Nombre del cliente
     * @param customerContact Contacto del cliente
     * @param productName Nombre del producto
     * @param productPrice Precio del producto
     * @param quantity Cantidad del producto
     * @return Factura creada
     */
    public Invoice createCompleteInvoice(String customerName, String customerContact, 
                                       String productName, double productPrice, int quantity) {
        // Crear cliente
        Customer customer = controller.createCustomer(customerName, customerContact);
        
        // Crear producto
        Product product = controller.createProduct(productName, productPrice, quantity);
        
        // Crear factura
        return controller.createProductInvoice(customer, product, quantity);
    }
    
    /**
     * Crea una factura de producto
     * @param customerName Nombre del cliente
     * @param customerContact Contacto del cliente
     * @param productName Nombre del producto
     * @param productPrice Precio del producto
     * @param quantity Cantidad del producto
     * @return Factura de producto creada
     */
    public Invoice createProductInvoice(String customerName, String customerContact,
                                      String productName, double productPrice, int quantity) {
        Customer customer = controller.createCustomer(customerName, customerContact);
        Product product = controller.createProduct(productName, productPrice, quantity);
        return controller.createProductInvoice(customer, product, quantity);
    }
    
    /**
     * Crea una factura de servicio
     * @param customerName Nombre del cliente
     * @param customerContact Contacto del cliente
     * @param serviceName Nombre del servicio
     * @param hourlyRate Tarifa por hora
     * @param hours Horas de servicio
     * @return Factura de servicio creada
     */
    public Invoice createServiceInvoice(String customerName, String customerContact,
                                      String serviceName, double hourlyRate, double hours) {
        Customer customer = controller.createCustomer(customerName, customerContact);
        Product service = controller.createProduct(serviceName, hourlyRate, 1);
        return controller.createServiceInvoice(customer, service, hours);
    }
    
    /**
     * Busca una factura por número
     * @param invoiceNumber Número de factura a buscar
     * @return Factura encontrada o null
     */
    public Invoice findInvoice(String invoiceNumber) {
        return controller.findInvoice(invoiceNumber);
    }
    
    /**
     * Obtiene todas las facturas del sistema
     * @return Lista de todas las facturas
     */
    public List<Invoice> getAllInvoices() {
        return controller.getAllInvoices();
    }
    
    /**
     * Obtiene estadísticas básicas del sistema
     * @return String con estadísticas
     */
    public String getSystemStatistics() {
        int count = controller.getInvoiceCount();
        return "Sistema de Facturación - Estadísticas:\n" +
               "Total de facturas: " + count + "\n" +
               "Estado del sistema: Activo";
    }
    
    /**
     * Imprime una factura (simula impresión)
     * @param invoiceNumber Número de factura a imprimir
     * @return true si se encontró e imprimió, false en caso contrario
     */
    public boolean printInvoice(String invoiceNumber) {
        Invoice invoice = findInvoice(invoiceNumber);
        if (invoice != null) {
            System.out.println("=== IMPRIMIENDO FACTURA ===");
            System.out.println(invoice.toString());
            System.out.println("=== FIN DE IMPRESIÓN ===");
            return true;
        }
        return false;
    }
    
    /**
     * Guarda una factura en el sistema (ya está guardada automáticamente)
     * @param invoice Factura a guardar
     * @return true si se guardó exitosamente
     */
    public boolean saveInvoice(Invoice invoice) {
        // Las facturas ya se guardan automáticamente al crearlas
        return invoice != null;
    }
}