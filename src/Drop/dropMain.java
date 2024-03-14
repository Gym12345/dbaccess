package Drop;


import Main.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class dropMain {
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
	DropController ctrl = null;
	try {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("drop_.fxml"));
		
		root = loader.load();
		ctrl=loader.getController();
		ctrl.setCtrl(this.ctrl);
		ctrl.setRoot(root);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	Scene scene = new Scene(root);
	
	ctrl.ac();
	stage.setScene(scene);
	stage.show();

		}


	
}


