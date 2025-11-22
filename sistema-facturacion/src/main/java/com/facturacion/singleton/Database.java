package com.facturacion.singleton;

import com.facturacion.model.invoices.Invoice;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa el patrón Singleton para la gestión de la base de datos
 * Simula una base de datos en memoria para almacenar las facturas
 */
public class Database {
    private static Database instance;   // Instancia única del singleton
    private List<Invoice> invoices;     // Lista de facturas almacenadas
    
    /**
     * Constructor privado para prevenir instanciación directa
     * Inicializa la lista de facturas
     */
    private Database() {
        invoices = new ArrayList<>();
    }
    
    /**
     * Método estático para obtener la única instancia de la base de datos
     * Implementa el patrón Singleton con verificación doble
     * @return La instancia única de Database
     */
    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }
    
    /**
     * Agrega una factura a la base de datos
     * @param invoice Factura a agregar
     */
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }
    
    /**
     * Obtiene todas las facturas almacenadas
     * @return Lista de facturas
     */
    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoices); // Retorna una copia para proteger la lista original
    }
    
    /**
     * Busca una factura por su número
     * @param invoiceNumber Número de factura a buscar
     * @return La factura encontrada o null si no existe
     */
    public Invoice findInvoiceByNumber(String invoiceNumber) {
        for (Invoice invoice : invoices) {
            if (invoice.getNumber().equals(invoiceNumber)) {
                return invoice;
            }
        }
        return null;
    }
    
    /**
     * Obtiene el número total de facturas almacenadas
     * @return Cantidad de facturas
     */
    public int getInvoiceCount() {
        return invoices.size();
    }
    
    /**
     * Limpia todas las facturas de la base de datos
     * Usar con precaución - elimina todos los datos
     */
    public void clearAllInvoices() {
        invoices.clear();
    }
    
    /**
     * Verifica si la base de datos está vacía
     * @return true si no hay facturas, false en caso contrario
     */
    public boolean isEmpty() {
        return invoices.isEmpty();
    }
}