package Main;


import Create.createMain;
import Drop.dropMain;
import Rename.renameMain;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainClass extends Application{
	MainController ctrl;
	@Override
	public void start(Stage arg0) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("adminLogin.fxml"));
		Parent root = loader.load();
		ctrl = loader.getController();
		ctrl.getMainController(ctrl);
		createMain cm =new createMain();
		cm.getController(ctrl);
		ctrl.setCreateMain(cm);
		
		dropMain dm=new dropMain();
		dm.getController(ctrl);
		ctrl.setDropMain(dm);
		
		renameMain rm=new renameMain();
		rm.getController(ctrl);
		ctrl.setDropMain(dm);
		
		ctrl.setRoot(root);
		
		Scene scene = new Scene(root);
		arg0.setTitle("Sql테이블생성");
		arg0.setScene(scene);
		arg0.show();
		
	}
	
	
	public MainController getCtrl() {
		return ctrl;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	

}
