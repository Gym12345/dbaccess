package Sequence;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SequenceController{
   
	Parent root;
	@FXML public VBox mainVbox;
	@FXML private TextField sequenceName;
	@FXML private TextArea  sequenceQuery;

	@FXML private CheckBox incrementByCheckBox;

	@FXML private CheckBox startWithCheckBox;
    
    @FXML private CheckBox minValueCheckBox;
    
    @FXML private CheckBox maxValueCheckBox;
    
    @FXML private CheckBox noCycleCheckBox;
    
    @FXML private CheckBox noCacheCheckBox;
    
    @FXML private CheckBox noOrderCheckBox;
    
    @FXML
    private TextField incrementByNum;

    @FXML
    private TextField startWithNum;

    @FXML
    private TextField minValueNum;

    @FXML
    private TextField maxValueNum;
    
    String genQuery;
	public void setRoot(Parent root) {
		this.root=root;
		incrementByNum.setText("1");
        startWithNum.setText("1");
        minValueNum.setText("1");
        maxValueNum.setText("999999999999999");
		
	}
	public void generateSequenceQuery() {
		StringBuilder sqlBuilder = new StringBuilder("CREATE SEQUENCE ");
		String sequenceValue = sequenceName.getText();
		
		sqlBuilder.append(sequenceValue);
		
	           
			boolean isChecked = incrementByCheckBox.isSelected();
			boolean startWithChecked = startWithCheckBox.isSelected();
	        boolean minValueChecked = minValueCheckBox.isSelected();
	        boolean maxValueChecked = maxValueCheckBox.isSelected();
	        boolean noCycleChecked = noCycleCheckBox.isSelected();
	        boolean noCacheChecked = noCacheCheckBox.isSelected();
	        boolean noOrderChecked = noOrderCheckBox.isSelected();
	          
	        String incrementValue = incrementByNum.getText();
	        String startWithValue = startWithNum.getText();
	        String minValue = minValueNum.getText();
	        String maxValue = maxValueNum.getText();

	           
	          
	           if (isChecked) {
	               sqlBuilder.append("\n INCREMENT BY "+incrementValue);
	               
	           }
	           
	           if (startWithChecked) {
	               sqlBuilder.append("\n START WITH "+startWithValue);
	           }
	           if (minValueChecked) {
	               sqlBuilder.append("\n MINVALUE " +minValue);
	           }
	           if (maxValueChecked) {
	               sqlBuilder.append("\n MAXVALUE "+maxValue);
	           }
	           if (noCycleChecked) {
	               sqlBuilder.append("\n NOCYCLE");
	           }
	           if (noCacheChecked) {
	               sqlBuilder.append("\n NOCACHE");
	           }
	           if (noOrderChecked) {
	               sqlBuilder.append("\n NOORDER");
	           }
	           
	           
	           String finalSql = sqlBuilder.toString();
	           
	 	      
		       
	  	     	genQuery=finalSql;
	  	     	System.out.println(genQuery);
	  	     	sequenceQuery.setText(genQuery);
	       }

	 public void commitSequence(){
		 System.out.println("Final SQL Query for DB: " + genQuery);
	       connection con=new connection();
	       Connection conn=con.getConnection();
	       PreparedStatement ps;
	       ResultSet rs;
	       
	       try {
	    	   
	       ps=conn.prepareStatement(genQuery);
	       ps.execute();
	       
	       }catch (Exception e) {
	         e.printStackTrace();
	      }
		
	 }
	       
	      
	       
	}
	
	

