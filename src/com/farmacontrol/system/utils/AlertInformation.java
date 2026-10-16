/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertInformation {

    public void viewAlert(String titulo, String cabecera, String mensaje, String tipoAlerta) {
        AlertType tipo = switch (tipoAlerta.toUpperCase()) {
            case "WARNING" -> AlertType.WARNING;
            case "ERROR" -> AlertType.ERROR;
            case "CONFIRM" -> AlertType.CONFIRMATION;
            default -> AlertType.INFORMATION;
        };
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /** Devuelve true si la enfermera pulsa Aceptar. */
    public boolean confirmar(String titulo, String cabecera, String mensaje) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(mensaje);
        Optional<ButtonType> respuesta = alert.showAndWait();
        return respuesta.isPresent() && respuesta.get() == ButtonType.OK;
    }
}
