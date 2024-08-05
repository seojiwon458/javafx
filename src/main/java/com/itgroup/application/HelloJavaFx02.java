package com.itgroup.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloJavaFx02 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/com/itgroup/fxml/" + "HelloJavaFx02.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent container = fxmlLoader.load() ;
        Scene scene = new Scene(container);
        stage.setTitle("xml로 만들기");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
