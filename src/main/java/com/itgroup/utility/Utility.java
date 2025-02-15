package com.itgroup.utility;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Utility {

    //fxml 파일이 있는 경로
    public static final String FXML_PATH = "/com/itgroup/www/fxml/";
    public static final String IMAGE_PATH = "/com/itgroup/www/images/";
    public static final String CSS_PATH = "/com/itgroup/www/css/";
    public static final String DATA_PATH = "\\src\\main\\java\\com\\itgroup\\data";

    private static Map<String, String> categoryMap = new HashMap<>();

    private static String makeMapData(String category, String mode){
        // 사용자가 카테고리 선택시 '한글'을 보고, 선택하므로 key에 한글 이름이 들어 가야 합니다.
        // 맵 자료 구조는 value를 이용하여 key 검색을 못합니다.

        // mode가 "key"(키 찾기), "value"(값 찾기)
        categoryMap.put("음료수", "beverage");
        categoryMap.put("빵", "bread");
        categoryMap.put("마카롱", "macaron");
        categoryMap.put("케익", "cake");

        if(mode.equals("value")){ // key로 value 찾기
            return categoryMap.get(category) ;

        }else{ // value으로 key찾기
            for(String key : categoryMap.keySet()){
                if(categoryMap.get(key).equals(category)){
                    return key ; // value가 발견되었으므로 key를 반환합니다.
                }
            }
            return null ; // key가 없군요
        }
    }

    public static String getCategoryName(String category, String mode) {
        return makeMapData(category, mode);
    }

    public static LocalDate getDatePicker(String inputDate) {
        // 문자열을 LocalDate 타입으로 변환하여 반환합니다.
        // 회원 가입 일자, 게시물 작성 일자, 상품 등록 일자 등에서 사용할 수 있습니다.
        System.out.println(inputDate);

        int year = Integer.valueOf(inputDate.substring(0, 4));
        int month = Integer.valueOf(inputDate.substring(5, 7));
        int day = Integer.valueOf(inputDate.substring(8));

        return LocalDate.of(year, month, day);
    }


    public static void showAlert(Alert.AlertType alertType, String[] message) {
        //단순 메세지 박스를 보여주기 위한 유틸리티 메소드
        Alert alert = new Alert(alertType);
        alert.setTitle(message[0]);
        alert.setHeaderText(message[1]);
        alert.setContentText(message[2]);
        alert.showAndWait();

    }
//    //유틸리티에 만든이유는 재활용을 하기 위해
//    public static Parent getFxmlParent(String fxmlName) throws Exception{
//
//        //입력된 fxml파일 이름을 이용하여, Fxml의 최상위 컨테이너를 반환해준다.
//        Parent parent = null;
//        FXMLLoader fxmlLoader = new FXMLLoader(getC);
//        return parent;
//    }
}
