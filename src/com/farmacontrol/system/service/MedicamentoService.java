/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.service;

import com.farmacontrol.system.model.Medicamento;
import com.farmacontrol.system.model.dto.MedicamentoDTO;
import com.farmacontrol.system.repository.MedicamentoRepository;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class MedicamentoService {

    private final MedicamentoRepository medRepo = new MedicamentoRepository();

    public List<MedicamentoDTO> listar() {
        try {
            return medRepo.listarMedicamentos();
        } catch (SQLException e) {
            System.out.println("Error al listar medicamentos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean crear(Medicamento m) {
        try {
            return medRepo.crear(m);
        } catch (SQLException e) {
            System.out.println("Error al crear medicamento: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Medicamento m) {
        try {
            return medRepo.actualizar(m);
        } catch (SQLException e) {
            System.out.println("Error al actualizar medicamento: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idMedicamento) {
        try {
            return medRepo.eliminar(idMedicamento);
        } catch (SQLException e) {
            System.out.println("Error al eliminar medicamento: " + e.getMessage());
            return false;
        }
    }
}