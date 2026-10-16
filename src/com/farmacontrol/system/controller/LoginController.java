/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.controller;

import com.farmacontrol.system.service.AuthenticationService;
import com.farmacontrol.system.service.AuthenticationStatus;
import com.farmacontrol.system.utils.AlertInformation;
import com.farmacontrol.system.utils.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;

    private final AuthenticationService authService = new AuthenticationService();
    private final AlertInformation alertInfo = new AlertInformation();

    /** HU 1 - Tarea 1.4: evalúa el Enum y redirige con ViewFactory/SceneManager. */
    @FXML
    public void onInicioSesion() {
        AuthenticationStatus status = authService.tryLogin(nameField.getText(), passwordField.getText());

        switch (status) {
            case LOGIN_SUCCESS ->
                new ViewFactory().viewMedicamento();
            case EMPTY_FIELDS ->
                alertInfo.viewAlert("Campos vacíos", "Datos incompletos",
                        "Ingrese usuario y contraseña.", "WARNING");
            case INVALID_CREDENTIALS ->
                alertInfo.viewAlert("Acceso denegado", "Credenciales incorrectas",
                        "El usuario o la contraseña no son correctos.", "ERROR");
            case INACTIVE_USER ->
                alertInfo.viewAlert("Cuenta inactiva", "No puede ingresar",
                        "Su cuenta está desactivada. Comuníquese con el administrador.", "ERROR");
            case CONNECTION_ERROR ->
                alertInfo.viewAlert("Error de conexión", "Base de datos no disponible",
                        "No se pudo conectar con MySQL. Verifique que el servicio esté activo.", "ERROR");
        }
        passwordField.clear();
    }
}
