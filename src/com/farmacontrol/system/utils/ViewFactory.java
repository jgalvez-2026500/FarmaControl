/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.utils;

import com.farmacontrol.system.Main;
import java.io.IOException;
import java.io.UncheckedIOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {

    private static final String PATH_VIEWS = "/com/farmacontrol/system/view/";

    private Scene loadFileFXML(String nameFile, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(PATH_VIEWS + nameFile));
            return new Scene(loader.load(), width, height);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void loadScene(String nameView) {
        Stage stage = SceneManager.getInstanciaSceneManager().getStagePrincipal();
        stage.setResizable(false);
        Scene scene;

        switch (nameView) {
            case "Medicamento" -> {
                stage.setTitle("FarmaControl - Botiquín");
                scene = loadFileFXML("ViewMedicamento.fxml", 1100, 620);
            }
            default -> {
                stage.setTitle("FarmaControl - Inicio de sesión");
                scene = loadFileFXML("ViewLogin.fxml", 420, 380);
            }
        }
        SceneManager.getInstanciaSceneManager().changeScene(scene);
    }

    public void viewLogin() {
        loadScene("Login");
    }

    public void viewMedicamento() {
        loadScene("Medicamento");
    }
}