package controller;
import dao.MySqlCabinet;
import entity.CabinetEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.List;

public class ControllerCreateCabinet {
    @FXML
    TextField cabinetName ;
    public void createCabinet(ActionEvent actionEvent) {

        if(cabinetName.getText().length()!=0)
        {
            MySqlCabinet mySqlCabinet = new MySqlCabinet();
            List<CabinetEntity> list = mySqlCabinet.getCabinetAll();
            for (CabinetEntity cabinet: list) {

                if(cabinet.getNumCabinet().equals(cabinetName.getText()))
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Данный тип существует");
                    alert.show();
                    break;
                }
            }
            CabinetEntity newCabinet = new CabinetEntity();
            newCabinet.setNumCabinet(cabinetName.getText());
            mySqlCabinet.create(newCabinet);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Кабинет успешно добавлен");
            alert.show();
            cabinetName.setText("");
        }

    }
}
