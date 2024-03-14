package Alter;

import Main.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddMain {
	Parent root;
	MainController ctrl;
	public void getCtrl(MainController ctrl) {
		this.ctrl=ctrl;
	}
	public void viewFx(String tableName) {
		Stage stage = new Stage();
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
		root = loader.load();
		AddController ctrl = loader.getController();
		ctrl.getRoot(root);
		ctrl.getCtrl(this.ctrl);
		ctrl.getTableName(tableName);
//		ctrl.setCtrl(this.ctrl);
//		ctrl.getView(viewArea);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
}
}
