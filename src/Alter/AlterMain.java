package Alter;

import Create.CreateController;
import Main.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AlterMain {
	AnchorPane viewArea;
	public void getView(AnchorPane viewArea) {
		this.viewArea=viewArea;
	}
	MainController ctrl;
	public void getController(MainController ctrl) {
		this.ctrl=ctrl;
	}
	
	Parent root;
	public void viewFx(String TableName) {
		Stage stage = new Stage();
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("alter.fxml"));
		root = loader.load();
		AlterController ctrl = loader.getController();
//		ctrl.setCtrl(this.ctrl);
//		ctrl.getView(viewArea);
		ctrl.setRoot(root);
		ctrl.getTable(TableName);
		ctrl.setCtrl(this.ctrl);
		ctrl.viewTable();
		}catch (Exception e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
}
}
