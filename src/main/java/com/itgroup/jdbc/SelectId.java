package com.itgroup.jdbc;

import com.itgroup.dao.MemberDao;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Scanner;

public class SelectId {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("all,남자,여자 중 1개 입력 :");
        String gender = scan.next();

        MemberDao dao = new MemberDao();
        // List<Member> allMember = dao.selectMenber(gender);
        //ystem.out.println("항목 개수 : " + allMember.size());

        //for(Member bean:allMember){
          //  ShowData.printBean(bean);
          //  }
        
    }
}
