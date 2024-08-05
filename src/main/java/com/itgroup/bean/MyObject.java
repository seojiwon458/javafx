package com.itgroup.bean;

// module-info.java 파일에 다음 코드를 작성해 주세요.
// opens com.itgroup.bean to javafx.base;

public class MyObject { // 품목의 이름과 이미지 정보를 저장하는 클래스
    private String name ;
    private String image ;

    public MyObject() {
    }

    public MyObject(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
