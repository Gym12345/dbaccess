package Sequence;

import java.net.URL;

import Main.MainController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SequenceMain {
	Parent root;
	
	public void viewFx() {
	Stage stage = new Stage();
	try {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("sequence.fxml"));
	root = loader.load();
	SequenceController ctrl = loader.getController();
	ctrl.setRoot(root);
	}catch (Exception e) {
		e.printStackTrace();
	}
	Scene scene = new Scene(root);
	
	stage.setScene(scene);
	stage.show();
	
	
		}

	
	
	

	
}
