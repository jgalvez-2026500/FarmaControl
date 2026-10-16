/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.utils;

import com.farmacontrol.system.model.User;

public class Session {

    private static User usuarioActivo;

    private Session() {
    }

    public static User getUsuarioActivo() { return usuarioActivo; }
    public static void setUsuarioActivo(User usuario) { usuarioActivo = usuario; }
    public static void cerrar() { usuarioActivo = null; }
}
