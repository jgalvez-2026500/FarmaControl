/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.utils;


import java.math.BigDecimal;

public class Validations {

    private Validations() {
    }

    public static boolean estaVacio(String texto) {
        return texto == null || texto.isBlank();
    }

    /** Solo dígitos (sin letras, sin signo menos, sin decimales) y que quepa en un int. */
    public static boolean esEnteroNoNegativo(String texto) {
        if (estaVacio(texto) || !texto.trim().matches("\\d{1,9}")) {
            return false;
        }
        return true;
    }

    /** Número decimal mayor que cero, con punto o coma (ej. 500, 12.5, 0,25). */
    public static boolean esDecimalPositivo(String texto) {
        if (estaVacio(texto) || !texto.trim().matches("\\d{1,6}([.,]\\d{1,2})?")) {
            return false;
        }
        return new BigDecimal(texto.trim().replace(',', '.')).signum() > 0;
    }

    public static BigDecimal aDecimal(String texto) {
        return new BigDecimal(texto.trim().replace(',', '.'));
    }
}
