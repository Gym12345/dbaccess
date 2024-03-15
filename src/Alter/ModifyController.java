package Alter;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Login.connection;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ModifyController implements Initializable{
	@FXML ChoiceBox columnType;
	@FXML TextField columnLength;
	@FXML Label columnName;
	@FXML Label errorLabel;
	Connection conn =Login.connection.con;
	PreparedStatement ps;
	ResultSet rs;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String[] ChoiceBoxList= {"VARCHAR2","NUMBER","DATE","TIMESTAMP"};
		columnType.getItems().addAll(ChoiceBoxList);
		columnLength.setDisable(true);
		errorLabel.setText("데이터 타입을 선택해주세요.");
	}
	String TableName=null;
	public void getTableName(String TableName) {
		this.TableName=TableName;

	}
	public Label setColumName() {
		return columnName;
	}
	String getcolumnName=null;
	public void ColumnName(String getcolumnName) {
		this.getcolumnName=getcolumnName;
	}

	MainController ctrl=MainController.ctrl;
	AlterController ctrl1=AlterController.ctrl;

	Parent root;
	public void getRoot(Parent root) {
		this.root= root;
	}

	public ChoiceBox getchoiceBox() {
		return columnType;
	}
	public TextField getColumnLength() {
		return columnLength;
	}
	public void typeSet() {
		ChoiceBox columnType=(ChoiceBox)root.lookup("#columnType");
		if(columnType.getValue()==null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("값을 선택해주세요.");
			alert.showAndWait();
		}else {
			StringBuilder SearchBuilder = new StringBuilder("select * from ");
			SearchBuilder.append(TableName);
			String finalSearchSql = SearchBuilder.toString();
			System.out.println("확인1");
			try {
				ps=conn.prepareStatement(finalSearchSql);
				rs=ps.executeQuery();
				if (rs.next()) {
					errorLabel.setText("값을 입력해주세요.");
					if(columnType.getValue().equals("VARCHAR2")) {
						columnLength.setDisable(false);
						columnLength.addEventFilter(KeyEvent.KEY_TYPED, event -> {          
							if (!Character.isDigit(event.getCharacter().charAt(0))) {
								event.consume();
							}
						});
					}
					//					else if(columnType.getValue().equals("NUMBER")) {
					//						columnLength.setDisable(false);
					//						columnLength.addEventFilter(KeyEvent.KEY_TYPED, event -> {
					//							String character = event.getCharacter();
					//							String text = columnLength.getText();
					//							if (character.matches("[0-9]") || (",".equals(character) && !text.contains(","))) {
					//								return;
					//							}
					//							event.consume(); 
					//						});
					//
					//					}
					else {
						columnLength.setDisable(true);
					}								
				}else {
					System.out.println("확인2");
					errorLabel.setText("현재 데이터가 존재합니다. 삭제 후 다시 이용해주세요.");	
					StringBuilder SearchNowtypeBuilder = new StringBuilder("SELECT data_type FROM USER_TAB_COLUMNS WHERE table_name = \"");
					SearchNowtypeBuilder.append(TableName).append("\" AND column_name = ").append("\"").append(getcolumnName).append("\"");
					String nowtypeSearchSql = SearchNowtypeBuilder.toString();
					try {
						conn=connection.con;
						ps=conn.prepareStatement(nowtypeSearchSql);
						rs=ps.executeQuery();
						if(rs.next()) {
							System.out.println();
							if(columnType.getValue().equals("VARCHAR2")) {
								String dataType=rs.getString(1);
								String nowType=dataType.substring(0,8);
								if(columnType.getValue().equals(nowType)) {
									columnLength.setDisable(false);
									columnLength.addEventFilter(KeyEvent.KEY_TYPED, event -> {          
										if (!Character.isDigit(event.getCharacter().charAt(0))) {
											event.consume();
										}
									});
								}
							}
							//							else if(columnType.getValue().equals("NUMBER")) {
							//								String dataType=rs.getString(1);
							//								String nowType=dataType.substring(0,6);
							//								if(columnType.getValue().equals(nowType)) {
							//									columnLength.setDisable(false);
							//									columnLength.addEventFilter(KeyEvent.KEY_TYPED, event -> {          
							//										if (!Character.isDigit(event.getCharacter().charAt(0))) {
							//											event.consume();
							//										}
							//									});
							//								}
							//							}
						}

					}catch (Exception e) {
						e.printStackTrace();

					}

				}	
			}
			catch (Exception es) {
				// TODO: handle exception
				es.printStackTrace();
			}
			if(columnType.getValue().equals(columnType))
				errorLabel.setText("현재 데이터가 존재합니다. 삭제 후 다시 이용해주세요.");
		}


	}
	public void commitModify() {
		ChoiceBox columnType=(ChoiceBox)root.lookup("#columnType");
		String columnName=(String)columnType.getValue();
		TextField NewColumnType=(TextField)root.lookup("#columnLength");
		String columnLan=NewColumnType.getText();
		StringBuilder modifyBuilder = new StringBuilder("ALTER TABLE ");
		modifyBuilder.append(TableName).append(" MODIFY ").append("\"").append(getcolumnName).append("\" ")
		.append(columnName).append("(").append(columnLan).append(")");
		String finalcolumnSql = modifyBuilder.toString();
		try {
			conn=connection.con;
			ps=conn.prepareStatement(finalcolumnSql);
			ps.execute();
			System.out.println("Modify실행");
			ps.clearParameters();
			ctrl.clearViewArea();
			ctrl1.clearViewArea();
			Alert alert =new Alert(AlertType.INFORMATION);
			alert.setContentText("변경되었습니다.");
			alert.showAndWait();
		}catch(java.sql.SQLSyntaxErrorException es) {
			Alert alert =new Alert(AlertType.ERROR);
			alert.setContentText("값이 존재할 경우 데이터 타입을 변경할 수 없습니다.. 내부 데이터를 삭제 후 진행해주세요.");
			alert.showAndWait();
		}
		catch (Exception e) {
			e.printStackTrace();
		}



	}
	public void cancelModify() {
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
	}

}
