/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Medicamento {

    private int idMedicamento;
    private String nombre;
    private BigDecimal miligramos;
    private int cantidadStock;
    private LocalDate fechaCaducidad;
    private int idLaboratorio;

    public Medicamento() {
    }

    public Medicamento(int idMedicamento, String nombre, BigDecimal miligramos,
            int cantidadStock, LocalDate fechaCaducidad, int idLaboratorio) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.miligramos = miligramos;
        this.cantidadStock = cantidadStock;
        this.fechaCaducidad = fechaCaducidad;
        this.idLaboratorio = idLaboratorio;
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
}
