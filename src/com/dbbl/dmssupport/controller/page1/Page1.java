package com.dbbl.dmssupport.controller.page1;

import com.dbbl.dmssupport.common.vo.ListVo;
import com.dbbl.dmssupport.controller.maincontroller.Main;
import com.dbbl.dmssupport.model.QueryException.QueryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Page1 implements Initializable {

    private Connection connection;
    List<ListVo> loadStatus= new ArrayList<>();
    ObservableList<ListVo>  listVos ;
    @FXML
    private ComboBox<ListVo> comboBox;
    @FXML
    private TextField current_status;
    @FXML
    private TextField internal_reference_no;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main main = new Main();
        this.connection =  main.getDatabaseConnection();
        current_status.setDisable(true);
    }

    @FXML
    void internalReferKeyPress(KeyEvent event) {
        current_status.setText("");

        if(event.getCode().equals(KeyCode.TAB))
        {
            QueryException queryException = new QueryException(this.connection);
            try{
                String internalReferenceNo = internal_reference_no.getText();

                String store = queryException.fetchCurrentStatus(internalReferenceNo);
                if(store=="")
                {
                    showDialogForNoDataFound();
                }
                else
                {
                    showDialogForSuccessfulDataFound();
                }
                current_status.setText(store);
                loadStatus = queryException.loadStatusForDropDown();

                listVos = FXCollections.observableArrayList();
                listVos.addAll(loadStatus);
                comboBox.getItems().clear();
                comboBox.setItems(listVos);
                comboBox.setConverter(new StringConverter<ListVo>() {
                    @Override
                    public String toString(ListVo object) {
                        return object.getListValue();
                    }

                    @Override
                    public ListVo fromString(String string) {
                        return null;
                    }
                });
                comboBox.valueProperty().addListener((obs, oldval, newval) -> {
                    if(newval != null)
                        System.out.println("Selected name: " + newval.getListValue()
                                + ". ID: " + newval.getListKey());
                });
                //connection.close();    //closing connection
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    public void showDialogForNoDataFound()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Message");
        alert.setContentText("No Data Found!!!");
        alert.showAndWait();
    }

    public void showDialogForSuccessfulDataFound()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message");
        alert.setContentText("Fetch Information Successfully.");
        alert.showAndWait();
    }
}
