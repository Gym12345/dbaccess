package Alter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Main.MainController;
import Main.connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class AlterController {
	Parent root;
	String tableName=null;
	@FXML public AnchorPane viewAnchor;
	String columnName = null;
    String dataType = null;
    String sql_null=null;
    connection con=new connection();
	Connection cons =con.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	public void setRoot(Parent root){
		this.root=root;
	}
	public void getTable(String A) {
		tableName=A;
	}
	MainController ctrl;
	public void setCtrl(MainController ctrl) {
		   this.ctrl=ctrl;
	   }
	
	AnchorPane viewArea;
	public void getView(AnchorPane viewArea) {
		this.viewArea=viewArea;
	}
	
	public void viewTable() {
		
		AnchorPane anchorPane;
		
		Label tableNameLabel;
			ArrayList<String> calumn= new ArrayList();
			System.out.println(tableName);
			
			try {
			DatabaseMetaData DM =cons.getMetaData();
			rs = DM.getColumns(null, null, tableName, null);
			while (rs.next()) {
                columnName = rs.getString("COLUMN_NAME");
                dataType = rs.getString("TYPE_NAME");
                int nullable = rs.getInt("NULLABLE");
                
                if(nullable == 0) {
                	sql_null="Not null";
                }else  {
                	sql_null="null";
                }

                System.out.println("Column Name: " + columnName);
                System.out.println("Data Type: " + dataType);
                System.out.println("Not Null: " + (nullable == 0 ? "YES" : "NO"));
                System.out.println();
                StringBuilder DataBuilder = new StringBuilder();
                DataBuilder.append(columnName);
                DataBuilder.append("\t\t");
                DataBuilder.append(dataType);
                DataBuilder.append("\t\t");
                DataBuilder.append(sql_null);
                String finalSql = DataBuilder.toString();
                calumn.add(finalSql);
//                calumn.add(columnName+" "+dataType+""+nullable);
            }

			
			anchorPane = new AnchorPane();
	        
	        ObservableList<String> observableList = FXCollections.observableArrayList(calumn);
	        
	        ListView columnList = new ListView<>(observableList);
	        tableNameLabel = new Label();
	        
	        anchorPane.setLayoutX(180.0);
	        anchorPane.setLayoutY(57.0);
	        anchorPane.setPrefHeight(200.0);
	        anchorPane.setPrefWidth(100*2.0);

	        columnList.setLayoutY(54.0);
	        columnList.setPrefHeight(146.0);
	        columnList.setPrefWidth(100*2.0);
	       

	        tableNameLabel.setLayoutX(30.0);
	        tableNameLabel.setLayoutY(8.0);
	        tableNameLabel.setPrefHeight(36.0);
	        tableNameLabel.setPrefWidth(40*2.0);
	        tableNameLabel.setStyle("-fx-font-size: 9;");
	        tableNameLabel.setText(tableName);
	        

	       
	        anchorPane.getChildren().addAll(columnList, tableNameLabel);
	        anchorPane.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
	        viewAnchor.getChildren().add(anchorPane);
	        System.out.println("테이블 생성 완료");
			}catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	public void	ColumnAdd() {
		AddMain add= new AddMain();
		add.viewFx(tableName);
		add.getCtrl(ctrl);
	}
	public void ColumnDrop() {
		StringBuilder sqlBuilder = new StringBuilder("ALTER TABLE ");
		
	}
	public void ColumnModify() {
		StringBuilder sqlBuilder = new StringBuilder("ALTER TABLE ");
	}
}