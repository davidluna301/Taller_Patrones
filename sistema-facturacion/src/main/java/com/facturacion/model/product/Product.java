package com.facturacion.model.product;

/**
 * Clase que representa un producto en el sistema de facturación
 * Contiene información del producto y métodos de cálculo
 */
public class Product {
    private String name;        // Nombre del producto
    private double price;       // Precio unitario del producto
    private int quantity;       // Cantidad del producto
    
    /**
     * Constructor de la clase Product
     * @param name Nombre del producto
     * @param price Precio unitario del producto
     * @param quantity Cantidad del producto
     */
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Getters y setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Calcula el total del producto (precio * cantidad)
     * @return El total del producto
     */
    public double calculateTotal() {
        return price * quantity;
    }
    
    /**
     * Representación en cadena del producto
     * @return String con la información del producto
     */
    @Override
    public String toString() {
        return "Producto: " + name + ", Precio: $" + price + ", Cantidad: " + quantity;
    }
}