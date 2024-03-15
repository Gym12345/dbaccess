package Rename;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RenameController {

    @FXML
    public TextField nowName;
	Parent root;

    @FXML
    public TextField reName;
	AnchorPane viewArea;
	MainController ctrl;
	public void setCtrl(MainController ctrl) {
		   this.ctrl=ctrl;
	   }
	
	public void getView(AnchorPane viewArea) {
		this.viewArea=viewArea;
	}
	public void setRoot(Parent root) {
		this.root=root;

		
		 
	}
	
    public void renameButton() {
            String currentTableName = nowName.getText();
            String newTableName = reName.getText();

//             여기에 테이블 이름 변경 로직을 추가하면 됩니다.
//             예를 들어, 변경된 테이블 이름을 콘솔에 출력하거나, 데이터베이스에 적용하는 등의 작업을 수행할 수 있습니다.
    		Connection conn =Login.connection.con;
    		PreparedStatement ps;
    		ResultSet rs;
    		int result=1;
    		StringBuilder sqlBuilder = new StringBuilder("ALTER TABLE ");
    		
    		newTableName=newTableName.toUpperCase();
    		sqlBuilder.append("\"").append(currentTableName).append("\"").append(" rename to ")
    		.append("\"").append(newTableName.toUpperCase()).append("\"").append("");
    		String finalsql=sqlBuilder.toString();
    		
    		try {
    		ps=conn.prepareStatement(finalsql);
    	    ps.executeUpdate();
    	    //finalsql="none";
	    	    Alert alert = new Alert(AlertType.INFORMATION);
		    	String msg="기존 테이블 이름: " + currentTableName+"\n"+"변경한 테이블 이름: " + newTableName;
		    	alert.setContentText(msg);
		    	alert.showAndWait();
		    	viewArea.getChildren().clear();
			    ctrl.search();  //다시 테이블 로드 at 메인
    	    
    		}catch (Exception e) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setContentText("존재하지 않는 테이블입니다.");
    	    	alert.showAndWait();
    			e.printStackTrace();
    		}
           
            
        
    }
    
    
    public void renameExit() {
    	Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
    }
}



