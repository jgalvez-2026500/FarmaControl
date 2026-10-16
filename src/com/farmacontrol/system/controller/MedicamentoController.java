/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.farmacontrol.system.controller;

import com.farmacontrol.system.model.Laboratorio;
import com.farmacontrol.system.model.Medicamento;
import com.farmacontrol.system.model.dto.MedicamentoDTO;
import com.farmacontrol.system.service.LaboratorioService;
import com.farmacontrol.system.service.MedicamentoService;
import com.farmacontrol.system.utils.AlertInformation;
import com.farmacontrol.system.utils.Session;
import com.farmacontrol.system.utils.Validations;
import com.farmacontrol.system.utils.ViewFactory;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MedicamentoController implements Initializable {

    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblError;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtMiligramos;
    @FXML
    private TextField txtStock;
    @FXML
    private DatePicker dpCaducidad;
    @FXML
    private ComboBox<Laboratorio> cbLaboratorio;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<MedicamentoDTO> tblMedicamentos;
    @FXML
    private TableColumn<MedicamentoDTO, String> colNombre;
    @FXML
    private TableColumn<MedicamentoDTO, BigDecimal> colMiligramos;
    @FXML
    private TableColumn<MedicamentoDTO, Integer> colStock;
    @FXML
    private TableColumn<MedicamentoDTO, LocalDate> colCaducidad;
    @FXML
    private TableColumn<MedicamentoDTO, String> colLaboratorio;

    private final MedicamentoService medicamentoService = new MedicamentoService();
    private final LaboratorioService laboratorioService = new LaboratorioService();
    private final AlertInformation alertInfo = new AlertInformation();
    private MedicamentoDTO seleccionado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Session.getUsuarioActivo() != null) {
            lblUsuario.setText("Sesión: " + Session.getUsuarioActivo().getNombreCompleto());
        }
        ocultarError();
        cargarLaboratorios();
        configurarTabla();
        cargarMedicamentos();
        configurarSeleccion();
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    // ---------- HU 2: ComboBox de laboratorios ----------
    private void cargarLaboratorios() {
        cbLaboratorio.setItems(FXCollections.observableArrayList(laboratorioService.listar()));
    }

    // ---------- HU 4: TableView con el nombre del laboratorio ----------
    private void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMiligramos.setCellValueFactory(new PropertyValueFactory<>("miligramos"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
        colCaducidad.setCellValueFactory(new PropertyValueFactory<>("fechaCaducidad"));
        colLaboratorio.setCellValueFactory(new PropertyValueFactory<>("nombreLab"));

        // Resalta en rojo los medicamentos ya vencidos
        tblMedicamentos.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(MedicamentoDTO item, boolean empty) {
                super.updateItem(item, empty);
                getStyleClass().remove("fila-vencida");
                if (!empty && item != null && item.isVencido()) {
                    getStyleClass().add("fila-vencida");
                }
            }
        });
    }

    private void cargarMedicamentos() {
        tblMedicamentos.setItems(FXCollections.observableArrayList(medicamentoService.listar()));
    }

    private void configurarSeleccion() {
        tblMedicamentos.getSelectionModel().selectedItemProperty().addListener((obs, anterior, nuevo) -> {
            if (nuevo == null) {
                return;
            }
            seleccionado = nuevo;
            txtNombre.setText(nuevo.getNombre());
            txtMiligramos.setText(nuevo.getMiligramos().stripTrailingZeros().toPlainString());
            txtStock.setText(String.valueOf(nuevo.getCantidadStock()));
            dpCaducidad.setValue(nuevo.getFechaCaducidad());
            cbLaboratorio.getItems().stream()
                    .filter(l -> l.getIdLaboratorio() == nuevo.getIdLaboratorio())
                    .findFirst()
                    .ifPresent(l -> cbLaboratorio.getSelectionModel().select(l));
            btnGuardar.setDisable(true);
            btnActualizar.setDisable(false);
            btnEliminar.setDisable(false);
            ocultarError();
        });
    }

    // ---------- HU 3: CRUD ----------
    @FXML
    public void onGuardar() {
        Medicamento m = leerFormulario();
        if (m == null) {
            return;
        }
        if (medicamentoService.crear(m)) {
            limpiar();
            cargarMedicamentos();
        } else {
            mostrarError("No se pudo registrar el medicamento.");
        }
    }

    @FXML
    public void onActualizar() {
        if (seleccionado == null) {
            mostrarError("Seleccione un medicamento de la tabla.");
            return;
        }
        Medicamento m = leerFormulario();
        if (m == null) {
            return;
        }
        m.setIdMedicamento(seleccionado.getIdMedicamento());
        if (medicamentoService.actualizar(m)) {
            limpiar();
            cargarMedicamentos();
        } else {
            mostrarError("No se pudo actualizar el medicamento.");
        }
    }

    @FXML
    public void onEliminar() {
        if (seleccionado == null) {
            mostrarError("Seleccione un medicamento de la tabla.");
            return;
        }
        if (alertInfo.confirmar("Confirmar eliminación", "Eliminar medicamento",
                "¿Desea eliminar " + seleccionado.getNombre() + "?")) {
            if (medicamentoService.eliminar(seleccionado.getIdMedicamento())) {
                limpiar();
                cargarMedicamentos();
            } else {
                mostrarError("No se pudo eliminar el medicamento.");
            }
        }
    }

    @FXML
    public void onLimpiar() {
        limpiar();
    }

    @FXML
    public void onCerrarSesion() {
        Session.cerrar();
        new ViewFactory().viewLogin();
    }

    // ---------- Tarea 3.4: validaciones ----------
    private Medicamento leerFormulario() {
        String nombre = txtNombre.getText();
        String mg = txtMiligramos.getText();
        String stock = txtStock.getText();

        if (Validations.estaVacio(nombre) || Validations.estaVacio(mg) || Validations.estaVacio(stock)
                || dpCaducidad.getValue() == null || cbLaboratorio.getValue() == null) {
            mostrarError("Complete todos los campos y seleccione un laboratorio.");
            return null;
        }
        if (!Validations.esDecimalPositivo(mg)) {
            mostrarError("Los miligramos deben ser un número mayor que cero (ej. 500 o 12.5).");
            return null;
        }
        if (!Validations.esEnteroNoNegativo(stock)) {
            mostrarError("El stock debe ser un número entero, sin letras ni valores negativos.");
            return null;
        }

        ocultarError();
        return new Medicamento(0, nombre.trim(), Validations.aDecimal(mg),
                Integer.parseInt(stock.trim()), dpCaducidad.getValue(),
                cbLaboratorio.getValue().getIdLaboratorio());
    }

    private void limpiar() {
        txtNombre.clear();
        txtMiligramos.clear();
        txtStock.clear();
        dpCaducidad.setValue(null);
        cbLaboratorio.getSelectionModel().clearSelection();
        tblMedicamentos.getSelectionModel().clearSelection();
        seleccionado = null;
        btnGuardar.setDisable(false);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        ocultarError();
    }

    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
        lblError.setVisible(true);
        lblError.setManaged(true);
    }

    private void ocultarError() {
        lblError.setText("");
        lblError.setVisible(false);
        lblError.setManaged(false);
    }
}
