/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.model;

public class Laboratorio {

    private int idLaboratorio;
    private String nombreLab;
    private String paisOrigen;

    public Laboratorio() {
    }

    public Laboratorio(int idLaboratorio, String nombreLab, String paisOrigen) {
        this.idLaboratorio = idLaboratorio;
        this.nombreLab = nombreLab;
        this.paisOrigen = paisOrigen;
    }

    public int getIdLaboratorio() { return idLaboratorio; }
    public void setIdLaboratorio(int idLaboratorio) { this.idLaboratorio = idLaboratorio; }
    public String getNombreLab() { return nombreLab; }
    public void setNombreLab(String nombreLab) { this.nombreLab = nombreLab; }
    public String getPaisOrigen() { return paisOrigen; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }

    /** El ComboBox muestra este texto. */
    @Override
    public String toString() {
        return nombreLab + " (" + paisOrigen + ")";
    }
}