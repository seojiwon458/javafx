package com.itgroup.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
//*이용하면 전체를 포괄할 수 있다
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginTest implements Initializable {
    @FXML private Label fxmlResult;
    @FXML private TextField fxmlID;
    @FXML private PasswordField fxmlPassword;
    @FXML private Button btnOK,btnCancel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnOK.setOnAction((event)->OkAction(event));
        btnCancel.setOnAction(event -> InitAction(event));

        //속성 바인딩 기능 : 특정 컨트롤의 속성 값이 변경이 되면, 자동적으로 다른 컨트롤의 속성 값이 변경되도록 하는 기능
        fxmlResult.textProperty().bind(fxmlID.textProperty());
    }

    private void OkAction(ActionEvent event){ //로그인 버튼

        boolean isCheck = checkInputState();

        if(isCheck){ //입력 조건에 충분
            boolean logincheck = this.getAccountCheck();
            if(logincheck){
                System.out.println("로그인 성공");
            }else{
                System.out.println("로그인 실패");
            }
        }else{ //입력 조건에 불충분
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("데이터 확인");
            alert.setHeaderText("입력 데이터 누락");
            alert.setContentText("아이디 또는 비번이 누락 되었습니다");
            alert.showAndWait();
        }
    }

    private boolean getAccountCheck() {
        //입력한 내용과 실제 계정 정보와 동일한지 체크한다.
        //다음 변수들은 실제 데이터 베이스에서 읽어 와야 하는 정보입니다
        String id = "hong",password = "1234";
        if(id.equals(fxmlID.getText()) && password.equals(fxmlPassword.getText())){
            return true;
        }else{
            return false;
        }
    }

    private void InitAction(ActionEvent event){ //초기화 버튼
        fxmlID.setText("");
        fxmlPassword.setText("");
        fxmlID.requestFocus(); //ID입력란에 포커스 주기
    }

    private boolean checkInputState(){
        //유효성 검사(validation check) : 입력되어야 할 데이터들이 제대로 잘 입력이 되었는지를 확인하는 절차
        //사용처 : 회원 가입,게시물 등록,상품 등록 등등
        //입력 양식에 대한 유효성 검사를 수행합니다.

        if(fxmlID.getText().length() == 0 ||
                fxmlPassword.getText().length() == 0 ||
                fxmlID == null ||
                fxmlPassword == null){
            return false;
        }else {
            return true;
        }
    }
}
