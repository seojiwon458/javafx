package com.itgroup.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {//Application < launch 상속
    @Override
    public void start(Stage stage) throws Exception {
        VBox container = new VBox(); // 컨테이너 박스
        container.setPrefWidth(350); // 넓이
        container.setPrefHeight(150); // 높이
        container.setAlignment(Pos.CENTER); //포지션 센터;
        container.setSpacing(20);


        Label label = new Label(); // 컨트롤
        label.setText("Hello! java FX"); //font 텍스트
        label.setFont(new Font(40)); // font 사이즈

        Button button = new Button(); //버튼 생성 구문
        button.setText("확인"); //버튼 텍스트에 '확인'
        //버튼에 람다식을 이용한 이벤트 처리
        button.setOnAction((event)->{
            System.out.println(event.toString());
            String text = label.getText();
            System.out.println(text + "호호호"); //확인 버튼 누르면 '호호호'출력
            Platform.exit(); //어플리케이션 종료
        });

        container.getChildren().add(label); // 담는것
        container.getChildren().add(button);


        Scene scene = new Scene(container); // 승급
        stage.setScene(scene); // 장면
        stage.setTitle("첫번째 애플리케이션"); // 제목
        stage.show(); // 보여준다.


    }

    public static void main(String[] args) {
        // launch 메소드 호출시 자동으로 start 메소드를 실행시켜 줍니다.
        launch(args);
    }
}
