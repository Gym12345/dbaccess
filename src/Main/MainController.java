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



public class MainController implements Initializable{
	private Parent root;
	connection con=new connection();
	Connection conn =con.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@FXML  AnchorPane viewArea;
	createMain cm;
	dropMain dm;
	renameMain rm;
	MainController ctrl;
	public void getMainController(MainController ctrl) {
		this.ctrl= ctrl;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		con =new connection();
		search();
		
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
		insertMain im=new insertMain();
		im.viewFx();
		
	}
	
	private AnchorPane anchorPane;
    private ListView<String> OlcolumnList;
    private Label tableNameLabel;
    
    
    
    
    
    public void search() {
		ArrayList<String> tableList= new ArrayList();
		try {
	String url ="select * from tab where TNAME NOT LIKE 'BIN$%'";
	ps= conn.prepareStatement(url);
	rs=ps.executeQuery();
	while(rs.next()){
			tableList.add(rs.getString("TNAME"));
		}
	}catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String,ArrayList> column = new HashMap<>();
		
		for(int i=0;i<tableList.size();i++) {
		ArrayList<String> columnList= new ArrayList();
		System.out.println(tableList.get(i));

		String sql  ="SELECT * FROM COLS WHERE TABLE_NAME ='"+tableList.get(i)+"'";
		try {
		ps= conn.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			columnList.add(rs.getString("COLUMN_NAME"));
		}
		column.put(tableList.get(i), columnList);
		viewTable(tableList.get(i),columnList,i);
		System.out.println(tableList.get(i));
		}catch (Exception e) {
			e.printStackTrace();
		}
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

       
        	System.out.println(ab);System.out.println(i);
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
        
        Alterbtn.setLayoutX(120.0);
        Alterbtn.setLayoutY(8.0);
        Alterbtn.setPrefHeight(36.0);
        Alterbtn.setPrefWidth(40.0);
        Alterbtn.setStyle("-fx-font-size: 9;");
        Alterbtn.setText("Alter");
       
       
        Insertbtn.setLayoutX(155.0);
        Insertbtn.setLayoutY(8.0);
        Insertbtn.setPrefHeight(36.0);
        Insertbtn.setPrefWidth(40.0);
        Insertbtn.setStyle("-fx-font-size: 9;");
        Insertbtn.setText("Insertbtn");
        Insertbtn.setOnAction(event -> {
        	insert();
        });
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