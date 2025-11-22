package com.facturacion.model.customer;

/**
 * Clase que representa a un cliente del sistema de facturación
 * Contiene la información básica del cliente
 */
public class Customer {
    private String name;        // Nombre del cliente
    private String contact;     // Información de contacto del cliente
    
    /**
     * Constructor de la clase Customer
     * @param name Nombre del cliente
     * @param contact Información de contacto del cliente
     */
    public Customer(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }
    
    // Getters y setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    /**
     * Representación en cadena del cliente
     * @return String con la información del cliente
     */
    @Override
    public String toString() {
        return "Cliente: " + name + ", Contacto: " + contact;
    }
}