package com.dbbl.dmssupport.controller.maincontroller;

import com.dbbl.dmssupport.database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {
    double x, y;
    Connection conn = null;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/com/dbbl/dmssupport/view/mainview/main.fxml"));
        //primaryStage.initStyle(StageStyle.UNDECORATED);

        /*root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getSceneX()- x );
            primaryStage.setY(event.getSceneY() - y);
        });*/
        primaryStage.setTitle("DMS Support");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

    }



    public Connection getDatabaseConnection()
    {
        return this.conn;
    }


    public static void main(String[] args) {
        Main main = new Main();
       // main.createDatabaseConnection();
        launch(args);
    }
}
