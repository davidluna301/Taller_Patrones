# Sistema de Facturación

Este proyecto es un sistema de facturación simple desarrollado en Java utilizando los patrones de diseño Factory Method, Facade y Singleton, junto con una interfaz gráfica interactiva utilizando JavaFX.

## Estructura del Proyecto

- **src/main/java/com/facturacion**: Contiene el código fuente de la aplicación.
  - **Main.java**: Punto de entrada de la aplicación que inicializa JavaFX.
  - **controlador**: Contiene la lógica de control de la aplicación.
    - **ControladorFacturacion.java**: Maneja las interacciones del usuario y conecta la vista con el modelo.
  - **modelo**: Contiene las clases que representan los datos del sistema.
    - **facturas**: Clases relacionadas con las facturas.
      - **Factura.java**: Representa una factura y calcula el total.
      - **FacturaFactory.java**: Implementa el patrón Factory Method para crear facturas.
      - **TipoFactura.java**: Enum que define los tipos de facturas.
    - **cliente**: Clases relacionadas con los clientes.
      - **Cliente.java**: Representa un cliente con información de contacto.
    - **producto**: Clases relacionadas con los productos.
      - **Producto.java**: Representa un producto con precio y cantidad.
  - **singleton**: Implementa el patrón Singleton.
    - **BaseDatos.java**: Maneja la conexión a la base de datos simulada.
  - **facade**: Implementa el patrón Facade.
    - **SistemaFacturacion.java**: Simplifica el proceso de creación y almacenamiento de facturas.
  - **vista**: Contiene las clases relacionadas con la interfaz gráfica.
    - **VentanaPrincipal.fxml**: Diseño de la ventana principal de la aplicación.
    - **VistaFacturacion.java**: Maneja la interfaz gráfica para crear y mostrar facturas.
  
- **pom.xml**: Archivo de configuración de Maven que especifica las dependencias del proyecto.

## Requisitos

- Java JDK 11 o superior
- JavaFX

## Cómo Ejecutar el Proyecto

1. Clona el repositorio.
2. Navega a la carpeta del proyecto.
3. Ejecuta `mvn clean install` para compilar el proyecto.
4. Ejecuta la aplicación con `mvn javafx:run`.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir, por favor abre un issue o envía un pull request.

## Licencia

Este proyecto está bajo la Licencia MIT.