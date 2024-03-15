package Create;

import java.net.URL;

import Main.MainController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class createMain {
	Parent root;
	
	
	AnchorPane viewArea;
	public void getView(AnchorPane viewArea) {
		this.viewArea=viewArea;
	}
	
	MainController ctrl;
	public void getController(MainController ctrl) {
		this.ctrl=ctrl;
	}
	
	public void viewFx() {
		Stage stage = new Stage();
		
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("create.fxml"));
		root = loader.load();
		CreateController ctrl = loader.getController();
		
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
