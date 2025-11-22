package com.facturacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal que inicia la aplicación de facturación
 * Implementa el patrón MVC con JavaFX
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar la interfaz principal desde el archivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/com/facturacion/view/MainWindow.fxml"));
        
        // Configurar la ventana principal
        primaryStage.setTitle("Sistema de Facturación");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}