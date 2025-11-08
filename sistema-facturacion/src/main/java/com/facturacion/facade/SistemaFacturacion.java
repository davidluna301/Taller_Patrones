package com.facturacion.facade;

import com.facturacion.modelo.facturas.Factura;
import com.facturacion.modelo.facturas.FacturaFactory;
import com.facturacion.singleton.BaseDatos;

public class SistemaFacturacion {

    private BaseDatos baseDatos;

    public SistemaFacturacion() {
        this.baseDatos = BaseDatos.getInstance();
    }

    public void crearFactura(String tipo, double monto) {
        FacturaFactory facturaFactory = new FacturaFactory();
        Factura factura = facturaFactory.crearFactura(tipo, monto);
        baseDatos.agregarFactura(factura);
    }

    public void mostrarFacturas() {
        baseDatos.mostrarFacturas();
    }
}