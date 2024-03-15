package Drop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Login.connection;
import Main.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DropController  {
	Parent root;
	@FXML public Button createButton;
	@FXML public VBox dropVbox;
	@FXML public Button dropbtn;
	String Query="none";
	AnchorPane viewArea;
	MainController ctrl;
	
	String genQuery;
	public void setCtrl(MainController ctrl) {
		this.ctrl=ctrl;
		
	}
	
	public void ac() {search();}
	public void setRoot(Parent root) {
		this.root=root;
	}
		Connection conn =connection.con;
		PreparedStatement ps;
		ResultSet rs;
		
	public void search() {
			
		ArrayList<String> tableList= new ArrayList();
		conn=connection.con;
			try {
					String url ="select * from tab where TNAME NOT LIKE 'BIN$%'";
					ps= conn.prepareStatement(url);
					rs=ps.executeQuery();
				while(rs.next()){
						tableList.add(rs.getString("TNAME"));
						}
				
				TextField textField = new TextField();
				textField.setText("삭제할 테이블 이름입니다."); // Set initial text
				Button buttonCommit = new Button("반영");
				
				//List<TextField> textFieldList = new ArrayList<>();
				for(int i=0;i<tableList.size();i++) 
				{
					System.out.println(tableList.get(i));
					Button buttons = new Button(tableList.get(i));
					
					
					
					
					AnchorPane.setLeftAnchor(buttons, 29.0);
					AnchorPane.setTopAnchor(buttons, 14.0);
					AnchorPane.setLeftAnchor(textField, 159.0);
					AnchorPane.setTopAnchor(textField, 19.0);
					AnchorPane.setLeftAnchor(buttonCommit, 524.0);
					AnchorPane.setTopAnchor(buttonCommit, 22.0);
					AnchorPane buttonPane = new AnchorPane(buttons,textField,buttonCommit);
					dropVbox.getChildren().add(buttonPane);
					
				
				
						buttonCommit.setOnAction(new EventHandler<ActionEvent>() { //반영
							
							
							    @Override
							    public void handle(ActionEvent event) {
							    	String text = textField.getText();
						            System.out.println("Commit button clicked. Text: " + text);
						            try {
						        		
						            	conn=connection.con;
						        		ps= conn.prepareStatement(genQuery);
						        		int a=ps.executeUpdate();
						        		Alert alerterror = new Alert(AlertType.INFORMATION);
						        		if(a==0) {
						        			System.out.println("데이터를 삭제하였습니다.");
						        			
											alerterror.setContentText("삭제가 완료되었습니다.");
						        			alerterror.showAndWait();
						        			genQuery="none";
						        			
						        			
						        			textField.setText("삭제할 테이블 이름입니다."); // Set initial text
						        			dropVbox.getChildren().clear();
						        			ctrl.clearViewArea();   //MainController 의 인스턴스
						        			search();    //dropController 의 search
						        			

						        			
						        		}else {
						        			
						        			
						        		}
						        	}catch (Exception e) {
						        		Alert alerterror = new Alert(AlertType.ERROR);
										alerterror.setContentText("반영을 하기 전에 테이블 명을 클릭해주세요.");
										alerterror.showAndWait();
										e.printStackTrace();
										
										
											
									}
						        }
					            
					            
						    	}
							
						);
						
						 buttons.setOnAction(new EventHandler<ActionEvent>() { //테이블명 버튼
							 
						        @Override
						        public void handle(ActionEvent event) {
						        	
						        	textField.setText("drop table "+buttons.getText());
						            genQuery="drop table "+"\""+buttons.getText()+"\"";
						            
						        
						        }
						        
						    }
						 
						 );
		 }
				}
			
				 catch (Exception e) {
						e.printStackTrace();
					}	
	 
			
				

	}
	
	
	public void cancelbtn() {
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
		
	}
	public void view() {
		int count=0;
		while(true) {
			while(count!=ctrl.count) {
				dropVbox.getChildren().clear();
    			search();
			}
		}
	}

   
    
    

   
}

    
    

   