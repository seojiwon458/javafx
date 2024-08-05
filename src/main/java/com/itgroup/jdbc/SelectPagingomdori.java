package com.itgroup.jdbc;

import com.itgroup.bean.gomdori;
import com.itgroup.dao.MemberDao;
import com.itgroup.utility.Paging;

import java.util.List;
import java.util.Scanner;

public class SelectPagingomdori {
    public static void main(String[] args) {

        // 검색 모드와 페이지 네이션 기능을 구현합니다.
        Scanner scan = new Scanner(System.in);
        System.out.println("몇 페이지 볼꺼니?");
        String pageNumber = scan.next();

        System.out.println("페이지 당 몇 건씩 볼꺼니?");
        String pageSize = scan.next(); //10

        System.out.print("all,남자,여자 중 1개 입력 : ");
        String mode = scan.next(); // 검색모드(무엇을 검색할 것인가?)

        MemberDao dao = new MemberDao();
        int totalCount = dao.getTotalCount(mode); //23

        String url = "prList.jsp";
        String keyword = "";
        Paging pageInfo = new Paging(pageNumber,pageSize,totalCount,url,mode,keyword);
        pageInfo.displayInformation();

        List<gomdori> membersList = dao.getPaginationData(pageInfo);

        for(gomdori bean:membersList){
            ShowgomdoriData.PrintBean(bean);

        }
    }
}
