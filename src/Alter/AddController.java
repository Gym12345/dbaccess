package Alter;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Login.connection;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddController implements Initializable {
	@FXML public TextField columnName;
	@FXML public ChoiceBox DataChoice;
	@FXML public CheckBox DataNull;
	@FXML public CheckBox DataPk;
	@FXML public CheckBox DataUnique;
	String tableName=null;
	
	 
	    Connection cons=Login.connection.con;
	    PreparedStatement ps;
	    ResultSet rs;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataChoice.getItems().addAll("varchar(50)","varchar2(50)","NUMBER","date");
	}
	
	MainController ctrl=MainController.ctrl;
	AlterController ctrl1=AlterController.ctrl;
	
	Parent root;
	public void getRoot(Parent root) {
		this.root= root;
	}
	public void getTableName(String tableName) {
		this.tableName=tableName;
	}
	public void CreatAction(){
		StringBuilder sqlBuilder = new StringBuilder("ALTER TABLE ");
		sqlBuilder.append(tableName);
		sqlBuilder.append(" ADD ");
		TextField TextField=(TextField)root.lookup("#columnName");
		String columnName = TextField.getText();
		ChoiceBox newChoiceBox = (ChoiceBox) root.lookup("#DataChoice");
        String dataType = (String) newChoiceBox.getValue();
        sqlBuilder.append("\"").append(columnName).append("\"").append(" ").append(dataType);
        CheckBox NullData = (CheckBox) root.lookup("#DataNull");
        CheckBox PkData = (CheckBox) root.lookup("#DataPk");
        CheckBox UniqueData = (CheckBox) root.lookup("#DataUnique");


        String finalSql = sqlBuilder.toString();
        System.out.println("Final SQL Query: " + finalSql);
	    try {

	    ps=cons.prepareStatement(finalSql);
	    ps.executeUpdate();
	    System.out.println("컬럼생성");
	    ps.clearParameters();
	    
      if (NullData.isSelected()) {
    	  String sql="UPDATE "+tableName+" SET \""+columnName+"\" = 'nodata' WHERE \""+columnName+"\" IS NULL";
    	  ps=cons.prepareStatement(sql);
    	  ps.executeUpdate();
    	  System.out.println("업데이트완료");
    	  StringBuilder ModiBuilder = new StringBuilder("ALTER TABLE ");
  	    	ModiBuilder.append(tableName);
  	    	ModiBuilder.append(" MODIFY ");
  	    	ModiBuilder.append("\"").append(columnName).append("\" ")
  	    .append(dataType).append(" NOT NULL ");
  	    String finalNullSql = ModiBuilder.toString();
  	  	ps=cons.prepareStatement(finalNullSql);
	    ps.executeUpdate();
	    System.out.println("not null생성");
	    ps.clearParameters();
      }
      if (PkData.isSelected()) {
    	  StringBuilder PkBuilder = new StringBuilder("ALTER TABLE ");
    	  PkBuilder.append(tableName);
    	  PkBuilder.append(" ADD CONSTRAINT ").append(" PRIMARY KEY (");
    	  PkBuilder.append("\"").append(columnName).append("\" ").append(")");
	    String finalPkSql = PkBuilder.toString();
	  	ps=cons.prepareStatement(finalPkSql);
	    ps.executeUpdate();
	    System.out.println("Pk 생성");
	    ps.clearParameters();
      }else {
      if (UniqueData.isSelected()) {
    	  StringBuilder UniBuilder = new StringBuilder("ALTER TABLE ");
    	  UniBuilder.append(tableName);
    	  UniBuilder.append(" ADD CONSTRAINT ").append("Unique_").append(columnName).append(" Unique (");
    	  UniBuilder.append("\"").append(columnName).append("\" ").append(")");
	    String finalUniqueSql = UniBuilder.toString();
	    cons=connection.con;
	  	ps=cons.prepareStatement(finalUniqueSql);
	    ps.executeUpdate();
	    System.out.println("Unique생성");
	    ps.clearParameters();
      }
      }
	    Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("컬럼이 생성되었습니다.");
		alert.showAndWait();
		ctrl.clearViewArea();
		ctrl1.clearViewArea();
	    }catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void CancelAction(){
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
	}
	
	
}
