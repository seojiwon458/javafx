package com.itgroup.application;

import com.itgroup.utility.Utility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CoffeeExam extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        String fxmlFile = Utility.FXML_PATH + "CoffeeExam.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent container = fxmlLoader.load(); //승급
        Scene scene = new Scene(container);
        stage.setTitle("App Main");
        stage.setScene(scene);
        stage.show();

        String myStyle = getClass().getResource(Utility.CSS_PATH + "CoffeeStyle.css").toString();
        scene.getStylesheets().add(myStyle);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
