package Insert;

import Sequence.SequenceController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class insertMain {
	
	
Parent root;
	
	public void viewFx() {
	Stage stage = new Stage();
	try {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("Insert.fxml"));
	root = loader.load();
	InsertController ctrl = loader.getController();
	ctrl.setRoot(root);
	}catch (Exception e) {
		e.printStackTrace();
	}
	Scene scene = new Scene(root);
	
	stage.setScene(scene);
	stage.show();
	
	
		}
}
