package com.itgroup.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ConfirmCounter implements Initializable {

    @FXML private TextArea textArea;
    @FXML private Button btnOK;
    int cnt; //카운터 변수
    DecimalFormat df = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cnt =1;
        df = new DecimalFormat("00");
    }
    public void btnOKAction(ActionEvent event) {
        String value = btnOK.getText();
        String massage = value + "버튼" + df.format(cnt) + "번 눌러짐";
        cnt++;
        //textArea.setText(value); 기존 내용 덮어쓰기
        //textArea.appendText(message+"\n"); //기존 내용 추가하기
    }

}
