package controller;

import dao.MySqlCabinet;
import dao.MySqlEquipment;
import dao.MySqlStatus;
import dao.MySqlType;
import entity.CabinetEntity;
import entity.EquipmentEntity;
import entity.StatusEntity;
import entity.TypeOfEquipmentEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerUpdateEquipment {

    @FXML
    ComboBox<CabinetEntity> numCabinet;
    @FXML
    ComboBox<StatusEntity> combo_nameStatus;
    @FXML
    ComboBox<TypeOfEquipmentEntity> typeName;
    @FXML
    TextField name;
    @FXML
    TextField inv;
    @FXML
    TextArea description;

    private static UpdateUI updateUICallBack;

    private ObservableList<TypeOfEquipmentEntity> observableListType = FXCollections.observableArrayList();
    private ObservableList<StatusEntity> observableListStatus = FXCollections.observableArrayList();
    private ObservableList<CabinetEntity> observableListCabinet = FXCollections.observableArrayList();

    MySqlCabinet mySqlCabinet = new MySqlCabinet();
    MySqlType mySqlType = new MySqlType();
    MySqlStatus mySqlStatus = new MySqlStatus();
    MySqlEquipment mySqlEquipment = new MySqlEquipment();


    public static EquipmentEntity equipment;

    static  void registerCallBack(UpdateUI callback) {
        updateUICallBack = callback;
    }

    @FXML
    private void initialize() {
        observableListCabinet.addAll(mySqlCabinet.getCabinetAll());
        numCabinet.getItems().setAll(observableListCabinet);
        numCabinet.getSelectionModel().select(equipment.getCabinetByCabinetIdcabinet());

        observableListStatus.addAll(mySqlStatus.getStatusAll());
        combo_nameStatus.setItems(observableListStatus);
        combo_nameStatus.getSelectionModel().select(equipment.getStatusEntity());

        observableListType.addAll(mySqlType.getTypeAll());
        typeName.setItems(observableListType);
        typeName.getSelectionModel().select(equipment.getTypeOfEquipmentEntity());

        name.setText(equipment.getName());
        inv.setText(equipment.getInventoryNumber());
        description.setText(equipment.getDescription());
    }

    public static void setEquipment(EquipmentEntity e) {
        equipment = e;
    }

    public void updateEquipment(ActionEvent actionEvent) {

        if (name.getText().length() != 0 && inv.getText().length() != 0
                && combo_nameStatus.getSelectionModel().getSelectedItem() != null
                && typeName.getSelectionModel().getSelectedItem() != null
                && numCabinet.getSelectionModel().getSelectedItem() != null) {


            equipment.setName(name.getText());
            equipment.setInventoryNumber(inv.getText());
            equipment.setStatusEntity(combo_nameStatus.getSelectionModel().getSelectedItem());
            equipment.setCabinetByCabinetIdcabinet(numCabinet.getSelectionModel().getSelectedItem());
            equipment.setTypeOfEquipmentEntity(typeName.getSelectionModel().getSelectedItem());

            if (description.getText() != null && description.getText().length() != 0) {
                equipment.setDescription(description.getText());
            } else equipment.setDescription("");
            mySqlEquipment.updateEquipment(equipment);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Оборудование успешно обновлено");
            alert.show();
            updateUICallBack.updateUI();

        } else {
            Alert alertError = new Alert(Alert.AlertType.INFORMATION);
            alertError.setHeaderText("Ошибка обновления проверьте данные");
            alertError.show();
        }
    }

    interface UpdateUI {
        void updateUI();
    }

}
