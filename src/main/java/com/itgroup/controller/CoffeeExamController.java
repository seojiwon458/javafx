package com.itgroup.controller;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;
import com.itgroup.utility.Paging;
import com.itgroup.utility.Utility;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CoffeeExamController implements Initializable {

    private ProductDao dao = null;
    @FXML private ImageView imageView;
    //필드 검색을 위한 mode변수
    private String mode = null;
    //테이블 뷰에 목록을 채워줍니다
    ObservableList<Product> dataList = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dao = new ProductDao();
        setTableColums();

        //최초 시작시 1페이지를 눌러주세요 Zerobase이기 때문에 0이 1로 된다
        setPagination(0);

        // 테이블 뷰의 1행을 클릭하면, 우측에 이미지를 보여 줍니다.
        ChangeListener<Product> tableListener = new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product oldValue, Product newValue) {
                if(newValue != null){
                    System.out.println("상품 정보");
                    System.out.println(newValue);

                    String imageFile = ""; // 해당 이미지의 fullPath + 이미지 이름
                    if(newValue.getImage01() != null){
                        imageFile = Utility.IMAGE_PATH + newValue.getImage01().trim() ;
                    }else{
                        imageFile = Utility.IMAGE_PATH + "noimage.jpg" ;
                    }

                    Image someImage = null ; // 이미지 객체
                    if(getClass().getResource(imageFile) == null){
                        imageView.setImage(null);
                    }else{
                        someImage = new Image(getClass().getResource(imageFile).toString());
                        System.out.println(imageView == null);
                        imageView.setImage(someImage);
                    }
                }
            }
        } ;

        productTable.getSelectionModel().selectedItemProperty().addListener(tableListener);
        setContextmenu();

    }
    private void setContextmenu(){
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItem01 = new MenuItem("단가 수직 막대");//승급관계
        MenuItem menuItem02 = new MenuItem("단가 파이 막대");//승급관계
        MenuItem menuItem03 = new MenuItem("단가/재고 막대");//승급관계

        //자바에서 ...은  가변 매개 변수입니다.
        //즉, 매개 변수를 무제한 개수로 넣을수 있는 데, addAll() 메소드가 매개변수 형태로 입력이 가능
        contextMenu.getItems().addAll(menuItem01,menuItem02,menuItem03);
        productTable.setContextMenu(contextMenu);

        //자바로 이벤트설정이 가능
        menuItem01.setOnAction((event) -> {
            //예외 처리 방지로 try catch 구문 사용
            try {
                makeBarChart();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });

        menuItem02.setOnAction((event) -> {
            //예외 처리 방지로 try catch 구문 사용
            try {
                makepieChart();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });

        menuItem03.setOnAction((event) -> {
            //예외 처리 방지로 try catch 구문 사용
            try {
                makeBarChartAll();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }

    private void makeBarChartAll() throws Exception {
        System.out.println("단가/재고 막대 그래프");
        FXMLLoader fxmlLoader = this.getFxmlLoader("BarChart.fxml");
        Parent parent = fxmlLoader.load();
        //주의)해당 컨트롤러는 반드시 load()메소드 이후에 호출해야 합니다
        BarChartController controller = fxmlLoader.getController(); //BarChartController에 상속받는다
        //해당 담당자 컨트롤러에게 그리고자 하는 정보를 넘겨줍니다.
        controller.makeChartAll(productTable.getItems());

        //여기서 컨트롤러 호출하기
        this.showModal(parent);
    }

    private void makepieChart() throws Exception {
        System.out.println("단가 파이 그래프");
        FXMLLoader fxmlLoader = this.getFxmlLoader("PieChart.fxml");
        Parent parent = fxmlLoader.load();
        //주의)해당 컨트롤러는 반드시 load()메소드 이후에 호출해야 합니다
        PieChartController controller = fxmlLoader.getController(); //BarChartController에 상속받는다
        //해당 담당자 컨트롤러에게 그리고자 하는 정보를 넘겨줍니다.
        controller.makePieChart(productTable.getItems());
        //여기서 컨트롤러 호출하기
        this.showModal(parent);
    }

    private void makeBarChart() throws Exception{
        System.out.println("단가 수직 막대 그래프 그리기");
        FXMLLoader fxmlLoader = this.getFxmlLoader("BarChart.fxml");
        Parent parent = fxmlLoader.load();
        //주의)해당 컨트롤러는 반드시 load()메소드 이후에 호출해야 합니다
        BarChartController controller = fxmlLoader.getController(); //BarChartController에 상속받는다
        //해당 담당자 컨트롤러에게 그리고자 하는 정보를 넘겨줍니다.
        controller.makeChart(productTable.getItems());
        //여기서 컨트롤러 호출하기
        this.showModal(parent);
    }

    private void showModal(Parent parent) {
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private FXMLLoader getFxmlLoader(String fxmlName) throws IOException {
        //입력된 fxml파일 이름을 이용하여, Fxml의 최상위 컨테이너를 반환해준다.
        Parent parent = null;

        String filelName = Utility.FXML_PATH + fxmlName;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filelName));

        return fxmlLoader;
    }


    //데이터를 페이지로 나누어 표시하고, 페이지 간의 이동을 가능하게 하는 컨트롤
    @FXML private Pagination pagination;
    private void setPagination(int pagiIndex) {
        //페이징 관련 설정을 합니다
        pagination.setCurrentPageIndex(pagiIndex);
        pagination.setPageFactory(this::createPage);

        //화면 갱신시 이미지뷰 정보도 없애주기
        imageView.setImage(null);//디폴트 이미지

    }

    private Node createPage(Integer pageIndex) {
        //각 페이지의 pagination을 동적으로 생성해주는 공장(Factory)역할을 합니다.
        //mode변수는 필드 검색시 사용하는변수
        //String mode = null;
        int totalCount = 0;  //null이면 전체 모드로 보기

        totalCount = dao.getTotalCount(this.mode);
        //1페이지에 10개씩목록 생성하고
        Paging pageInfo = new Paging(String.valueOf(pageIndex+1),"10",totalCount,null,this.mode,null);

        //10개목록씩 total페이지로 출력
        pagination.setPageCount(pageInfo.getTotalPage());
        //페이지를 채우는 구문
        fillTableData(pageInfo);
        VBox vBox = new VBox(productTable);

        return vBox;
    }

    @FXML private Label pageStatus;

    private void fillTableData(Paging pageInfo) {

        List<Product> productList = dao.getPaginationData(pageInfo); /*dao.selectByCategory(null);*/
        dataList = FXCollections.observableArrayList(productList);
        //set구문은 table,List,combo에 전부 사용 되어진다
        productTable.setItems(dataList);
        pageStatus.setText(pageInfo.getPagingStatus());
    }


    //테이블 목록을 보여주는 뷰
    @FXML private TableView<Product> productTable; //
    private void setTableColums() {
        //product 빈 클래스에서 보여주고자 하는 컬럼 정보들을 연결합니다.
        //열거 되지 않는 목록들은 항목의 상세 보기 페이지에서 구현 할 수 있습니다.
        String[] fields = {"pnum","name","company","category","inputdate"};
        //한글로 치환시킨 구문
        String[] colNames = {"상품번호","이름","제조회사","카테고리","입고일자"};

        TableColumn tableColumn = null;
        for(int i = 0; i<fields.length; i++){
            tableColumn = productTable.getColumns().get(i);
            //컬럼을 한글 이름으로 변경
            tableColumn.setText(colNames[i]);
            //Product 빈 클래스의 인스턴스 변수 이름을 셋팅하면 데이터가 자동으로 바인딩 된다.
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fields[i]));

            tableColumn.setStyle("-fx-alignment:center;");// 모든 셀 데이터를 가운데 정렬
        }
    }

    public void onInsert(ActionEvent event) throws IOException {
        // 상품을 등록합니다.
        // fxml 파일 로딩
        String fxmlFile = Utility.FXML_PATH + "ProductInsert.fxml";

        // import java.net.URL ;
        URL url = getClass().getResource(fxmlFile);

        FXMLLoader fxmlLoader = new FXMLLoader(url);

        Parent container = fxmlLoader.load(); // fxml의 최상위 컨테이너 객체

        Scene scene = new Scene(container); // 씬에 담기
        Stage stage = new Stage() ;

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene); // 씬을 무대에 담기
        stage.setResizable(false);
        stage.setTitle("상품 등록하기");
        stage.showAndWait(); // 창 띄우고 대기

        setPagination(0); // 화면 갱신

    }

    public void onUpdate(ActionEvent event) throws IOException {
        // 선택된 항목(Product Bean)에 대한 수정 작업을 합니다.
        int idx = productTable.getSelectionModel().getSelectedIndex();

        if(idx >= 0){
            System.out.println("선택된 색인 번호 : " + idx);

            String fxmlFile = Utility.FXML_PATH + "ProductUpdate.fxml";
            URL url = getClass().getResource(fxmlFile);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent container = fxmlLoader.load();

            // update 기능에서 추가된 내용 시작
            // 현재 내가 선택한 상품(Product) 정보와 색인 정보(idx)를 해당 컨트롤러에게 메소드를 통하여 전달해 줍니다.
            Product bean = productTable.getSelectionModel().getSelectedItem() ;

            ProductUpdateController controller = fxmlLoader.getController();

            controller.setBean(bean);

            // update 기능에서 추가된 내용 끝

            Scene scene = new Scene(container);
            Stage stage = new Stage() ;

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("상품 수정 하기");
            stage.showAndWait();
            setPagination(0);

        }else{
            String[] message = new String[]{"상품 선택 확인", "상품 미선택", "수정하고자 하는 상품을 선택해 주세요."};
            Utility.showAlert(Alert.AlertType.ERROR, message);
        }
    }

    public void onDelete(ActionEvent event) {
        //특정항목에 대한 요소를 삭제
        int idx = productTable.getSelectionModel().getSelectedIndex();
        if(idx>=0){

            String[] message = new String[]{"삭제 확인 메세지", "삭제 항목 선택 대화 상자 ","이 항목을 정말로 삭제하시겠습니까?"};
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("삭제 확인 메세지");
            alert.setHeaderText("삭제 항목 대화 상자");
            alert.setContentText("이 항목을 정말로 삭제하시겠습니까? ");
            //Utility.showAlert(Alert.AlertType.CONFIRMATION,message);
            Optional<ButtonType> response = alert.showAndWait();
            //response 응답을 위한 객체

            if(response.get() == ButtonType.OK){
                //선택된 항목에서
                Product been = productTable.getSelectionModel().getSelectedItem();
                int pnum = been.getPnum();
                int cnt = -1;
                cnt = dao.deleteData(pnum);
                if(cnt !=-1){
                    System.out.println("삭제 성공");
                }else{
                    System.out.println("삭제 취소");
                }
            }else{
                System.out.println("삭제를 취소하였습니다");
            }

        }else{

            String[] message = new String[]{"삭제할 목록 확인", "삭제할 대상 미선택 ","삭제할 행을 선택해주세요"};
            //Utility.showAlert(Alert.AlertType.WARNING,message);
            Utility.showAlert(Alert.AlertType.CONFIRMATION,message);
            //Alert.AlertType은 아이콘을 나타낸다.
        }

    }

    public void onSaveFile(ActionEvent event) {
        //현재 페이지에 보이는 테이블 뷰 목록 형식의 파일을 저장합니다.
        FileChooser chooser = new FileChooser();
        Button mybtn = (Button) event.getSource();
        Window window = mybtn.getScene().getWindow();
        //showSaveDialog 파일로 저장하는 구문
        File saveedFile = chooser.showSaveDialog(window);

        if(saveedFile !=null){
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                //append는 영수증 처럼 뒤에 추가하는 구문
                //null 형식은 계속 덮어씌어지는 기본 값
                fw = new FileWriter(saveedFile);
                bw = new BufferedWriter(fw); //승급

                for (Product bean : dataList) {
                    bw.write(bean.toString());
                    bw.newLine();
                }
                System.out.println("파일 저장 하였습니다");

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if(bw!=null){bw.close();}
                    if(fw!=null){fw.close();}
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

        }else{
            System.out.println("파일을 저장하지 않았습니다");
        }
    }

    public void onClosing(ActionEvent event) {

        System.out.println("프로그랩을 종료합니다");
        //프로그램을 닫는 구문
        Platform.exit();
    }

    @FXML private ComboBox<String>fieldSearch;
    public void choiceSelect(ActionEvent event) {

        //특정 카테고리에 대한 필터링 합니다
        String category = fieldSearch.getSelectionModel().getSelectedItem();
        System.out.println("검색 카테고리 :  + ["+ category+"]");
        //인스턴스 변수를 강조하기위해 this사용
        this.mode = Utility.getCategoryName(category,"value");
        System.out.println("필드 검색 모드 :  + ["+this.mode+"]");

        //화면 갱신시 이미지뷰 정보도 없애주기
        setPagination(0);

    }
}
