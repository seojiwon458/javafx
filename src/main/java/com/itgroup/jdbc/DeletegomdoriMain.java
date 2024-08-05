package com.itgroup.jdbc;

import com.itgroup.dao.MemberDao;

import java.util.Scanner;

public class DeletegomdoriMain {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("삭제할 아이디 : ");
        String id = scan.next();

        MemberDao dao = new MemberDao();
        int cnt  = -1;
        cnt = dao.deleteData(id);

        if(cnt==-1){
            System.out.println("회원 아이디 삭제에 '실패'하였습니다.");
        }else{
            System.out.println("회원 아이디 삭제에 '성공'하였습니다.");
        }

    }
}
