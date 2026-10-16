/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.repository;

import com.farmacontrol.system.config.ConexionDB;
import com.farmacontrol.system.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationRepository {

    /** Ejecuta sp_login_usuario. Devuelve null si usuario/clave no coinciden. */
    public User login(String nombreUsuario, String clave) throws SQLException {
        String sql = "{call sp_login_usuario(?, ?)}";
        Connection conn = ConexionDB.getInstanciaConexionDB().getConnection();

        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, nombreUsuario);
            cstmt.setString(2, clave);

            try (ResultSet rs = cstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre_usuario"),
                            rs.getString("nombre_completo"),
                            rs.getBoolean("user_status"));
                }
            }
        }
        return null;
    }
}
