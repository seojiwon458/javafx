package com.itgroup.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

public class ButtonEventcontroller implements Initializable {

    @FXML private TextArea textArea;
    @FXML private Button btnOK;
    @FXML private Button btnCancle;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnOKAction(ActionEvent event) {
        String value = btnOK.getText();
        //textArea.setText(value); 기존 내용 덮어쓰기
        textArea.appendText(value+"\n"); //기존 내용 추가하기
    }

    public void btnCancelAction(ActionEvent event) {
        String value = btnCancle.getText();
        //textArea.setText(value); 기존 내용 덮어쓰기
        textArea.appendText(value+"\n"); //기존 내용 추가하기
    }
}

