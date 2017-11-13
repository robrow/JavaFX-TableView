package com.embero.jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JFXApp
 * Description
 *
 * https://stackoverflow.com/questions/30233068/javafx-consuming-rest-service-and-displaying-the-data-in-front-end
 *
 * @author Robin Rowinski
 * created on 09.11.17
 */
public class JFXApp extends Application {

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JFX App - Robin");
        Pane pane = (Pane) FXMLLoader.load(getClass().getResource("/overview.fxml"));
        pane.setMinSize(640, 480);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
