/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.repository;

import com.farmacontrol.system.config.ConexionDB;
import com.farmacontrol.system.model.Laboratorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaboratorioRepository {

    /** HU 2 - Tarea 2.2: SELECT con PreparedStatement. */
    public List<Laboratorio> listarLaboratorios() throws SQLException {
        List<Laboratorio> laboratorios = new ArrayList<>();
        String sql = "SELECT id_laboratorio, nombre_lab, pais_origen FROM laboratorio ORDER BY nombre_lab";
        Connection conn = ConexionDB.getInstanciaConexionDB().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                laboratorios.add(new Laboratorio(
                        rs.getInt("id_laboratorio"),
                        rs.getString("nombre_lab"),
                        rs.getString("pais_origen")));
            }
        }
        return laboratorios;
    }
}
