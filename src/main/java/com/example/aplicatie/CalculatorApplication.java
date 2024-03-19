package com.example.aplicatie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculatorApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 980, 620);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Polynomial Calculator");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
        //System.out.println(Math.round(0.00));
    }
}