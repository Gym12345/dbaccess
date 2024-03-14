module dbaccess {
	exports Alter;
	exports Create;
	exports Sequence;
	exports Main;
	exports Rename;
	exports Drop;
	opens Main to javafx.fxml; 
	opens Sequence to javafx.fxml; 
	opens Insert to javafx.fxml; 


	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
}