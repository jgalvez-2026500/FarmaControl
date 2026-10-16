/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Proyección de view_read_medicamento (Medicamento + nombre del Laboratorio). */
public class MedicamentoDTO {

    private int idMedicamento;
    private String nombre;
    private BigDecimal miligramos;
    private int cantidadStock;
    private LocalDate fechaCaducidad;
    private int idLaboratorio;
    private String nombreLab;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(int idMedicamento, String nombre, BigDecimal miligramos,
            int cantidadStock, LocalDate fechaCaducidad, int idLaboratorio, String nombreLab) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.miligramos = miligramos;
        this.cantidadStock = cantidadStock;
        this.fechaCaducidad = fechaCaducidad;
        this.idLaboratorio = idLaboratorio;
        this.nombreLab = nombreLab;
    }

    public boolean isVencido() {
        return fechaCaducidad != null && fechaCaducidad.isBefore(LocalDate.now());
    }

    public int getIdMedicamento() { return idMedicamento; }
    public void setIdMedicamento(int idMedicamento) { this.idMedicamento = idMedicamento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public BigDecimal getMiligramos() { return miligramos; }
    public void setMiligramos(BigDecimal miligramos) { this.miligramos = miligramos; }
    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }
    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(LocalDate fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }
    public int getIdLaboratorio() { return idLaboratorio; }
    public void setIdLaboratorio(int idLaboratorio) { this.idLaboratorio = idLaboratorio; }
    public String getNombreLab() { return nombreLab; }
    public void setNombreLab(String nombreLab) { this.nombreLab = nombreLab; }
}

