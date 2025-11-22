package com.facturacion;

import com.facturacion.facade.InvoiceSystemFacade;
import com.facturacion.model.invoices.Invoice;

/**
 * Clase de prueba para verificar el funcionamiento del sistema de facturación
 * Prueba todos los patrones de diseño implementados
 */
public class TestInvoiceSystem {
    
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBAS DEL SISTEMA DE FACTURACIÓN ===\n");
        
        // Crear el sistema de facturación usando el facade
        InvoiceSystemFacade invoiceSystem = new InvoiceSystemFacade();
        
        // Prueba 1: Crear factura de producto
        System.out.println("1. Creando factura de producto:");
        Invoice productInvoice = invoiceSystem.createProductInvoice(
            "Juan Pérez", 
            "555-1234", 
            "Laptop Dell", 
            1200.00, 
            2
        );
        System.out.println(productInvoice.toString());
        System.out.println();
        
        // Prueba 2: Crear factura de servicio
        System.out.println("2. Creando factura de servicio:");
        Invoice serviceInvoice = invoiceSystem.createServiceInvoice(
            "María García", 
            "maria@email.com", 
            "Desarrollo Web", 
            50.00, 
            8
        );
        System.out.println(serviceInvoice.toString());
        System.out.println();
        
        // Prueba 3: Mostrar estadísticas del sistema
        System.out.println("3. Estadísticas del sistema:");
        System.out.println(invoiceSystem.getSystemStatistics());
        System.out.println();
        
        // Prueba 4: Mostrar todas las facturas
        System.out.println("4. Mostrando todas las facturas:");
        java.util.List<Invoice> allInvoices = invoiceSystem.getAllInvoices();
        for (Invoice invoice : allInvoices) {
            System.out.println("- " + invoice.getNumber() + ": " + invoice.getCustomer().getName() + 
                             " - Total: $" + String.format("%.2f", invoice.getTotal()));
        }
        System.out.println();
        
        // Prueba 5: Buscar factura específica
        System.out.println("5. Buscando factura por número:");
        Invoice foundInvoice = invoiceSystem.findInvoice(productInvoice.getNumber());
        if (foundInvoice != null) {
            System.out.println("Factura encontrada: " + foundInvoice.getNumber());
        } else {
            System.out.println("Factura no encontrada");
        }
        System.out.println();
        
        // Prueba 6: Imprimir factura (simulación)
        System.out.println("6. Simulando impresión de factura:");
        boolean printed = invoiceSystem.printInvoice(serviceInvoice.getNumber());
        if (printed) {
            System.out.println("Factura impresa exitosamente");
        } else {
            System.out.println("Error al imprimir factura");
        }
        
        System.out.println("\n=== PRUEBAS COMPLETADAS EXITOSAMENTE ===");
    }
}