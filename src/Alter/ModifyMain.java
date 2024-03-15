package Alter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyMain {
	Parent root;
	AlterController ctrl;
	public void getCtrl(AlterController ctrl) {
		this.ctrl=ctrl;
	}
	public void viewFx(String getcolumnName,String TableName) {
		Stage stage = new Stage();
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("modify.fxml"));
		root = loader.load();
		ModifyController ctrl = loader.getController();
		ctrl.getRoot(root);
		Label columnName=ctrl.setColumName();
		columnName.setText(getcolumnName);
		ctrl.ColumnName(getcolumnName);
		ctrl.getTableName(TableName);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
}
	
}
