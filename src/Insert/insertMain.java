package Insert;

import Main.MainClass;
import Main.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class insertMain {
	
	
	Parent root;
	MainController mainController;
	
		
	public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

	public void viewFx() {
	Stage stage = new Stage();
	try {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Insert.fxml"));
		
		root = loader.load();
		InsertController ctrl = loader.getController();
		
       	ctrl.setRoot(root);
        ctrl.setMainController(mainController);
        ctrl.dynamicTextfield();
	}catch (Exception e) {
		e.printStackTrace();
	}
	Scene scene = new Scene(root);
	
	stage.setScene(scene);
	stage.show();
	
	
		}
}
