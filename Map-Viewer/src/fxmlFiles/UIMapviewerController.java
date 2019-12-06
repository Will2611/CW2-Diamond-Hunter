package fxmlFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import Tiles.TileMap;
import fxmlFiles.UIControllerFunctions;

public class UIMapviewerController {
	UIControllerFunctions holder = new UIControllerFunctions();
	int NUM_COL = 40;
	int NUM_ROW = 40;
	//make true map loaded once
	boolean hasLoaded = false;
	TileMap tileMap = new TileMap(16);
	int[][] map;
	@FXML
	public GridPane grid;
	@FXML 
	public Label reminder;
	
	
	public void initialize() {
		hasLoaded = true;
		holder.loadMap(grid,reminder);
	}
	
	public void LoadMap() {
		if (hasLoaded == false) {
		hasLoaded = true;
		holder.loadMap(grid,reminder);
		}
	

	}
	public void PlaceAxe() {
		holder.setAxe(grid, reminder);
	}
	
	public void PlaceBoat() {
		System.out.println("Boat");
	}

    /*public void setMainApp(MainViewer mainapp) {
    	
    }*/

}
