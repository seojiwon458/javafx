package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.List;
import java.util.Scanner;

public class SelectBranchCategory {
    public static void main(String[] args) {

        //모든 상품,특정 카테고리 조회
        Scanner scan = new Scanner(System.in);
        System.out.print("all,beverage,bread,macaron,cake 중 1개 입력");

        String category = scan.next();

        ProductDao dao = new ProductDao();
        //전체도 List,부분도 List형태
        List<Product> allProduct = dao.selectByCategory(category);
        System.out.println("상품 개수 :" + allProduct.size());

        for (Product bean : allProduct){
            ShowData.PrintBean(bean);
        }
    }
}
