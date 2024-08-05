package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.Scanner;

public class InsertMain {
    public static void main(String[] args) {

        //관리자가 상품을 1개 등록 (매개변수,반환타입 생각)
        //insert는 무조건 1개씩 추가,
        ProductDao dao = new ProductDao();
        Product bean = new Product();

        Scanner scan = new Scanner(System.in);
        System.out.println("상품 이름 : ");
        String name = scan.next();

        //SQL 보고 매칭시켜서 추가하기
        bean.setPnum(0);//시퀀스로 대체예정
        bean.setCompany("AB식품");
        bean.setName(name);
        bean.setImage01("xx.png");
        bean.setImage02("yy.png");
        bean.setImage03("zz.png");
        bean.setStock(1234);
        bean.setPrice(5678);
        bean.setCategory("bread");
        bean.setContents("맛없어요");
        bean.setPoint(0); //포인트는 기본 값 사용 예정
        bean.setInputdate(null); //입고 일자도 기본 값 사용 예정
        int cnt = -1;//-1을 실패한 경우라고 가정 ,0개를 수용할수 있기 때문 0은 사용하면 안된다
        cnt = dao.insertData(bean);

        if (cnt==-1){
            System.out.println("상품 등록에 '실패'하였습니다");
        }else{
            System.out.println("상품 등록에 '성공'하였습니다");
        }
    }
}
