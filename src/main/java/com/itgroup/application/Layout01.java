package com.itgroup.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Layout01 extends Application {
    //푸쉬 테스트 철수1
    //오늘은 날씨가 업청 더워여
    //오늘 점신은 시원한 국밥을 먹어요
    //철수가 먼저 푸쉬함
    @Override
    public void start(Stage stage) throws Exception {

        HBox container = new HBox();
        //insert 클래스 : 컨테이너 내부의 경계 사이의 간격(여백)을 정의해주는 클래스
        container.setPadding(new Insets(10));
        //콘테이너와 버튼의 공간 간격(여백)을 정의해주는 클래스
        container.setSpacing(20);
        // 콘테이너 백그라운드 색깔 변경, 색깔 불투명도 출력
        String myStyle = "-fx-background-color: purple;-fx-opacity:0.8;";
        container.setStyle(myStyle);

        //글자 입력이 가능한 한줄짜리 평점
        TextField textField = new TextField();
        textField.setPrefWidth(200);

        Button button = new Button();
        button.setText("확인");
        button.setPrefWidth(60);
        container.getChildren().add(textField);
        container.getChildren().add(button);
        // 텍스트 필드의 컬러를 바꾸는 구문, 텍스트 필드의 글자색을 바꾸는 구문
        textField.setStyle("-fx-background-color: lightyellow; -fx-text-fill: blue;");

        button.setOnAction((event)->{
            String text = textField.getText();
            System.out.println(text + "하하하");
            Platform.exit();
        });

        Scene scene = new Scene(container,330,120);
        stage.setScene(scene);
        stage.setTitle("레이 아웃 01");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
