package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.List;

public class SelectAllMain {
    public static void main(String[] args) {
        //모든 상품 조회
        ProductDao dao = new ProductDao();//1)
        List<Product> allProduct = dao.selectAll();
        System.out.println(allProduct.size());

        for(Product bean : allProduct){
            ShowData.PrintBean(bean);
        }

    }
}
