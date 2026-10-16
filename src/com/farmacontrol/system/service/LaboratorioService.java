/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.service;

import com.farmacontrol.system.model.Laboratorio;
import com.farmacontrol.system.repository.LaboratorioRepository;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class LaboratorioService {

    private final LaboratorioRepository labRepo = new LaboratorioRepository();

    public List<Laboratorio> listar() {
        try {
            return labRepo.listarLaboratorios();
        } catch (SQLException e) {
            System.out.println("Error al listar laboratorios: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
