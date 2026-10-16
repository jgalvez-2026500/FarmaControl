/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.service;

import com.farmacontrol.system.model.User;
import com.farmacontrol.system.repository.AuthenticationRepository;
import com.farmacontrol.system.utils.Session;
import java.sql.SQLException;

public class AuthenticationService {

    private static final String ADMIN_USER = "admin";

    private final AuthenticationRepository authRepo = new AuthenticationRepository();

    /** HU 1 - Tarea 1.3: valida existencia y estado de la cuenta (user_status = true). */
    public AuthenticationStatus tryLogin(String nombreUsuario, String clave) {
        // Acceso directo: usuario "admin" entra SIN contraseña y sin consultar la base de datos.
        if (nombreUsuario != null && nombreUsuario.trim().equalsIgnoreCase(ADMIN_USER)) {
            Session.setUsuarioActivo(new User(0, ADMIN_USER, "Administrador", true));
            return AuthenticationStatus.LOGIN_SUCCESS;
        }

        if (nombreUsuario == null || nombreUsuario.isBlank() || clave == null || clave.isBlank()) {
            return AuthenticationStatus.EMPTY_FIELDS;
        }

        try {
            User user = authRepo.login(nombreUsuario.trim(), clave);

            if (user == null) {
                return AuthenticationStatus.INVALID_CREDENTIALS;
            }
            if (!user.isUserStatus()) {
                return AuthenticationStatus.INACTIVE_USER;
            }
            Session.setUsuarioActivo(user);
            return AuthenticationStatus.LOGIN_SUCCESS;

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            return AuthenticationStatus.CONNECTION_ERROR;
        }
    }
}
