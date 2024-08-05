package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.Scanner;

public class UpdateMain {
    public static void main(String[] args) {

        //특정 상품에 대한 정보를 수정
        // insert는 무조건 1개씩 추가,
        ProductDao dao = new ProductDao();
        Product bean = new Product();

        Scanner scan = new Scanner(System.in);
        System.out.print("상품 번호 : ");
        int pnum = scan.nextInt();

        System.out.print("상품 이름 : ");
        String name = scan.next();

        //SQL 보고 매칭시켜서 추가하기
        bean.setPnum(pnum);//시퀀스로 대체예정
        bean.setCompany("AB식품");
        bean.setName(name);
        bean.setImage01("AA.png");
        bean.setImage02("BB.png");
        bean.setImage03("CC.png");
        bean.setStock(9999);
        bean.setPrice(1111);
        bean.setCategory("bread");
        bean.setContents("최악이에요");
        bean.setPoint(015); //포인트는 기본 값 사용 예정
        bean.setInputdate("2024/07/17"); //입고 일자도 기본 값 사용 예정
        int cnt = -1;//-1을 실패한 경우라고 가정 ,0개를 수용할수 있기 때문 0은 사용하면 안된다
       cnt = dao.updateData(bean);
       if (cnt==-1){
           System.out.println("상품 수정에 '실패'하였습니다");
       }else{
           System.out.println("상품 수정에 '성공'하였습니다");
       }
    }
}
