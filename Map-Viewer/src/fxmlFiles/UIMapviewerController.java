package fxmlFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import Tiles.TileMap;
import fxmlFiles.UIControllerFunctions;

public class UIMapviewerController implements UIControllerInterface {
	UIControllerFunctions holder = new UIControllerFunctions();
	StatusGetters status = new StatusGetters();
	int NUM_COL = 40;
	int NUM_ROW = 40;
	//make true map loaded once
	boolean hasLoaded = false;
	TileMap tileMap = new TileMap(16);
	int[][] map;
	@FXML
	public GridPane grid;
	@FXML 
	public Label reminder, cords;
	@FXML
	public MouseEvent hover;
	
	
	public void initialize() {
		hasLoaded = true;
		holder.loadMap(grid,reminder);
		status.setCordsText(cords, 0, 0);
	}
	
	public void LoadMap() {
		if (hasLoaded == false) {
		hasLoaded = true;
		holder.loadMap(grid,reminder);
		}
	
	}
	public void PlaceAxe() {
		this.hoverCords(hover);
		holder.setAxe(grid, reminder);
		
	}
	
	public void PlaceBoat() {
		this.hoverCords(hover);
		holder.setBoat(grid, reminder);
	}
	
	
	
	public void hoverCords(MouseEvent event) {
		hover=event;
		status.getcords(grid,cords,hover);
	}
	
    public void closeApp() {
    	System.exit(0);
    }

}
