package com.itgroup.jdbc;

import com.itgroup.bean.gomdori;
import com.itgroup.dao.MemberDao;


import java.util.List;
import java.util.Scanner;

public class SelectgomdoriMain {
    public static void main(String[] args) {
        //모든 상품,특정 카테고리 조회
        Scanner scan = new Scanner(System.in);
        System.out.print("all,남자,여자 중 1개 입력");

        String gender = scan.next();

        MemberDao dao = new MemberDao();
        //전체도 List,부분도 List형태
        List<gomdori> allgomdori = dao.selectBygender(gender);
        System.out.println( gender +"인원 :" + allgomdori.size());

        for (gomdori bean : allgomdori) {
            ShowgomdoriData.PrintBean(bean);
        }
    }
}
