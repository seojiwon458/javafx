package com.itgroup.dao;

import com.itgroup.bean.Product;
import com.itgroup.utility.Paging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ProductDao extends SuperDao {

    //생성자 만들기
    public ProductDao() {
        //2)
        super();
    }

    public List<Product> selectAll() {//모든 상품 조회하기
        Connection conn = null;
        //오라클의 세미콜롬(;)은 적지 않는다
        String sql = "select * from products order by pnum desc";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Product> allData = new ArrayList<Product>();

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            //내려가려면 next사용
            while(rs.next()){

                Product bean = this.makeBean(rs);
                allData.add(bean);

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();

            }
        }
        return allData;
    }

    private Product makeBean(ResultSet rs) {
        Product bean = new Product();
        try{
            bean.setPnum(rs.getInt("pnum"));
            bean.setName(rs.getString("name"));
            bean.setCompany(rs.getString("company"));
            bean.setImage01(rs.getString("image01"));
            bean.setImage02(rs.getString("image02"));
            bean.setImage03(rs.getString("image03"));
            bean.setStock(rs.getInt("stock"));
            bean.setPrice(rs.getInt("price"));
            bean.setCategory(rs.getString("Category"));
            bean.setContents(rs.getString("Contents"));
            bean.setPoint(rs.getInt("point"));
            bean.setInputdate(rs.getString("Inputdate"));

        }catch(Exception ex){
            ex.printStackTrace();
        } return bean;
    }

    public List<Product> selectByCategory(String category) {
        Connection conn = null ;

        String sql = " select * from products " ;

        boolean bool = category == null || category.equals("all");
        if(!bool){
            sql += " where category = ? ";
        }

        //문장 객체
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;

        List<Product> allData = new ArrayList<>();
        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            //값을 바꾸려면 set,치환하고 실행하기 때문에
            //set는 executeQuery 앞에

            if (!bool) {
                pstmt.setString(1,category);
            }
            rs = pstmt.executeQuery() ;

            while(rs.next()){
                Product bean = this.makeBean(rs);
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
        return allData ;
    }

    public Product selectByPK(int pnum) {
        Connection conn = null ;
        String sql = " select * from products " ;
        sql += " where pnum = ?";

        PreparedStatement pstmt = null ;
        ResultSet rs = null ;

        Product bean = null;
        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,pnum);
            rs = pstmt.executeQuery() ;
            if(rs.next()){
                 bean = this.makeBean(rs);
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
        return bean ;
    }

    public int insertData(Product bean) {
        // Resultset은 출력할 때 사용
        //데이터가 잘 들어왔는지 확인
        System.out.println(bean);
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = " insert into products(pnum,name,company,image01,image02,image03,stock,price,category,contents,point,inputdate)";
        sql +=" values(Num.nextval,?,?,?,?,?,?,?,?,?,default,default)" ;


        try{
            //객체 생성
            conn = super.getConnection();
            //자동커밋을 끄고 한번에 커밋으 하겠다 선언,
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);


            //치환할때는 excuteUpdate전에 구문 사용
            //cnt 삭제,추가로 업데이트 사용
            pstmt.setString(1,bean.getName());
            pstmt.setString(2,bean.getCompany());
            pstmt.setString(3,bean.getImage01());
            pstmt.setString(4,bean.getImage02());
            pstmt.setString(5,bean.getImage03());
            pstmt.setInt(6,bean.getStock());
            pstmt.setInt(7,bean.getPrice());
            pstmt.setString(8,bean.getCategory());
            pstmt.setString(9,bean.getContents());

            cnt = pstmt.executeUpdate();

            conn.commit();

        }catch(Exception ex){
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }finally{
            try{
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int updateData(Product bean) {

            System.out.println(bean);
            int cnt = -1;
            Connection conn = null;
            PreparedStatement pstmt = null;

            String sql = " update products set name = ?,company = ?,image01 = ?,image02 = ?,image03 = ?,stock = ?,price = ?,category = ?,contents = ?,point = ?,inputdate = ? ";
            sql += " where pnum = ? " ;


            try{
                //객체 생성
                conn = super.getConnection();
                //자동커밋을 끄고 한번에 커밋으 하겠다 선언,
                conn.setAutoCommit(false);
                pstmt = conn.prepareStatement(sql);


                //치환할때는 excuteUpdate전에 구문 사용
                //cnt 삭제,추가로 업데이트 사용
                pstmt.setString(1,bean.getName());
                pstmt.setString(2,bean.getCompany());
                pstmt.setString(3,bean.getImage01());
                pstmt.setString(4,bean.getImage02());
                pstmt.setString(5,bean.getImage03());
                pstmt.setInt(6,bean.getStock());
                pstmt.setInt(7,bean.getPrice());
                pstmt.setString(8,bean.getCategory());
                pstmt.setString(9,bean.getContents());
                pstmt.setInt(10,bean.getPoint());
                pstmt.setString(11,bean.getInputdate());
                pstmt.setInt(12,bean.getPnum());

                cnt = pstmt.executeUpdate();

                conn.commit();

            }catch(Exception ex){
                ex.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }finally{
                try{
                    if(pstmt!=null){pstmt.close();}
                    if(conn!=null){conn.close();}
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }

            return cnt;
        }

    public int deleteData(int pnum) {

        System.out.println("기본 키 = " + pnum);
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = " delete from products ";
        sql += " where pnum = ? " ;


        try{
            //객체 생성
            conn = super.getConnection();
            //자동커밋을 끄고 한번에 커밋을 하겠다 선언,
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,pnum);

            cnt = pstmt.executeUpdate();

            conn.commit();

        }catch(Exception ex){
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }finally{
            try{
                if(pstmt!=null){pstmt.close();}
                if(conn!=null){conn.close();}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int getTotalCount(String category) {
        //all이거나 null이면 전부다 찾으는 구문
        int getTotalCount = 0;
        String sql = " select count (*) as mycnt from products ";
        boolean bool = category == null || category.equals("all");
        //카테고리를 넣지 않으면 실행되지 않음
        if(!bool){
            sql += " where category = ? ";
        }

        Connection conn = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            if(!bool){
                pstmt.setString(1,category);
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

    public List<Product> getPaginationData(Paging pageInfo) {
        Connection conn = null ;

        String sql = " select pnum, name, company , image01, image02, image03, stock, price, category, contents, point, inputdate ";
        sql += " from ( ";
        sql += " select pnum, name, company , image01, image02, image03, stock, price, category, contents, point, inputdate, ";
        sql += " rank() over(order by pnum desc) as ranking ";
        sql += " from products ";



        // mode가 'all'이 아니면 where 절이 추가로 필요합니다.
        String mode = pageInfo.getMode();
        boolean bool = mode.equals(null) || mode.equals("null") || mode.equals("") || mode.equals("all");

        if (!bool) {
            sql += " where category = ? ";
        }

        sql += " ) ";
        sql += " where ranking between ? and ? ";
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;

        List<Product> allData = new ArrayList<>();
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
                Product bean = this.makeBean(rs);
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


