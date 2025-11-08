package com.facturacion.controlador;

import com.facturacion.modelo.facturas.Factura;
import com.facturacion.modelo.facturas.FacturaFactory;
import com.facturacion.modelo.facturas.TipoFactura;
import com.facturacion.modelo.cliente.Cliente;
import com.facturacion.modelo.producto.Producto;
import com.facturacion.vista.VistaFacturacion;
import com.facturacion.facade.SistemaFacturacion;

public class ControladorFacturacion {
    private final VistaFacturacion vista;
    private final SistemaFacturacion sistemaFacturacion;

    public ControladorFacturacion(VistaFacturacion vista) {
        this.vista = vista;
        this.sistemaFacturacion = new SistemaFacturacion();
    }

    public void crearFacturaProducto(Cliente cliente, Producto producto, int cantidad) {
        try {
            Factura factura = sistemaFacturacion.crearFactura(
                TipoFactura.PRODUCTO,
                cliente,
                producto,
                cantidad
            );
            vista.mostrarFactura(factura);
            vista.mostrarMensaje("Factura creada exitosamente");
        } catch (Exception e) {
            vista.mostrarError("Error al crear la factura: " + e.getMessage());
        }
    }

    public void crearFacturaServicio(Cliente cliente, Producto servicio, double horas) {
        try {
            Factura factura = sistemaFacturacion.crearFactura(
                TipoFactura.SERVICIO,
                cliente,
                servicio,
                horas
            );
            vista.mostrarFactura(factura);
            vista.mostrarMensaje("Factura de servicio creada exitosamente");
        } catch (Exception e) {
            vista.mostrarError("Error al crear la factura de servicio: " + e.getMessage());
        }
    }

    public void guardarFactura(Factura factura) {
        try {
            sistemaFacturacion.guardarFactura(factura);
            vista.mostrarMensaje("Factura guardada correctamente");
        } catch (Exception e) {
            vista.mostrarError("Error al guardar la factura: " + e.getMessage());
        }
    }

    public void imprimirFactura(Factura factura) {
        try {
            sistemaFacturacion.imprimirFactura(factura);
            vista.mostrarMensaje("Factura enviada a impresión");
        } catch (Exception e) {
            vista.mostrarError("Error al imprimir la factura: " + e.getMessage());
        }
    }
}

package com.facturacion.modelo.facturas;

public class FacturaFactory {
    // Add your FacturaFactory implementation here
}

