package controller;


import dao.MySqlCabinet;
import dao.MySqlEquipment;
import dao.MySqlStatus;
import dao.MySqlType;
import entity.CabinetEntity;
import entity.EquipmentEntity;
import entity.StatusEntity;
import entity.TypeOfEquipmentEntity;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.loader.plan.spi.EntityQuerySpace;

import java.util.List;
import java.util.Optional;

public class ContollerSampleTable<TabelView> implements ControllerUpdateEquipment.UpdateUI {

    @FXML
    VBox boxStatus;
    @FXML
    VBox boxCabinet;

    @FXML
    VBox boxType;
    @FXML
    ScrollPane scrollStatus;
    @FXML
    ScrollPane scrollType;
    @FXML
    ScrollPane scrollCabinet;

    @FXML
    TextField searchName;

    @FXML
    TitledPane statusOfEquipment;
    @FXML
    TitledPane typeOfEquipment;
    @FXML
    TitledPane cabinetOfEquipment;
    @FXML
    TitledPane All;

    @FXML
    private TableView<EquipmentEntity> table_equipment;
    @FXML
    private TableColumn<EquipmentEntity, String> inv;
    @FXML
    private TableColumn<EquipmentEntity, String> name;
    @FXML
    private TableColumn<EquipmentEntity, String> status;
    @FXML
    private TableColumn<EquipmentEntity, String> type;
    @FXML
    private TableColumn<EquipmentEntity, String> cabinet;

    private ObservableList<EquipmentEntity> observableList = FXCollections.observableArrayList();

    private MySqlEquipment mySqlEquipment = new MySqlEquipment();
    private MySqlCabinet mySqlCabinet = new MySqlCabinet();
    private MySqlStatus mySqlStatus = new MySqlStatus();
    private MySqlType mySqlType = new MySqlType();

    EventHandler updateTitledPane = new EventHandler() {
        @Override
        public void handle(Event event) {
            setButtonTitledPane();
        }
    };


    @FXML
    private void initialize() {
        observableList.addAll(mySqlEquipment.getEquipmentAll());
        showList();
        statusOfEquipment.setOnMouseClicked(updateTitledPane);
        typeOfEquipment.setOnMouseClicked(updateTitledPane);
        cabinetOfEquipment.setOnMouseClicked(updateTitledPane);
        ControllerUpdateEquipment.registerCallBack(this);

        All.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                updateObservableList(mySqlEquipment.getEquipmentAll());

            }
        });
    }


    private void showList() {
        inv.setCellValueFactory(new PropertyValueFactory<>("inventoryNumber"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        cabinet.setCellValueFactory(new PropertyValueFactory<>("cabinet"));
        table_equipment.setItems(observableList);

        table_equipment.setRowFactory(new Callback<TableView<EquipmentEntity>, TableRow<EquipmentEntity>>() {
            @Override
            public TableRow<EquipmentEntity> call(TableView<EquipmentEntity> param) {
                final TableRow<EquipmentEntity> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Delete");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION);
                        alertDelete.setTitle("Удалить оборудование");
                        alertDelete.setHeaderText("Вы действительно хотете удалить оборудование ?");
                        Optional<ButtonType> option = alertDelete.showAndWait();
                        if (option.get() == ButtonType.OK) {
                            table_equipment.getItems().remove(row.getItem());
                            mySqlEquipment.deleteEquipment(row.getItem());
                        } else if (option.get() == ButtonType.CANCEL) alertDelete.close();
                    }
                });
                final MenuItem updateMenuItem = new MenuItem("Update");
                updateMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            ControllerUpdateEquipment.setEquipment(row.getItem());
                            openWindowEquipmentUpdate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                contextMenu.getItems().add(removeMenuItem);
                contextMenu.getItems().add(updateMenuItem);
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu) null)
                                .otherwise(contextMenu)
                );
                return row;
            }
        });
    }


    private void setButtonTitledPane() {
        boxCabinet.getChildren().clear();
        boxType.getChildren().clear();
        boxStatus.getChildren().clear();
        List<StatusEntity> statusEntities = mySqlStatus.getStatusAll();
        for (StatusEntity status : statusEntities) {
            Button btn = new Button();
            btn.setText(status.getNameStatus());
            btn.setPrefWidth(130.0);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    updateObservableList(mySqlEquipment.getEquipmentByStatus(status));

                }
            });
            boxStatus.getChildren().add(btn);
        }
        scrollStatus.setContent(boxStatus);

        List<TypeOfEquipmentEntity> types = mySqlType.getTypeAll();
        for (TypeOfEquipmentEntity type : types) {
            Button btn = new Button();
            btn.setText(type.getTypeName());
            btn.setPrefWidth(130.0);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    updateObservableList(mySqlEquipment.getEquipmentByType(type));
                }
            });
            boxType.getChildren().add(btn);
        }
        scrollType.setContent(boxType);

        List<CabinetEntity> cabinetEntities = mySqlCabinet.getCabinetAll();
        for (CabinetEntity cabinet : cabinetEntities) {
            Button btn = new Button();
            btn.setText(cabinet.getNumCabinet());
            btn.setPrefWidth(130.0);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    updateObservableList(mySqlEquipment.getEquipmentByCabinet(cabinet));

                }
            });
            boxCabinet.getChildren().add(btn);
        }
        scrollCabinet.setContent(boxCabinet);

    }

    public void openWindowEqipment(ActionEvent actionEvent) throws Exception {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/createEuipment.fxml"));
        primaryStage.setTitle("Фиксики");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public void openWindowEquipmentUpdate() throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/updateEquipment.fxml"));
        primaryStage.setTitle("Фиксики");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public void openWindowCabinet(ActionEvent actionEvent) throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/createCabinet.fxml"));
        primaryStage.setTitle("Фиксики");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void openWindowType(ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/createType.fxml"));
        stage.setTitle("Фиксики");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void searchName(ActionEvent actionEvent) {
        if (searchName.getText().length() != 0) {
            updateObservableList(mySqlEquipment.searh(searchName.getText()));
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("введите название для поиска");
            alert.show();
        }
    }

    public void updateObservableList(List<EquipmentEntity> list) {
        observableList.clear();
        observableList.addAll(list);
        showList();
    }

    @Override
    public void updateUI() {
        updateObservableList(mySqlEquipment.getEquipmentAll());
    }
}
