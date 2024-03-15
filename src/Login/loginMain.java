package Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class loginMain extends Application {
	Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPageForDB.fxml"));
        root = loader.load();
        LoginController ctrl=loader.getController();
        
        ctrl.setStage(primaryStage);
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
        
        
//        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";

    }

    public static void main(String[] args) {
        launch(args);
    }
}
