package Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Alter.AlterMain;
import Create.createMain;
import Drop.dropMain;
import Insert.insertMain;
import Login.connection;
import Rename.renameMain;
import Sequence.SequenceMain;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;



public class MainController implements Initializable {
	private Parent root;
	Connection conn=connection.con ;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		search();
	}
	
	public Connection getcon() {
		return conn;
	}
	public void setcon(Connection con) {
		this.conn=con;
	}
	PreparedStatement ps;
	ResultSet rs;
	@FXML  AnchorPane viewArea;
	createMain cm;
	dropMain dm;
	renameMain rm;
	public static MainController ctrl;
	
	
	private int pageNumTable; //
	private String tableName;//
	
	private int colCountForEach;//
	
	int tableColumnCnt=0;//
	public int getPageNumTable() {//
		
		return pageNumTable;
	}
	public ArrayList<Integer> getColCount() {//
		
		return colCount;
	}
	
	public int init_TableColumnCnt() {//
		tableColumnCnt=0;
		return tableColumnCnt;
	}
public int getcolCountForEach() {//
		
		return colCountForEach;
	}
	
	//HashMap<String,ArrayList> column = new HashMap<>(); //global that contains TableInfos
	public HashMap<String,ArrayList> getTableInfos(){
		
		HashMap<String,ArrayList> tableInfo=new HashMap<String, ArrayList>();
		tableInfo=column;
		return tableInfo;
				
	}
	
	public String getTableName() {
		
		return tableName;
	}
	
	
	
	public void getMainController(MainController ctrl) {
		this.ctrl= ctrl;
	}
	
	
	public void setRoot(Parent root){
		this.root=root;
	}
	
	
	public void setCreateMain(createMain cm) {
		this.cm=cm;
		
	}
	public void setDropMain(dropMain dm) {
		this.dm=dm;
		
	}
	public void setRenameMain(renameMain rm) {
		this.rm=rm;
		
	}
	public void create() {
		cm.getView(viewArea);
		cm.viewFx();
	}
	public void drop() {
		dm.getView(viewArea);
		dm.viewFx();
	}
	public void rename() {
		rm.getView(viewArea);
		rm.viewFx();
	}
	
	public void truncate() {}
	
	public void sequence() {
		SequenceMain sm=new SequenceMain();
		sm.viewFx();
		
	}
	
	
	public void insert() {
		insertMain im=new insertMain(); // InsertController 를 쓰면 setMainController 를 두번 안써도 되지만 현재 코드 구조엔 맞지않음
	    im.setMainController(this); // 현재이 파일의 MainController instance 를참조

		im.viewFx();
		
	}
	
	private AnchorPane anchorPane;
    private ListView<String> OlcolumnList;
    private Label tableNameLabel;
    
    
    
    
   

//    HashMap<String,ArrayList> column= new HashMap<>(); 
//    
//    ArrayList<String> tableList=new ArrayList();
    
    
    HashMap<String,ArrayList> column= null; //= new HashMap<>(); //global 전역으로 설정해야 위에 getTableInfos() 함수에서 쓸수잇으니까
    
    ArrayList<String> tableList= null; //new ArrayList(); //global, contains table name only //전역으로 설정해야 viewTable 내에서 쓸수잇으니까
    
    ArrayList<Integer> colCount=new ArrayList();
    public void search() {
    	conn=connection.con;
    	column = new HashMap<>();
    	tableList= new ArrayList();   // 이렇게 search안에서 new 해줘야 hashmap 과 arrayList 를 한번 비워줄수있으니까
		try {
			
			String url ="select * from tab where TNAME NOT LIKE 'BIN$%'";
			ps= conn.prepareStatement(url);
			rs=ps.executeQuery();
	 
		while(rs.next()){
			tableList.add(rs.getString("TNAME"));
			}
		
		for(int i=0;i<tableList.size();i++) {
			ArrayList<String> columnList= new ArrayList(); // column list for each table
			
			
			
			String sql  ="SELECT * FROM COLS WHERE TABLE_NAME ='"+tableList.get(i)+"'";
					try {
						ps= conn.prepareStatement(sql);
						rs=ps.executeQuery();
						
						while(rs.next()){
							columnList.add(rs.getString("COLUMN_NAME"));
							tableColumnCnt=tableColumnCnt+1;//
							
							}
						
						
						column.put(tableList.get(i), columnList);
						viewTable(tableList.get(i),columnList,i);
						
						
					    System.out.println(i);
					   
						}
		
					
		catch (Exception e) {
			e.printStackTrace();
			}
					
					 colCount.add(tableColumnCnt);//	
					 tableColumnCnt=0;//
			}//for 문 end
	
	
	
	}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
    }
    
	public void viewTable(String TableName,ArrayList<String> columnList,int i) {
		 double currentY = 50;
        anchorPane = new AnchorPane();
        Button Alterbtn= new Button();
        Button Insertbtn= new Button();
        ObservableList<String> observableList = FXCollections.observableArrayList(columnList);
        
        OlcolumnList = new ListView<>(observableList);
        tableNameLabel = new Label();
        
        
        int ab = i % 5;
        double offsetX = 210.0 * ab;
        double offsetY = 240.0 * (i / 5);

        anchorPane.setLayoutX(10 + offsetX);
        anchorPane.setLayoutY(currentY + offsetY);

       
        
        	anchorPane.setLayoutX(10+(210*ab));
            anchorPane.setLayoutY(currentY+(240*(i/5)));
        
        

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(100*2.0);

        OlcolumnList.setLayoutY(54.0);
        OlcolumnList.setPrefHeight(146.0);
        OlcolumnList.setPrefWidth(100*2.0);
       

        tableNameLabel.setLayoutX(30.0);
        tableNameLabel.setLayoutY(8.0);
        tableNameLabel.setPrefHeight(36.0);
        tableNameLabel.setPrefWidth(40*2.0);
        tableNameLabel.setStyle("-fx-font-size: 9;");
        tableNameLabel.setText(TableName);
        
        Alterbtn.setLayoutX(110.0);
        Alterbtn.setLayoutY(8.0);
        Alterbtn.setPrefHeight(36.0);
        Alterbtn.setPrefWidth(40.0);
        Alterbtn.setStyle("-fx-font-size: 9;");
        Alterbtn.setText("Alter");
       
       //
        Insertbtn.setLayoutX(155.0);
        Insertbtn.setLayoutY(8.0);
        Insertbtn.setPrefHeight(36.0);
        Insertbtn.setPrefWidth(40.0);
        Insertbtn.setStyle("-fx-font-size: 9;");
        Insertbtn.setText("Insert");
        Insertbtn.setOnAction(event -> {
        	
        	pageNumTable=i;
        	tableName=tableList.get(i);
        	colCountForEach=colCount.get(i);
        	insert();
        	
        });
        //
        anchorPane.getChildren().addAll(OlcolumnList, tableNameLabel,Alterbtn,Insertbtn);
        anchorPane.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        viewArea.getChildren().add(anchorPane);
        
        Alterbtn.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	AlterMain am= new AlterMain();
	        	am.getView(viewArea);
	        	am.viewFx(TableName);
	        	am.getController(ctrl);
	        }
	    });
        
       
        
        

     
    }
	
	public void clearViewArea() {
		viewArea.getChildren().clear();
		search();
		
	}
	
	public int count=0;
	public void createCounter() {
		count+=1;
	}
	public AnchorPane getViewArea() {
		return viewArea;
	}
	
	
	
	
	
	
	
	
	
	
	
}