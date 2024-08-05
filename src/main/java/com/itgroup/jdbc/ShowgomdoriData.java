package com.itgroup.jdbc;

import com.itgroup.bean.gomdori;

import java.lang.reflect.Member;

public class ShowgomdoriData {
    public static void PrintBean(gomdori bean) {
        String id = bean.getId();
        String name = bean.getName();
        String ssn = bean.getssn();
        String address = bean.getaddress();
        String gender = bean.getGender();
        String email = bean.getEmail();
        String hiredate = bean.getHiredate();


        System.out.println("아이디 : " + id);
        System.out.println("이름 : " + name);
        System.out.println("주민 번호: " + ssn);
        System.out.println("주소 : " + address);
        System.out.println("성별 : " + gender);
        System.out.println("이메일 : " + email);
        System.out.println("입사일 : " + hiredate);
        System.out.println("=======================");
    }

}
