package com.itgroup.jdbc;

import com.itgroup.bean.Product;

import java.lang.reflect.Member;

public class ShowData {
    public static void PrintBean(Product bean) {
        int pnum = bean.getPnum();
        String name = bean.getName();
        String company = bean.getCompany();
        String image01 = bean.getImage01();
        String image02 = bean.getImage02();
        String image03 = bean.getImage03();
        int stock = bean.getStock();
        int price = bean.getPrice();
        String Category = bean.getCategory();
        String Contents = bean.getContents();
        int point = bean.getPoint();
        String Inputdate = bean.getInputdate();

        System.out.println("상품 번호 : " + pnum);
        System.out.println("상품명 : " + name);
        System.out.println("제조 회사 : " + company);
        System.out.println("이미지01 : " + image01);
        System.out.println("이미지02 : " + image02);
        System.out.println("이미지03 : " + image03);
        System.out.println("재고 : " + stock);
        System.out.println("단가 : " + price);
        System.out.println("카테고리 : " + Category);
        System.out.println("상품 설명 : " + Contents);
        System.out.println("적립 포인트" + point);
        System.out.println("입고 일자 : " + Inputdate);
        System.out.println("=======================");
    }
}
