package com.facturacion.model.invoices;

import com.facturacion.model.customer.Customer;
import com.facturacion.model.product.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa una factura en el sistema de facturación
 * Implementa la lógica de negocio para el cálculo de totales
 */
public class Invoice {
    private String number;           // Número único de la factura
    private Date date;               // Fecha de emisión de la factura
    private Customer customer;       // Cliente asociado a la factura
    private List<Product> products;  // Lista de productos en la factura
    private double total;           // Total calculado de la factura
    private InvoiceType type;       // Tipo de factura (PRODUCTO o SERVICIO)
    
    /**
     * Constructor de la clase Invoice
     * @param number Número de la factura
     * @param customer Cliente asociado
     * @param type Tipo de factura
     */
    public Invoice(String number, Customer customer, InvoiceType type) {
        this.number = number;
        this.date = new Date();  // Fecha actual por defecto
        this.customer = customer;
        this.products = new ArrayList<>();
        this.total = 0.0;
        this.type = type;
    }
    
    // Getters y setters
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public List<Product> getProducts() {
        return products;
    }
    
    public double getTotal() {
        return total;
    }
    
    public InvoiceType getType() {
        return type;
    }
    
    public void setType(InvoiceType type) {
        this.type = type;
    }
    
    /**
     * Agrega un producto a la factura y actualiza el total
     * @param product Producto a agregar
     */
    public void addProduct(Product product) {
        products.add(product);
        calculateTotal();
    }
    
    /**
     * Calcula el total de la factura sumando todos los productos
     */
    private void calculateTotal() {
        total = 0.0;
        for (Product product : products) {
            total += product.calculateTotal();
        }
    }
    
    /**
     * Genera una representación en cadena de la factura
     * @return String con la información completa de la factura
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura #").append(number).append("\n");
        sb.append("Fecha: ").append(date).append("\n");
        sb.append("Cliente: ").append(customer.getName()).append("\n");
        sb.append("Tipo: ").append(type).append("\n");
        sb.append("Productos:\n");
        for (Product product : products) {
            sb.append("  - ").append(product.toString()).append("\n");
        }
        sb.append("Total: $").append(String.format("%.2f", total));
        return sb.toString();
    }
}