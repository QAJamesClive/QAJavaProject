package code;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Page {

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		/*HBox row1 = new HBox(10);
		row1.setAlignment(Pos.CENTER);
		HBox row2 = new HBox(10);
		row2.setAlignment(Pos.CENTER);
		VBox column = new VBox(10);
		column.setAlignment(Pos.CENTER);
		root.setAlignment(Pos.CENTER);
		
		Button loginBtn = new Button("Login");
		
		Label usernameLb = new Label("Username: ");
		Label passwordLb = new Label("Password: ");
		
		TextField usernameTF = new TextField();
		TextField passwordTF = new TextField();
		
		row1.getChildren().addAll(usernameLb,usernameTF);
		row2.getChildren().addAll(passwordLb,passwordTF);
		column.getChildren().addAll(row1,row2,loginBtn);*/
		
		Button loginBtn = new Button("Login");
		
		Label usernameLb = new Label("Username: ");
		Label passwordLb = new Label("Password: ");
		
		TextField usernameTF = new TextField();
		TextField passwordTF = new TextField();
		
		ArrayList<Object> itemsList = new ArrayList<Object>();
		
		itemsList.addAll(Arrays.asList(usernameLb,usernameTF,passwordLb,passwordTF,loginBtn));
		
		//root.setAlignment(Pos.CENTER);
		
		HBox column = new HBox(10);
		
		
		column.getChildren().addAll(createGrid(itemsList, 2));
		
		root.getChildren().addAll(column);
		Scene scene= new Scene(root,800,600);
		
		primaryStage.setTitle("TicketBook");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
