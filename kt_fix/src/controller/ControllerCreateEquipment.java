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
import javafx.scene.control.*;
import javafx.util.Callback;

public class ControllerCreateEquipment {

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


    private ObservableList<TypeOfEquipmentEntity> observableListType = FXCollections.observableArrayList();
    private ObservableList<StatusEntity> observableListStatus = FXCollections.observableArrayList();
    private ObservableList<CabinetEntity> observableListCabinet = FXCollections.observableArrayList();

    MySqlCabinet mySqlCabinet = new MySqlCabinet();
    MySqlType mySqlType = new MySqlType();
    MySqlStatus mySqlStatus = new MySqlStatus();
    MySqlEquipment mySqlEquipment = new MySqlEquipment();

    @FXML
    private void initialize() {
        observableListCabinet.addAll(mySqlCabinet.getCabinetAll());
        numCabinet.getItems().setAll(observableListCabinet);

        observableListStatus.addAll(mySqlStatus.getStatusAll());
        combo_nameStatus.setItems(observableListStatus);

        observableListType.addAll(mySqlType.getTypeAll());
        typeName.setItems(observableListType);


    }

    public void createEquipment(ActionEvent actionEvent) {
        if (name.getText().length() != 0 && inv.getText().length() != 0
                && combo_nameStatus.getSelectionModel().getSelectedItem() != null
                && typeName.getSelectionModel().getSelectedItem() != null
                && numCabinet.getSelectionModel().getSelectedItem() != null) {

            EquipmentEntity newEquipment = new EquipmentEntity();
            newEquipment.setName(name.getText());
            newEquipment.setInventoryNumber(inv.getText());
            newEquipment.setStatusEntity(combo_nameStatus.getSelectionModel().getSelectedItem());
            newEquipment.setCabinetByCabinetIdcabinet(numCabinet.getSelectionModel().getSelectedItem());
            newEquipment.setTypeOfEquipmentEntity(typeName.getSelectionModel().getSelectedItem());

            if (description.getText()!=null && description.getText().length() != 0) {
                newEquipment.setDescription(description.getText());
            }
            else  newEquipment.setDescription("");
            mySqlEquipment.createEquipment(newEquipment);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Оборудование успешно добавленно");
            alert.show();
            name.setText("");
            inv.setText("");
            description.setText("");
            combo_nameStatus.getSelectionModel().clearSelection();
            numCabinet.getSelectionModel().clearSelection();
            typeName.getSelectionModel().clearSelection();

        }
        else  {Alert alertError = new Alert(Alert.AlertType.INFORMATION);
        alertError.setHeaderText("Ошибка добавления, проверьте данные");
        alertError.show();}
    }
}

