package com.facturacion.model.invoices;

import com.facturacion.model.customer.Customer;
import com.facturacion.model.product.Product;
import java.util.UUID;

/**
 * Clase que implementa el patrón Factory para la creación de facturas
 * Permite crear diferentes tipos de facturas según el tipo especificado
 */
public class InvoiceFactory {
    
    /**
     * Crea una factura del tipo especificado
     * @param type Tipo de factura (PRODUCT o SERVICE)
     * @param customer Cliente asociado a la factura
     * @param product Producto a facturar
     * @param quantity Cantidad del producto o horas de servicio
     * @return Factura creada
     */
    public Invoice createInvoice(InvoiceType type, Customer customer, Product product, double quantity) {
        // Generar número único de factura
        String invoiceNumber = generateInvoiceNumber();
        
        // Crear la factura con el tipo especificado
        Invoice invoice = new Invoice(invoiceNumber, customer, type);
        
        // Ajustar la cantidad del producto según el tipo
        Product productForInvoice = new Product(product.getName(), product.getPrice(), (int) quantity);
        
        // Si es servicio, el precio puede ser por hora
        if (type == InvoiceType.SERVICE) {
            double servicePrice = product.getPrice() * quantity;
            productForInvoice = new Product(product.getName() + " (Servicio)", servicePrice, 1);
        }
        
        // Agregar el producto a la factura
        invoice.addProduct(productForInvoice);
        
        return invoice;
    }
    
    /**
     * Genera un número único de factura
     * @return String con el número de factura generado
     */
    private String generateInvoiceNumber() {
        // Generar número con prefijo y UUID corto
        String uuid = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "FAC-" + uuid;
    }
    
    /**
     * Crea una factura de producto estándar
     * @param customer Cliente asociado
     * @param product Producto a facturar
     * @param quantity Cantidad del producto
     * @return Factura de producto creada
     */
    public Invoice createProductInvoice(Customer customer, Product product, int quantity) {
        return createInvoice(InvoiceType.PRODUCT, customer, product, quantity);
    }
    
    /**
     * Crea una factura de servicio
     * @param customer Cliente asociado
     * @param service Servicio a facturar
     * @param hours Horas de servicio
     * @return Factura de servicio creada
     */
    public Invoice createServiceInvoice(Customer customer, Product service, double hours) {
        return createInvoice(InvoiceType.SERVICE, customer, service, hours);
    }
}