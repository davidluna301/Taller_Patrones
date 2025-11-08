package com.facturacion.singleton;

import com.facturacion.modelo.facturas.Factura;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos {
    private static BaseDatos instancia;
    private final List<Factura> facturas;

    private BaseDatos() {
        facturas = new ArrayList<>();
    }

    public static BaseDatos getInstance() {
        if (instancia == null) {
            synchronized (BaseDatos.class) {
                if (instancia == null) {
                    instancia = new BaseDatos();
                }
            }
        }
        return instancia;
    }

    public void guardarFactura(Factura factura) {
        facturas.add(factura);
    }

    public List<Factura> obtenerFacturas() {
        return new ArrayList<>(facturas);
    }
}