package com.itgroup.jdbc;

import com.itgroup.bean.gomdori;
import com.itgroup.dao.MemberDao;

import java.util.Scanner;

public class gomdoriInsertMain {
    public static void main(String[] args) {

        //관리자가 상품을 1개 등록 (매개변수,반환타입 생각)
        //insert는 무조건 1개씩 추가,
        MemberDao dao = new MemberDao();
        gomdori bean = new gomdori();

        Scanner scan = new Scanner(System.in);
        System.out.println("성별 : ");
        String gender = scan.next();

        System.out.println("아이디 : ");
        String id = scan.next();

        System.out.println("맴버 이름 : ");
        String name = scan.next();

        //SQL 보고 매칭시켜서 추가하기
        bean.setId(id);
        bean.setName(name);
        bean.setSSN("980423-111111");
        bean.setaddres("강남구");
        bean.setGender(gender);
        bean.setEmail("seo8145s@naver.com");
        bean.setHiredate("2024/07/15");


        int cnt = -1;//-1을 실패한 경우라고 가정 ,0개를 수용할수 있기 때문 0은 사용하면 안된다
        cnt = dao.insertData(bean);

        if (cnt==-1){
            System.out.println("맴버 등록에 '실패'하였습니다");
        }else{
            System.out.println("맴버 등록에 '성공'하였습니다");
        }
    }
}
