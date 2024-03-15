package Rename;


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
		
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("rename.fxml"));
		root = loader.load();
		RenameController ctrl = loader.getController();
		ctrl.setCtrl(this.ctrl);
		ctrl.getView(viewArea);
		
		ctrl.setRoot(root);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
       
	}
}