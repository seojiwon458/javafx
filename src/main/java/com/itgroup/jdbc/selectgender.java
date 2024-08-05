package com.itgroup.jdbc;

import com.itgroup.bean.gomdori;
import com.itgroup.dao.MemberDao;

import java.util.List;
import java.util.Scanner;

public class selectgender {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("all,남,여 중 1개 입력");

        String category = scan.next();

        MemberDao dao = new MemberDao();
        //전체도 List,부분도 List형태
        List<gomdori> allgomdori = dao.selectBygender(category);
        System.out.println("사람 인원 :" + allgomdori.size());

        for (gomdori bean : allgomdori){
            SelectMenber.PrintBean(bean);
        }
    }
}
