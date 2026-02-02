package com.muimi.calculator;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class Calculator extends Application {
    @Override
    public void start(Stage stage){
        InputStream iconStream = getClass().getResourceAsStream("/images/favicon.ico");
        if (iconStream != null) {
            Image icon = new Image(iconStream);
            stage.getIcons().add(icon);
        }
        calculatorStageFrame calculator = new calculatorStageFrame();
        stage.setScene(calculator.getScene());
        stage.setTitle("Calculator");
        stage.setResizable(false);
        stage.show();
    }
}
