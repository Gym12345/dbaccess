package Rename;


import Drop.DropController;
import Main.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class renameMain {
	Parent root;
	
	MainController ctrl;
	public void getController(MainController ctrl) {
		this.ctrl=ctrl;
	}
	
	AnchorPane viewArea;
	public void getView(AnchorPane viewArea) {
		this.viewArea=viewArea;
	}
	
	public void viewFx() {
		Stage stage = new Stage();
		RenameController ctrl = null;
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("rename.fxml"));
		root = loader.load();
		ctrl = loader.getController();
		//ctrl.setRoot(root);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
       
	}
}