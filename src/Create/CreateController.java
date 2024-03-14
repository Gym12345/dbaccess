package Create;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Main.MainController;
import Main.connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateController{
   

	
	@FXML public  Button testbtn;
	@FXML public VBox columnVbox;
	@FXML public VBox mainVbox;
	@FXML public TextField newTextField;
	@FXML public ChoiceBox newChoiceBox;
	@FXML public CheckBox notNullCheckBox;
	@FXML public CheckBox primaryKeyCheckBox;
	@FXML public CheckBox UNIQUECheckBox;	
	@FXML public TextField generatedQuery;
	@FXML public TextField tableName;
	
	AnchorPane newAnchorPane;
	
	public String tableNameValue;
	String genQuery="none";
	
	Parent root;
	
	MainController ctrl;
	public void setCtrl(MainController ctrl) {
		   this.ctrl=ctrl;
	   }
	
	AnchorPane viewArea;
	public void getView(AnchorPane viewArea) {
		this.viewArea=viewArea;
	}
	
	
	int i =0;
	public void setRoot(Parent root) {
		this.root=root;

		AnchorPane newAnchorPane = createAnchorPane();
		 columnVbox.getChildren().add(newAnchorPane);
		 i+=1;
		 
	}
	Stage stage;
	
	
	public void columnCreate(){
		if(i<=9) {
		 newAnchorPane = createAnchorPane();
		 newAnchorPane.setId("newAnchorPane"+i);
		 columnVbox.getChildren().add(newAnchorPane);
		 i+=1;
		 System.out.println(i);
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("최대 10개까지 생성 가능합니다.");
		}
	}
	public void columnDelete() {
		System.out.println(columnVbox.getChildren().size());
        if (columnVbox.getChildren().size() > 1) {
        	
        	
        	columnVbox.getChildren().remove(columnVbox.getChildren().size() - 1);
        } else {
            System.out.println("Cannot delete the last remaining AnchorPane.");
        }
	}
	
	private AnchorPane createAnchorPane() {
        TextField newTextField = new TextField();
        newTextField.setLayoutX(14.0);
        newTextField.setLayoutY(11.0);
        newTextField.setPrefHeight(22.0);
        newTextField.setPrefWidth(111.0);
        
       newTextField.setId("newTextField"+i);

        ChoiceBox newChoiceBox = new ChoiceBox();
        newChoiceBox.setLayoutX(156.0);
        newChoiceBox.setLayoutY(11.0);
        newChoiceBox.setPrefHeight(22.0);
        newChoiceBox.setPrefWidth(100.0);
        newChoiceBox.getItems().addAll("VARCHAR2(20)","NUMBER","DATE");
        newChoiceBox.setId("newChoiceBox"+i);

        CheckBox notNullCheckBox = new CheckBox("not null");
        notNullCheckBox.setLayoutY(40.0);
        notNullCheckBox.setId("notNullCheckBox"+i);
        CheckBox primaryKeyCheckBox = new CheckBox("PRIMARY KEY");
        primaryKeyCheckBox.setLayoutX(70.0);
        primaryKeyCheckBox.setLayoutY(40.0);
        primaryKeyCheckBox.setId("primaryKeyCheckBox"+i);
      
        CheckBox UNIQUECheckBox = new CheckBox("UNIQUE");
        UNIQUECheckBox.setLayoutX(170.0);
        UNIQUECheckBox.setLayoutY(40.0);
        UNIQUECheckBox.setId("UNIQUECheckBox"+i);        
        return new AnchorPane(newTextField, newChoiceBox, notNullCheckBox, primaryKeyCheckBox, UNIQUECheckBox);
    }
	
	public void delete() {}
	public void createTable() throws SQLException {
		 
	     
	       System.out.println("Final SQL Query: " + genQuery);
	       connection con=new connection();
	       Connection conn=con.getConnection();
	       PreparedStatement ps;
	       ResultSet rs;
	       
	       Alert alert = new Alert(AlertType.INFORMATION);
			
	       try {
	         
	       ps=conn.prepareStatement(genQuery);
	       ps.execute();
	       
	       alert.setContentText("테이블이 생성되었습니다.");
	       ctrl.createCounter();
			alert.showAndWait();
		    viewArea.getChildren().clear();
		    ctrl.search();
	       
	       }catch (Exception e) {
	    	 alert.setContentText("query error");
             alert.showAndWait();
	         e.printStackTrace();
	      }finally {
	    	  genQuery="none";
	    	  generatedQuery.setText(genQuery);
	      }
		
	       
	       						
	   }
	
	
	public void cancelCreate() {
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
	}
	public void generateQuery() {
		 StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE  (");
		 String tableNameValue = tableName.getText();
		 System.out.println("tableNameValue"+tableNameValue);
	       for (int i = 0; i < columnVbox.getChildren().size(); i++) {
	           AnchorPane rootA = (AnchorPane) columnVbox.getChildren().get(i);
	           
	           
		       
		       
	           TextField textField = (TextField) rootA.lookup("#newTextField" + i);
	           String columnName = textField.getText();
	           
	           ChoiceBox newChoiceBox = (ChoiceBox) rootA.lookup("#newChoiceBox" + i);
	           String dataType = (String) newChoiceBox.getValue();

	           CheckBox primaryKeyCheckBox = (CheckBox) rootA.lookup("#primaryKeyCheckBox" + i);
	           CheckBox UNIQUECheckBox = (CheckBox) rootA.lookup("#UNIQUECheckBox" + i);
	           CheckBox notNullCheckBox = (CheckBox) rootA.lookup("#notNullCheckBox" + i);

	          
	           sqlBuilder.append("\"").append(columnName).append("\"").append(" ").append(dataType);

	           if (notNullCheckBox.isSelected()) {
	               sqlBuilder.append(" NOT NULL");
	           }

	           if (primaryKeyCheckBox.isSelected()) {
	               sqlBuilder.append(" PRIMARY KEY");
	           }

	           if (UNIQUECheckBox.isSelected()) {
	               sqlBuilder.append(" UNIQUE");
	           }

	           sqlBuilder.append(",");
	       }

	      
	       sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
	       sqlBuilder.append(")");
	       sqlBuilder.insert(14,tableNameValue);
	       String finalSql = sqlBuilder.toString();
	     
	      
	       
	     genQuery=finalSql;
		 System.out.println(genQuery);
		 generatedQuery.setText(genQuery);
		 
	}
	
	
	

	



	}