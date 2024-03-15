module dbaccess {
	exports Alter;
	exports Create;
	exports Sequence;
	exports Main;
	exports Rename;
	exports Drop;
	exports Insert;
	exports Login;
	opens Main to javafx.fxml; 
	opens Sequence to javafx.fxml; 
	opens Alter to javafx.fxml;
	opens Insert to javafx.fxml;
	opens Login to javafx.fxml;
	

	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
}