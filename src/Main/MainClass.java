package Main;


import java.sql.Connection;

import Create.createMain;
import Drop.dropMain;
import Rename.renameMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass {
	MainController ctrl;
	Connection con;
	public void start(Stage stage)  {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		Parent root = loader.load();
		ctrl = loader.getController();
		ctrl.search();
		ctrl.setcon(con);
		ctrl.getMainController(ctrl);
		createMain cm =new createMain();
		cm.getController(ctrl);
		ctrl.setCreateMain(cm);
		
		
		
		dropMain dm=new dropMain();
		dm.getController(ctrl);
		ctrl.setDropMain(dm);
		
		
		renameMain rm=new renameMain();
		rm.getController(ctrl);
		ctrl.setRenameMain(rm);
		
		ctrl.setRoot(root);
		
		Scene scene = new Scene(root);
		stage.setTitle("Sql테이블생성");
		stage.setScene(scene);
		stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void setcon(Connection con) {
		this.con=con;
	}
	
	
	public MainController getCtrl() {
		return ctrl;
	}
	

	

}
