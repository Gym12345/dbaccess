package Login;
import java.sql.Connection;

import Main.MainClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	Connection con=null;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField jdbcUrlField;
    Stage stage=null;
    public void setStage(Stage stage) {
    	this.stage=stage;
    }
    static String jdbcUrl;
    static String username;
    static String password;
    public void ConnectButtonClicked() {
    	jdbcUrl= jdbcUrlField.getText();
        username = usernameField.getText();
        password = passwordField.getText();

       
        
        connection conn =new connection(jdbcUrl, username, password);
        con=conn.getConnection();
        
        System.out.println(username);
        System.out.println(password);
        System.out.println(jdbcUrl);
        MainClass main = new MainClass();
        main.start(stage);
        main.setcon(con);
        

    }
    public String getjdbcUrl(){
    	return jdbcUrl;

    }
    public String getusername(){
    	return username;
    }
    public String getpassword(){
    	return password;
    }

}
