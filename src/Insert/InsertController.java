package Insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Login.connection;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InsertController {
	Parent root;
    private MainController mainController; // Added variable to hold MainController instance

    @FXML Button button;
    @FXML TextField InsertQuery;
    @FXML VBox textFieldContainer;
    String sbQuery;
	public void setRoot(Parent root) {
		this.root=root;
		
		
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
		
	}
	
//	ArrayList<String> valuesOfColList=new ArrayList<String>();
	public void dynamicTextfield() {
		 int colCount = mainController.getcolCountForEach();
	        textFieldContainer.getChildren().clear(); // Clear existing text fields
	      
	        ArrayList<String> colList=new ArrayList<String>();
	        colList= mainController.getTableInfos().get(mainController.getTableName());
	        
	        
	        
	        System.out.println();
	        for (int i = 0; i < colCount; i++) {
	        		
	        		TextField textField = new TextField();
	        	    Label label = new Label();
	        	    label.setText(colList.get(i) + ":"); // Modified label text
	        	    
	        	    
	        	    HBox hbox = new HBox(label, textField); // Wrap label and text field in HBox
	        	    textFieldContainer.getChildren().add(hbox); // Add HBox to container
	        }
	     System.out.println(colCount);
	}
	
	public void generateInsertQuery() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("INSERT INTO ").append(mainController.getTableName()).append(" (");

	    // Append column names
	    ArrayList<String> colList = mainController.getTableInfos().get(mainController.getTableName());
	    for (int i = 0; i < colList.size(); i++) {
	    	sb.append("\"").append(colList.get(i)).append("\"");
	        if (i < colList.size() - 1) {
	            sb.append(", ");
	        }
	    }

	    sb.append(") VALUES (");

	    // Append values from text fields
	    for (int i = 0; i < textFieldContainer.getChildren().size(); i++) {
	        HBox hbox = (HBox) textFieldContainer.getChildren().get(i);
	        TextField textField = (TextField) hbox.getChildren().get(1); // Assuming text field is always at index 1   (label, textField) 1의위치
	        
	        sb.append("'").append(textField.getText()).append("'");
	        
	        if (i < textFieldContainer.getChildren().size() - 1) {
	            sb.append(", ");
	        }
	    }

	    sb.append(")");
	    
	    
	    
	    sbQuery = sb.toString();
	    InsertQuery.setText(sbQuery);
	}

	public void commitInsertQuery(){
		 	System.out.println("Final SQL Query: " + sbQuery);
	       Connection conn=connection.con;
	       PreparedStatement ps;
	       ResultSet rs;
	       
	       Alert alert = new Alert(AlertType.INFORMATION);
			
	       try {
	         
	       ps=conn.prepareStatement(sbQuery);
	       ps.execute();
	       
	       alert.setContentText("테이블에 반영되었습니다.");
	       	
	       
	       }catch (Exception e) {
	    	 alert.setContentText("query error");
           alert.showAndWait();
	         e.printStackTrace();
	      }finally {
	    	  sbQuery="none";
	    	  InsertQuery.setText(sbQuery);
	    	  for (int i = 0; i < textFieldContainer.getChildren().size(); i++) {
	    		  HBox hbox = (HBox) textFieldContainer.getChildren().get(i);
	              TextField textField = (TextField) hbox.getChildren().get(1);
	              textField.setText("none");
	  	    }
	      }
		
	}
}
