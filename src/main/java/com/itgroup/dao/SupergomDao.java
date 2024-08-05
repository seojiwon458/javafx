package com.itgroup.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SupergomDao {
  //4)
    private String driver;
    private String url = null;
    private String id = null;
    private String password = null;
    //생성자 만들기
    public SupergomDao() {

        this.driver = "oracle.jdbc.driver.OracleDriver";
        this.url = "jdbc:oracle:thin:@localhost:1521:xe";
        this.id = "gomdori";
        this.password = "butterfly";
        //3)
        try{
            //5)
            Class.forName(driver); //동적 개체 생성 //핵심
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    protected Connection getConnection() {
        Connection conn =null;
        try{
            conn = DriverManager.getConnection(url,id,password); //핵심
            if(conn!=null){
                System.out.println("접속 성공");
            } else{
                System.out.println("접속 실패");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{

            }catch(Exception ex){
                ex.printStackTrace();

            }
        }
        return conn;
    }
}
