package com.itgroup.controller;

import com.itgroup.bean.Person;
import com.itgroup.dao.PersonDao;
import com.itgroup.utility.Paging;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaginationController implements Initializable {

    @FXML private Label pageStatus;
    @FXML private VBox vBox;
    @FXML private TableView<Person>tableView;
    @FXML private Pagination pagination;

    PersonDao dao = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new PersonDao();

        setTableColums();
        setPagination(0);
    }

    private void setPagination(int pageIndex) {
        System.out.println("pageIndex :" + pageIndex);
        //주의) 0 base
        pagination.setCurrentPageIndex(pageIndex);

        //createPage 메소드를 사용하여 페이지 네이션을 생성
        pagination.setPageFactory(this::createPage);
    }

    private Node createPage(int pagenumber) {
        int totalCount = dao.getTotalCount();
        Paging pageInfo = new Paging(String.valueOf(pagenumber+1),"10",totalCount,null,null,null);

        //페이지 정보를 디스플레이에 출력
        pageInfo.displayInformation();
        pagination.setPageCount(pageInfo.getTotalPage());
        fillTableData(pageInfo);
        vBox = new VBox(tableView);


        return vBox;
    }

    ObservableList<Person> dataList = null;

    private void fillTableData(Paging pageInfo) {

        //TableView에 데이터를 채워 줍니다
        //-1을 하는 이유는 대부분의 경우 페이징 처리를 할 때 데이터베이스의 행 번호가 0부터 시작하기 때문입니다.
        List<Person> personList = dao.getAllData(pageInfo.getBeginRow()-1,pageInfo.getEndRow());
        dataList = FXCollections.observableArrayList(personList);
        tableView.setItems(dataList);
        pageStatus.setText(pageInfo.getPagingStatus());
    }

    private void setTableColums() {

        //테이블 내 컬럼 작업
        //tableView 객체가 Person 타입의 정보를 가지고 있으므로
        //다음 배열 요소의 값은 Person 클래스의 변수 이름과 동일해야 합니다
        String[] filed = {"num","firstname","lastname"};
        TableColumn tcol = null;
        //for문을 돌려서해당칼럼에 적절하게 들어가게 설정
        for(int i = 0; i< filed.length; i++){
            tcol=tableView.getColumns().get(i);
            tcol.setCellValueFactory(new PropertyValueFactory<>(filed[i]));
            tcol.setStyle("-fx-alignment:center;");
        }
    }
}
