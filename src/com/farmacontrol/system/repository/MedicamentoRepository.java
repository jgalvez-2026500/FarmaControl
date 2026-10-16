/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.repository;

import com.farmacontrol.system.config.ConexionDB;
import com.farmacontrol.system.model.Medicamento;
import com.farmacontrol.system.model.dto.MedicamentoDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoRepository {

    /** HU 4 - Tarea 4.3: lee view_read_medicamento (INNER JOIN con laboratorio). */
    public List<MedicamentoDTO> listarMedicamentos() throws SQLException {
        List<MedicamentoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM view_read_medicamento ORDER BY fecha_caducidad";
        Connection conn = ConexionDB.getInstanciaConexionDB().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new MedicamentoDTO(
                        rs.getInt("id_medicamento"),
                        rs.getString("nombre"),
                        rs.getBigDecimal("miligramos"),
                        rs.getInt("cantidad_stock"),
                        rs.getDate("fecha_caducidad").toLocalDate(),
                        rs.getInt("id_laboratorio"),
                        rs.getString("nombre_lab")));
            }
        }
        return lista;
    }

    /** HU 3 - Tarea 3.3: CREATE con CallableStatement + try-with-resources. */
    public boolean crear(Medicamento m) throws SQLException {
        String sql = "{call sp_crear_medicamento(?, ?, ?, ?, ?)}";
        Connection conn = ConexionDB.getInstanciaConexionDB().getConnection();

        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, m.getNombre());
            cstmt.setBigDecimal(2, m.getMiligramos());
            cstmt.setInt(3, m.getCantidadStock());
            cstmt.setDate(4, Date.valueOf(m.getFechaCaducidad()));
            cstmt.setInt(5, m.getIdLaboratorio());
            cstmt.execute();
            return true;
        }
    }

    public boolean actualizar(Medicamento m) throws SQLException {
        String sql = "{call sp_actualizar_medicamento(?, ?, ?, ?, ?, ?)}";
        Connection conn = ConexionDB.getInstanciaConexionDB().getConnection();

        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, m.getIdMedicamento());
            cstmt.setString(2, m.getNombre());
            cstmt.setBigDecimal(3, m.getMiligramos());
            cstmt.setInt(4, m.getCantidadStock());
            cstmt.setDate(5, Date.valueOf(m.getFechaCaducidad()));
            cstmt.setInt(6, m.getIdLaboratorio());
            cstmt.execute();
            return true;
        }
    }

    public boolean eliminar(int idMedicamento) throws SQLException {
        String sql = "{call sp_eliminar_medicamento(?)}";
        Connection conn = ConexionDB.getInstanciaConexionDB().getConnection();

        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, idMedicamento);
            cstmt.execute();
            return true;
        }
    }
}
