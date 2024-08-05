package com.itgroup.controller;

import com.itgroup.bean.MyObject;
import com.itgroup.utility.Utility;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewControl01 implements Initializable {

    @FXML private ListView listView;
    @FXML private TableView <MyObject> tableView;
    @FXML private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> dataList
                = FXCollections.observableArrayList("어린이","음료수","빵","동물");
        listView.setItems(dataList);

        //테이블 뷰의 각 컬럼의 값을 myobject 객체와 연결되도록 property를 세팅해주어야 합니다.
        //0번째 컬럼은 name변수이고  1번째 컬럼은 image입니다.
        TableColumn tcName = (TableColumn) tableView.getColumns().get(0);
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn tcImage = (TableColumn) tableView.getColumns().get(1);
        tcImage.setCellValueFactory(new PropertyValueFactory<>("image"));


        ChangeListener<Number> listListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                String message = "oldValue :" + oldValue + ", new value: " + newValue;
                System.out.println(message);

                int menu = newValue.intValue();
                System.out.println("선택한 인덱스: " + menu);

                ObservableList tableData = null; //데이블 뷰의 넣을 데이터 정보
                switch (menu){
                    case 0: //어린이
                        tableData = FXCollections.observableArrayList(
                                new MyObject("어린이01","child01.jpg"),
                        new MyObject("어린이02","child02.jpg"),
                        new MyObject("어린이03","child03.jpg"),
                        new MyObject("어린이04","child04.jpg")
                        );
                        break;
                    case 1: //음료수
                        tableData = FXCollections.observableArrayList(
                                new MyObject("아메리카노","americano01.png"),
                                new MyObject("카푸치노","cappuccino01.png"),
                                new MyObject("바닐라라떼","vanilla_latte_01.png"),
                                new MyObject("우유","milk01.jpg")
                        );
                        break;
                    case 2: //빵
                        tableData = FXCollections.observableArrayList(
                                new MyObject("바게트01","french_baguette_01.png"),
                                new MyObject("바게트02","french_baguette_02.png"),
                                new MyObject("바게트03","french_baguette_03.png"),
                                new MyObject("치아버터","ciabatta_01.png")
                        );
                        break;
                    case 3: //동물
                        tableData = FXCollections.observableArrayList(
                        new MyObject("동물01","animal01.jpg"),
                                new MyObject("동물02","animal02.jpg"),
                                new MyObject("동물03","animal03.jpg"),
                                new MyObject("동물04","animal04.jpg")
                        );
                        break;
                } //end Swich

                if(tableData !=null){
                    tableView.setItems(tableData);
                }

            }
        };
        //리스트 뷰의 색인 정보가 변경되었을 때 listListener가 동작
        listView.getSelectionModel().selectedIndexProperty().addListener(listListener);
        ChangeListener<MyObject> tableListener = new ChangeListener<MyObject>() {
            @Override
            public void changed(ObservableValue<? extends MyObject> observableValue, MyObject oldValue, MyObject newValue) {
                if(newValue != null){
                    String imageFile = Utility.IMAGE_PATH+newValue.getImage();
                    System.out.println("이미지 파일");
                    System.out.println(imageFile);
                    Image someImage = new Image(getClass().getResource(imageFile).toString());
                    imageView.setImage(someImage);
                }
            }
        };

        tableView.getSelectionModel().selectedItemProperty().addListener(tableListener);
    }

    public void handleBtnOkAction(ActionEvent event) {
        //확인버튼 이벤트
        Object item = listView.getSelectionModel().getSelectedItems().toString();

        if(item == null)
        {
            System.out.println("리스트를 선택해주세요");
        }else {
            System.out.println("리스트 뷰 선태된 항목 :" + item);
        }
        MyObject bean = tableView.getSelectionModel().getSelectedItem();
        //변수를 Utility에 넣어 놀고 재사용하기 위해
        if(bean == null){
            String[] message = {"테이블 선택 여부","항목 미체크","테이블 뷰에서 항목을 선택해주세요 "};
                  Utility.showAlert(Alert.AlertType.INFORMATION,message);
        } else{
            System.out.println("선택되 품목 :" + bean.getName());
            System.out.println("선택되 이미지 :" + bean.getImage());

        }
    }

    public void handleBtnCancelAction(ActionEvent event) {
        //취소버튼 이벤트
        System.out.println("종료합니다");
        Platform.exit();
    }
}
