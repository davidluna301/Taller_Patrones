package com.facturacion.controller;

import com.facturacion.model.customer.Customer;
import com.facturacion.model.invoices.Invoice;
import com.facturacion.model.invoices.InvoiceFactory;
import com.facturacion.model.invoices.InvoiceType;
import com.facturacion.model.product.Product;
import com.facturacion.singleton.Database;

/**
 * Clase controladora que maneja la lógica de negocio del sistema de facturación
 * Actúa como intermediario entre la vista y el modelo
 */
public class InvoiceController {
    private InvoiceFactory invoiceFactory;  // Fábrica para crear facturas
    private Database database;               // Base de datos singleton
    
    /**
     * Constructor del controlador
     * Inicializa la fábrica de facturas y la base de datos
     */
    public InvoiceController() {
        this.invoiceFactory = new InvoiceFactory();
        this.database = Database.getInstance();
    }
    
    /**
     * Crea una factura de producto
     * @param customer Cliente asociado
     * @param product Producto a facturar
     * @param quantity Cantidad del producto
     * @return Factura creada
     */
    public Invoice createProductInvoice(Customer customer, Product product, int quantity) {
        Invoice invoice = invoiceFactory.createProductInvoice(customer, product, quantity);
        database.addInvoice(invoice);
        return invoice;
    }
    
    /**
     * Crea una factura de servicio
     * @param customer Cliente asociado
     * @param service Servicio a facturar
     * @param hours Horas de servicio
     * @return Factura creada
     */
    public Invoice createServiceInvoice(Customer customer, Product service, double hours) {
        Invoice invoice = invoiceFactory.createServiceInvoice(customer, service, hours);
        database.addInvoice(invoice);
        return invoice;
    }
    
    /**
     * Busca una factura por número
     * @param invoiceNumber Número de factura a buscar
     * @return Factura encontrada o null
     */
    public Invoice findInvoice(String invoiceNumber) {
        return database.findInvoiceByNumber(invoiceNumber);
    }
    
    /**
     * Obtiene todas las facturas del sistema
     * @return Lista de todas las facturas
     */
    public java.util.List<Invoice> getAllInvoices() {
        return database.getAllInvoices();
    }
    
    /**
     * Obtiene el número total de facturas
     * @return Cantidad de facturas
     */
    public int getInvoiceCount() {
        return database.getInvoiceCount();
    }
    
    /**
     * Crea un cliente con validación básica
     * @param name Nombre del cliente
     * @param contact Información de contacto
     * @return Cliente creado
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public Customer createCustomer(String name, String contact) {
        // Validar que el nombre no esté vacío
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        }
        
        // Validar que el contacto no esté vacío
        if (contact == null || contact.trim().isEmpty()) {
            throw new IllegalArgumentException("El contacto del cliente no puede estar vacío");
        }
        
        return new Customer(name.trim(), contact.trim());
    }
    
    /**
     * Crea un producto con validación básica
     * @param name Nombre del producto
     * @param price Precio del producto
     * @param quantity Cantidad del producto
     * @return Producto creado
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public Product createProduct(String name, double price, int quantity) {
        // Validar que el nombre no esté vacío
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        
        // Validar que el precio sea positivo
        if (price <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        
        // Validar que la cantidad sea positiva
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        
        return new Product(name.trim(), price, quantity);
    }
}