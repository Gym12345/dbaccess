package Rename;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Main.connection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RenameController {

    @FXML
    public TextField nowName;

    @FXML
    public TextField reName;
//
//    @FXML
//    private Button renameButton;
//    @FXML
//    private VBox renamebox;
//
//    @FXML
    public void renameButton() {
            String currentTableName = nowName.getText();
            String newTableName = reName.getText();

//             여기에 테이블 이름 변경 로직을 추가하면 됩니다.
//             예를 들어, 변경된 테이블 이름을 콘솔에 출력하거나, 데이터베이스에 적용하는 등의 작업을 수행할 수 있습니다.
            connection con=new connection();
    		Connection conn =con.getConnection();
    		PreparedStatement ps;
    		ResultSet rs;
    		int result=1;
    		StringBuilder sqlBuilder = new StringBuilder("ALTER TABLE ");
    		sqlBuilder.append("\"").append(currentTableName).append("\"").append(" rename to ")
    		.append("\"").append(newTableName).append("\"").append("");
    		String finalsql=sqlBuilder.toString();
    		try {
    		ps=conn.prepareStatement(finalsql);
    	    result=ps.executeUpdate();
    	    if(result==0) {
    	    	Alert alert = new Alert(AlertType.INFORMATION);
    	    	String msg="기존 테이블 이름: " + currentTableName+"\n"+"변경한 테이블 이름: " + newTableName;
    	    	alert.setContentText(msg);
    	    	alert.showAndWait();
    	    }

    		}catch (Exception e) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setContentText("존재하지 않는 테이블입니다.");
    	    	alert.showAndWait();
    			e.printStackTrace();
    		}
           
            
        
    }
}



