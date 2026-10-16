/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.config;
   
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static ConexionDB instanciaConexionDB;
    private Connection connection;

    private ConexionDB() {
        conectar();
    }

    private void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + Enviroment.LOCATION_SERVICE + "/" + Enviroment.DATA_BASE
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    Enviroment.USER,
                    Enviroment.PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: driver MySQL no encontrado (revise lib/)");
        } catch (SQLException e) {
            System.out.println("Error de conexión a DB: " + e.getMessage());
        }
    }

    public static ConexionDB getInstanciaConexionDB() {
        if (instanciaConexionDB == null) {
            instanciaConexionDB = new ConexionDB();
        }
        return instanciaConexionDB;
    }

    /** Devuelve la conexión; si se cerró o nunca se abrió, intenta reconectar. */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            conectar();
        }
        if (connection == null) {
            throw new SQLException("No hay conexión con la base de datos");
        }
        return connection;
    }
}