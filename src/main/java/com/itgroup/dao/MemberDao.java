package com.itgroup.dao;

import com.itgroup.bean.Product;
import com.itgroup.bean.gomdori;
import com.itgroup.utility.Paging;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends SupergomDao {
    public MemberDao() {
        super();
    }

    public List<gomdori> selectAll() {//모든 상품 조회하기
        Connection conn = null;
        //오라클의 세미콜롬(;)은 적지 않는다
        String sql = "select * from members order by name desc";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<gomdori> allData = new ArrayList<gomdori>();

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            //내려가려면 next사용
            while (rs.next()) {

                gomdori bean = this.makeBean(rs);
                allData.add(bean);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return allData;
    }

    private gomdori makeBean(ResultSet rs) {
        gomdori bean = new gomdori();
        try {
            bean.setId(rs.getString("Id"));
            bean.setName(rs.getString("name"));
            bean.setSSN(rs.getString("ssn"));
            bean.setaddres(rs.getString("address"));
            bean.setGender(rs.getString("gender"));
            bean.setEmail(rs.getString("email"));
            bean.sethiredate(rs.getString("hiredate"));


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bean;
    }

    public List<gomdori> selectBygender(String Gender) {
        Connection conn = null;

        String sql = " select * from members ";

        boolean bool = Gender == null || Gender.equals("all");
        if (!bool) {
            sql += " where gender = ? ";
        }

        //문장 객체
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<gomdori> allData = new ArrayList<>();
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            //값을 바꾸려면 set,치환하고 실행하기 때문에
            //set는 executeQuery 앞에

            if (!bool) {
                pstmt.setString(1, Gender);
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {
                gomdori bean = this.makeBean(rs);
                allData.add(bean);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return allData;
    }

    public int insertData(gomdori bean) {

        System.out.println(bean);
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = " insert into members( id,name,ssn,address,gender,email,hiredate )";
        sql += " values (?,?,?,?,?,?,? )";

        try {
            //객체 생성
            conn = super.getConnection();
            //자동커밋을 끄고 한번에 커밋으 하겠다 선언,
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);


            //치환할때는 excuteUpdate전에 구문 사용
            //cnt 삭제,추가로 업데이트 사용
            pstmt.setString(1, bean.getId());
            pstmt.setString(2, bean.getName());
            pstmt.setString(3, bean.getssn());
            pstmt.setString(4, bean.getaddress());
            pstmt.setString(5, bean.getGender());
            pstmt.setString(6, bean.getEmail());
            pstmt.setString(7, bean.getHiredate());

            cnt = pstmt.executeUpdate();

            conn.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return cnt;

    }

    public int deleteData(String id) {
        System.out.println("회원 아이디 = " + id);
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = " delete from members ";
        sql += " where id = ? ";

        try {
            //객체 생성
            conn = super.getConnection();
            //자동커밋을 끄고 한번에 커밋을 하겠다 선언,
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, id);

            cnt = pstmt.executeUpdate();

            conn.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

            return cnt;
        }

    public int getTotalCount(String gender) {
        int getTotalCount = 0;
        String sql = " select count (*) as mycnt from members ";
        boolean bool = gender == null || gender.equals("all");
        //성별을 넣지 않으면 실행되지 않음
        if(!bool){
            sql += " where gender = ? ";
        }

        Connection conn = null ;

        PreparedStatement pstmt = null ;
        ResultSet rs = null ;

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            if(!bool){
                pstmt.setString(1,gender);
            }

            rs = pstmt.executeQuery() ;

            if(rs.next()){
                getTotalCount = rs.getInt("mycnt");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(rs!=null){rs.close();}
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        return getTotalCount;
    }

    public List<gomdori> getPaginationData(Paging pageInfo) {
        Connection conn = null ;

        String sql = " select id, name, ssn , address , gender , email , hiredate";
        sql += " from ( ";
        sql += " select id, name, ssn , address , gender , email , hiredate, ";
        sql += " rank() over(order by id desc) as ranking ";
        sql += " from members ";

        // mode가 'all'이 아니면 where 절이 추가로 필요합니다.
        String mode = pageInfo.getMode();
        boolean bool = mode.equals(null) || mode.equals("null") || mode.equals("") || mode.equals("all");

        if (!bool) {
            sql += " where gender = ? ";
        }

        sql += " ) ";
        sql += " where ranking between ? and ? ";
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;

        List<gomdori> allData = new ArrayList<>();
        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            if (!bool) {
                pstmt.setString(1, mode);
                pstmt.setInt(2,pageInfo.getBeginRow());
                pstmt.setInt(3,pageInfo.getEndRow());
            } else {
                pstmt.setInt(1,pageInfo.getBeginRow());
                pstmt.setInt(2,pageInfo.getEndRow());
            }

            rs = pstmt.executeQuery() ;

            while(rs.next()){
                gomdori bean = this.makeBean(rs);
                allData.add(bean);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(rs!=null){rs.close();}
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return allData;
    }
}


