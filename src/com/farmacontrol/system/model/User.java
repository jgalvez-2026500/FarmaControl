/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.model;

public class User {

    private int idUsuario;
    private String nombreUsuario;
    private String nombreCompleto;
    private boolean userStatus;

    public User() {
    }

    public User(int idUsuario, String nombreUsuario, String nombreCompleto, boolean userStatus) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombreCompleto = nombreCompleto;
        this.userStatus = userStatus;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public boolean isUserStatus() { return userStatus; }
    public void setUserStatus(boolean userStatus) { this.userStatus = userStatus; }
}
