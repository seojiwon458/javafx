package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.List;
import java.util.Scanner;

public class SelectToralCount {
    public static void main(String[] args) {

        //모든 상품,특정 카테고리 조회
        Scanner scan = new Scanner(System.in);
        System.out.print("all,beverage,bread,macaron,cake 중 1개 입력");

        String category = scan.next();

        ProductDao dao = new ProductDao();
        //전체도 List,부분도 List형태
        int totalCount = dao.getTotalCount(category);
       if(category.equals("all")){
           System.out.println("상품 전체 개수 : " + totalCount);
       }else{
           String message = "카테고리 %s의 개수 : %d\n";
           System.out.printf(message,category,totalCount);
       }

    }
}
