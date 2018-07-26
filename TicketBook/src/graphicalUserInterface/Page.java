package graphicalUserInterface;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public abstract class Page extends Application {
	
	public ArrayList<VBox> createGrid(ArrayList<Object> items, int columns) {
		ArrayList<VBox> columnsList = new ArrayList<VBox>();
		for(int i = 0; i < columns;i++) {
			columnsList.add(new VBox(10));
			columnsList.get(i).setPadding(new Insets(0,20,0,20));
		}
		int columnID = 0;
		for(int i = 0; i < items.size();i++) {
			if(columnID == columns) {
				columnID = 0;
			}
			columnsList.get(columnID).getChildren().add((Node) items.get(i));
			columnID += 1;
		}
		return columnsList;
	}
}

