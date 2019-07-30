package controller;

import dao.MySqlType;
import entity.TypeOfEquipmentEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.List;

public class ControllerCreateType {
    @FXML
    TextField nameType;
    public void createType(ActionEvent actionEvent) {
        if(nameType.getText().length()!=0)
        {
            MySqlType mySqlType = new MySqlType();
            List<TypeOfEquipmentEntity> list = mySqlType.getTypeAll();
            for (TypeOfEquipmentEntity type: list) {

                if(type.getTypeName().equals(nameType.getText()))
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Данный тип существует");
                    alert.show();
                    break;
                }
            }
            TypeOfEquipmentEntity newTtype = new TypeOfEquipmentEntity();
            newTtype.setTypeName(nameType.getText());
            mySqlType.create(newTtype);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Тип успешно добавлен");
            alert.show();
            nameType.setText("");
        }

    }
}
