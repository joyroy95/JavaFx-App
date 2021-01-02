package com.dbbl.dmssupport.controller.maincontroller;

import com.dbbl.dmssupport.controller.page1.Page1;
import com.dbbl.dmssupport.database.DatabaseConnection;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    Connection conn = null;
    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane slider;

    @FXML
    private AnchorPane ap;

    @FXML
    void page1(MouseEvent event) {
        Parent root=null;
        try {
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/com/dbbl/dmssupport/view/page1/page1.fxml"));
            root = loader.load();
            Page1 page1 = loader.getController();
            page1.setConnection(this.conn);
            ap.getChildren().removeAll();
            ap.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void page2(MouseEvent event) {
        loadPage("page2");
    }

    @FXML
    void page3(MouseEvent event) {
        loadPage("page3");
    }

    @FXML
    void page4(MouseEvent event) {

    }

    @FXML
    void page5(MouseEvent event) {

    }

    @FXML
    void page6(MouseEvent event) {

    }

    private void loadPage(String page)
    {
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
            ap.getChildren().removeAll();
            ap.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        slider.setTranslateX(-176);
        TranslateTransition slide1 = new TranslateTransition();
        slide1.setDuration(Duration.seconds(0.4));
        slide1.setNode(slider);

        slide1.setToX(0);
        slide1.play();

        slider.setTranslateX(-176);

        slide1.setOnFinished((ActionEvent e)-> {
            Menu.setVisible(false);
            MenuBack.setVisible(true);
        });

        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            });
        });

        createDatabaseConnection();

    }

    public  void createDatabaseConnection()
    {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = new DatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.conn = databaseConnection.conn;
    }
}
